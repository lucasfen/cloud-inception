package com.lucas.demo.controller.testcontroller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@Slf4j
public class TestFileController {

    private static final Gson gson = new Gson();


    @RequestMapping(method = RequestMethod.POST, value = {"/cloud/v1/vmail/uploadFile",
            "/cloud/v1/vmail/uploadEmailTemplate"})
    @ResponseBody
    @CrossOrigin
    public void servletRequest(MultipartHttpServletRequest request) throws Exception {
        JsonObject contextJson = this.getHttpRequestContext(request);
        Set<String> contextSet = contextJson.keySet();
        Set<String> fileSet = request.getFileMap().keySet();
        JsonObject dataJson = new JsonObject();
        MultipartBodyBuilder multipartBodyBuilder = getMultipartBodyBuilder(request, contextSet, fileSet, dataJson);
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("requestUrl", request.getRequestURI());
        ctx.put("httpMethod", HttpMethod.resolve(request.getMethod()));
        ctx.put("multivalueMap", multipartBodyBuilder.build());
        JsonObject iotInternalRequestJson = new JsonObject();
        iotInternalRequestJson.add("context", contextJson);
        iotInternalRequestJson.add("data", dataJson);
    }

    private MultipartBodyBuilder getMultipartBodyBuilder(MultipartHttpServletRequest request, Set<String> contextSet, Set<String> fileSet, JsonObject dataJson) throws IOException, ServletException {
        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
        request.getParts().forEach(part -> {
            try {
                if (fileSet.contains(part.getName())) {
                    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
                    part.getHeaderNames().forEach(s -> multiValueMap.addAll(s, new ArrayList<>(part.getHeaders(s))));

                    HttpEntity<byte[]> httpEntity = new HttpEntity<>(StreamUtils.copyToByteArray(part.getInputStream()),
                            multiValueMap);
                    multipartBodyBuilder.part(part.getName(), httpEntity);
                } else {
                    if (!contextSet.contains(part.getName())) {
                        dataJson.addProperty(part.getName(), request.getParameter(part.getName()));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return multipartBodyBuilder;
    }


    private JsonObject getHttpRequestContext(HttpServletRequest request) {
        String traceId = request.getParameter("traceId");
        if (!StringUtils.hasText(traceId)) {
            traceId = String.valueOf(Instant.now().toEpochMilli());
        }
        String timeZone = request.getParameter("timeZone");
        if (!StringUtils.hasText(timeZone)) {
            timeZone = "timeZone";
        }
        String acceptLanguage = request.getParameter("acceptLanguage");
        if (!StringUtils.hasText(acceptLanguage)) {
            acceptLanguage = "acceptLanguage";
        }
        String osInfo = request.getParameter("osInfo");
        if (!StringUtils.hasText(osInfo)) {
            osInfo = "osInfo";
        }
        String clientType = request.getParameter("clientType");
        if (!StringUtils.hasText(clientType)) {
            clientType = "clientType";
        }
        String accountID = request.getParameter("accountID");
        if (!StringUtils.hasText(accountID)) {
            accountID = "accountID";
        }
        String token = request.getParameter("token");
        if (!StringUtils.hasText(token)) {
            token = "token";
        }
        String clientVersion = request.getParameter("clientVersion");
        if (!StringUtils.hasText(clientVersion)) {
            clientVersion = "clientVersion";
        }
        String clientInfo = request.getParameter("clientInfo");
        if (!StringUtils.hasText(clientInfo)) {
            clientInfo = "clientInfo";
        }
        String terminalId = request.getParameter("terminalId");
        if (!StringUtils.hasText(terminalId)) {
            terminalId = "terminalId";
        }
        String method = request.getParameter("method");
        if (!StringUtils.hasText(method)) {
            String[] uri = request.getRequestURI().split("/");
            method = uri[uri.length - 1];
        }
        JsonObject contextJson = new JsonObject();
        contextJson.addProperty("traceId", traceId);
        contextJson.addProperty("method", method);
        contextJson.addProperty("token", token);
        contextJson.addProperty("accountID", accountID);
        contextJson.addProperty("timeZone", timeZone);
        contextJson.addProperty("acceptLanguage", acceptLanguage);
        contextJson.addProperty("osInfo", osInfo);
        contextJson.addProperty("clientType", clientType);
        contextJson.addProperty("clientVersion", clientVersion);
        contextJson.addProperty("clientInfo", clientInfo);
        contextJson.addProperty("terminalId", terminalId);
        return contextJson;
    }

}
