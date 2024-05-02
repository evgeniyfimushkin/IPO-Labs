package edu.evgen.fights;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class FightersReopsitory {
    @Getter
    private final static List<Fighter> fighters = new ArrayList<>();

    private static void setUpIds() {
        fighters.forEach(fighter -> fighter.setId(fighters.indexOf(fighter)));
    }

    public static void addFighter(String... fightersNames) {

        for (String fighter : fightersNames) {
            fighters.add(new Fighter(fighter));
        }

        //setting id as index of array
        setUpIds();

        log.info("AllFighters: ");
        fighters.forEach(fighter -> log.info("{}", fighter));
    }

    public static void removeFighter(Fighter fighter) {
        fighters.remove(fighter);
        setUpIds();
        log.info("size after remove: {}", fighters.size());
    }

}
