package com.lucas.util.thymeleaf;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author lucas
 * @date 2019/09/20
 */
public class HtmlTemplateUtils {

    private final static TemplateEngine templateEngine = new TemplateEngine();

    /**
     * 使用Thymeleaf渲染HTML
     *
     * @param template HTML模板
     * @param content  参数
     * @return 渲染后的HTML
     */
    public static String render(String template, JsonObject content) {

        Context context = new Context();
        Map<String, Object> params = new Gson().fromJson(content, Map.class);
        context.setVariables(params);
        return templateEngine.process(template, context);
    }
}
