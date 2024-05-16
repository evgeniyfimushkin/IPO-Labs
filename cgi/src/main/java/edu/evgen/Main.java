package edu.evgen;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        // Установка заголовка Content-Type для возвращаемых данных
        System.out.println("Content-Type: text/html\n");

        // Получаем данные из потока ввода (stdin)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        StringBuilder inputData = new StringBuilder();
        while ((input = br.readLine()) != null) {
            inputData.append(input).append("\n");
        }

        // Обработка данных из формы
        // Здесь вы можете добавить свою логику для обработки данных из формы

        // Выводим HTML-страницу
        System.out.println("<!DOCTYPE html>");
        System.out.println("<html lang=\"ru\">");
        System.out.println("<head>");
        System.out.println("<meta charset=\"UTF-8\">");
        System.out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        System.out.println("<title>Результаты теста</title>");
        System.out.println("</head>");
        System.out.println("<body>");
        System.out.println("<h1>Результаты теста</h1>");
        System.out.println("<p>Java CGI-скрипт успешно выполнен.</p>");
        System.out.println("</body>");
        System.out.println("</html>");
    }
}
