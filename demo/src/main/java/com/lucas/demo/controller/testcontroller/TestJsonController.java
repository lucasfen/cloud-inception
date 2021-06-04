package com.lucas.demo.controller.testcontroller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucasfen
 * @Date 2021/05/31
 */
@RestController
@RequestMapping("/test/json")
public class TestJsonController {

    @PostMapping("/parse")
    public void test() {
        JsonObject configValue0 = new JsonObject();
        JsonObject configValue1 = new JsonObject();
        JsonObject configValue11 = new JsonObject();
        JsonObject configValue12 = new JsonObject();
        JsonObject configValue2 = new JsonObject();
        JsonObject configValue21 = new JsonObject();
        JsonObject configValue22 = new JsonObject();
        JsonObject configValue3 = new JsonObject();
        ArrayList<JsonObject> configValueList0 = new ArrayList<>();
        ArrayList<JsonObject> configValueList1 = new ArrayList<>();
        ArrayList<JsonObject> configValueList2 = new ArrayList<>();
        ArrayList<JsonObject> configValueList3 = new ArrayList<>();
        configValue0.addProperty("countryCode", "US");
        configValue0.addProperty("value", "true");
        configValueList0.add(configValue0);
        configValue1.addProperty("countryCode", "US");
        configValue1.addProperty("value", "true");
        configValue11.addProperty("countryCode", "CA");
        configValue11.addProperty("value", "true");
        configValue12.addProperty("countryCode", "MX");
        configValue12.addProperty("value", "true");
        configValueList1.add(configValue1);
        configValueList1.add(configValue11);
        configValueList1.add(configValue12);
        configValue2.addProperty("countryCode", "US");
        configValue2.addProperty("value", "true");
        configValue21.addProperty("countryCode", "CA");
        configValue21.addProperty("value", "true");
        configValue22.addProperty("countryCode", "MX");
        configValue22.addProperty("value", "true");
        configValueList2.add(configValue2);
        configValueList2.add(configValue21);
        configValueList2.add(configValue22);
        configValue3.addProperty("countryCode", "JP");
        configValue3.addProperty("value", "true");
        configValueList3.add(configValue3);



        //configValueList.add(configValue);
        //configValueList.add(configValue1);
//        String str0 = new Gson().toJson(configValueList0);
//        String str1 = new Gson().toJson(configValueList1);
//        String str2 = new Gson().toJson(configValueList2);
//        String str3 = new Gson().toJson(configValueList3);


//        String str0 = "[{\"countryCode\":\"US\",\"value\":\"true\"}]";
//        String str1 = "[{\"countryCode\":\"US\",\"value\":\"true\"},{\"countryCode\":\"CA\",\"value\":\"true\"},{\"countryCode\":\"MX\",\"value\":\"true\"}]";
//        String str2 = "[{\"countryCode\":\"US\",\"value\":\"true\"},{\"countryCode\":\"CA\",\"value\":\"true\"},{\"countryCode\":\"MX\",\"value\":\"true\"}]";
//        String str3 = "[{\"countryCode\":\"JP\",\"value\":\"false\"}]";
        String str0 = "{\"supported\":[\"US\", \"CA\", \"MX\"]}";
        String str1 = "{\"unsupported\":[\"JP\"]}";
        Config json1 = new Gson().fromJson(str0, Config.class);
        Config json2 = new Gson().fromJson(str1, Config.class);
//        JsonArray array0 = new JsonParser().parse(str0).getAsJsonArray();
//        JsonArray array1 = new JsonParser().parse(str1).getAsJsonArray();
//        JsonArray array2 = new JsonParser().parse(str2).getAsJsonArray();
//        JsonArray array3 = new JsonParser().parse(str3).getAsJsonArray();

//        System.out.println(array0);
//        System.out.println(array1);
//        System.out.println(array2);
//        System.out.println(array3);
        //System.out.println(array);
        System.out.println(json1);
        System.out.println(json2);
    }

    @Data
    public class Config {
        private List<String> supported;
        private List<String> unsupported;
    }
}
