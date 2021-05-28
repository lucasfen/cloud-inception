package com.lucas.demo.controller.testcontroller;

import java.io.IOException;
import java.io.InputStream;

import com.google.common.io.ByteStreams;
import com.google.gson.JsonObject;
import com.lucas.util.inputstream.InputUtils;
import com.lucas.util.thymeleaf.HtmlTemplateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucasfen
 * @Date 2021/05/27
 */
@RestController
@RequestMapping("/test/util")
public class TestUtilController {

    @PostMapping("/getInputStream")
    public String testGetInputStream() {
        InputStream inputStream = InputUtils.getInputStream1("static/amount.html");
        try {
            JsonObject content = new JsonObject();
            content.addProperty("amount", 1);
            String str = new String(ByteStreams.toByteArray(inputStream));
            return HtmlTemplateUtils.render(str, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
