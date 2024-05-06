package edu.evgen.jsonParser;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JsonParser<T> {
    private final Class classObject;
    private T object;
    private FileInputStream in;

    public JsonParser(Class classObject) {
        this.classObject = classObject;
    }

    @SneakyThrows
    public T parsing(String filename) {
        try {

            object = (T) classObject.getDeclaredConstructor().newInstance();
            in = new FileInputStream(filename);
            int i;
            System.out.println();
            System.out.println();
            i = in.read();
            do {

                if (i == '{')
                    parsingObject(i);



                System.out.print((char) i);
                i = in.read();
            } while (i != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    private Object parsingObject(int i) {


    }

}
