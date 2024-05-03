package edu.evgen;

import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        JsonSerializer jsonSerializer = new JsonSerializer("/json.json");
        Fighter fighter = new Fighter("john");
        Fighter fighter1 = new Fighter("john1");
        Fighter fighter2 = new Fighter("john2");
        fighter.addWin(fighter1);
        fighter.addWin(fighter2);
        jsonSerializer.serialize(fighter);
        System.out.println(fighter);
        jsonSerializer.getWriter().close();
    }

}