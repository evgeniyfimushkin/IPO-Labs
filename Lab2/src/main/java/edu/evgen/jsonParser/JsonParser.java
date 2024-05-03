package edu.evgen.jsonParser;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class JsonParser {
    String json;
    ObjectParser objectParser;

    Integer pointer = 0;

    @SneakyThrows
    public JsonParser() {
        try (FileReader fileReader = new FileReader("json.json");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            json = bufferedReader.readLine();
        }

        System.out.println(json);
    }

    public Object parsing() {

        switch (json.charAt(pointer++)) {
            case '{':
                objectParsing();
            case '[':
                arrayparsing();

        }

        return new Object();
    }

    private void arrayparsing() {
        while (json.charAt(pointer) != ']'){
//            mainParsing();
        }
    }

    private void objectParsing() {
        while (json.charAt(pointer) != '}'){
            fieldsParsing();
        }
    }

    private void fieldsParsing() {

    }
    public Map<String,Object> parseJson(String jsonString) {
        Map<String, Object> jsonObject = new HashMap<>();

        int i = 0;
        char[] chars = jsonString.toCharArray();
        while (i < chars.length) {
            StringBuilder key = new StringBuilder();
            StringBuilder value = new StringBuilder();

            // Чтение ключа
            while (i < chars.length && chars[i] != ':') {
                if (chars[i] != '"' && chars[i] != '{' && chars[i] != '}') {
                    key.append(chars[i]);
                }
                i++;
            }
            i++; // Пропуск :

            // Чтение значения
            while (i < chars.length && chars[i] != ',' && chars[i] != '}') {
                if (chars[i] != '"' && chars[i] != '{' && chars[i] != '}') {
                    value.append(chars[i]);
                }
                i++;
            }
            jsonObject.put(key.toString().trim(), value.toString().trim());

            if (i < chars.length && chars[i] == ',') {
                i++;
            }
        }

        return jsonObject;
    }
}
