package edu.evgen;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class JsonSerializer {
    private FileWriter writer;

    public FileWriter getWriter() {
        return writer;
    }

    @SneakyThrows
    public JsonSerializer(String filename) {
        writer = new FileWriter("json.json", false);
    }

    @SneakyThrows
    public void serialize(Object... objects) {
        if (objects.length == 1)
            serializeElement(objects[0]);
        else serializeArray(objects);
    }

    @SneakyThrows
    private void serializeArray(Object[] objects) {
        writer.write("[");

        for (int i = 0; i < objects.length; i++) {
            serialize(objects[i]);
            if ((i + 1) != objects.length) {
//                System.out.println(",,,,,,,,,,,,,,,,,,,");
                writer.write(",");
            }
        }

        writer.write("]");
    }

    @SneakyThrows
    private void serializeElement(Object object) {
        if (object == null) {
            serializeNullElement(object);
        } else if (object instanceof String) {
            serializeStringElement(object);
        } else if (object instanceof Number) {
            serializeElementNumber(object);
        } else if (object instanceof Boolean) {
            serializeBooleanElement(object);
        } else if (object.getClass().isArray()) {
            serializeArrayElement(object);
        } else if (object instanceof List) {
            serializeListElement((List<?>) object);
        } else if (object instanceof Map) {
            serializeMapElement((Map) object);
        } else {
            serializeObjectElement(object);
        }
    }

    @SneakyThrows
    private void serializeObjectElement(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();

        writer.write("{");

        int jsonFieldsCount = 0;
        for (Field field : fields) {
            if (field.isAnnotationPresent(JsonField.class))
                jsonFieldsCount++;
        }

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (fields[i].isAnnotationPresent(JsonField.class)) {
                writer.write("\"" + fields[i].getName() + "\"" + ": ");
                serialize(fields[i].get(object));
                if ((i + 1) != jsonFieldsCount)
                    writer.write(",");
            } else {
//                System.out.println(fields[i].getName());
            }
        }

        writer.write("}");

    }

    @SneakyThrows
    private void serializeMapElement(Map objects) {
        writer.write("[");
        for (int i = 0; i < objects.size(); i++) {
            serialize(objects.get(i));
            if ((i + 1) != objects.size())
                writer.write(",");
        }
        writer.write("]");
    }

    @SneakyThrows
    private void serializeListElement(List<?> objects) {
        writer.write("[");
        for (int i = 0; i < objects.size(); i++) {
            serialize(objects.get(i));
            if ((i + 1) != objects.size())
                writer.write(",");
        }
        writer.write("]");
    }

    private void serializeArrayElement(Object object) {

    }

    @SneakyThrows
    private void serializeBooleanElement(Object object) {
        writer.write(object.toString());
    }

    @SneakyThrows
    private void serializeElementNumber(Object object) {
        writer.write(object.toString());
//        System.out.println("NUMBER");
    }

    @SneakyThrows
    private void serializeStringElement(Object object) {
        writer.write("\"" + object + "\"");
    }

    @SneakyThrows
    private void serializeNullElement(Object object) {
        writer.write("null");
    }


    @SneakyThrows
    public void testWrite(String string) {
        writer.write(string);
    }
}
