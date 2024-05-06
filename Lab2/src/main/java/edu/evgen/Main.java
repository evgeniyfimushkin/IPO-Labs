package edu.evgen;

import edu.evgen.jsonParser.JsonParser;
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
        fighter.setId(12);
        fighter.getInts().add(1);
        fighter.getInts().add(2);
        fighter.getInts().add(3);
        jsonSerializer.serialize(fighter, fighter1, fighter2);
//        System.out.println(fighter);
        jsonSerializer.getWriter().close();
        JsonParser<Fighter> jsonParser = new JsonParser<>(Fighter.class);
        Fighter fighterFromJson = jsonParser.parsing("json.json");
        System.out.println();
        System.out.println(fighterFromJson);
    }

}