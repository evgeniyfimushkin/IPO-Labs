package edu.evgen.fights;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class FightersReopsitory {
    public final static List<Fighter> fighters = new ArrayList<>();

    public static void addFighter(String... fightersNames) {

        for (String fighter : fightersNames) {
            fighters.add(new Fighter(fighter));
        }

        //setting id as index of array
        fighters.forEach(fighter -> fighter.setId(fighters.indexOf(fighter)));

        log.info("AllFighters: ");
        fighters.forEach(fighter -> log.info("{}", fighter));
    }
}
