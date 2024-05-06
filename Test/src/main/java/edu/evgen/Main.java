package edu.evgen;

import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        while (true){
            Thread.sleep(200L);
            System.out.println("HELLO DOCKER WORLD!");
        }
    }
}