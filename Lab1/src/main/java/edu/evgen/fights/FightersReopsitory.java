package edu.evgen.fights;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        fighters.forEach(fighter1 -> fighter1.removeWin(fighter));
        setUpIds();
        log.info("size after remove: {}", fighters.size());
    }


    public static void addWin(int winner, int looser) {
        fighters.get(winner).addWin(fighters.get(looser));
        fighters.get(looser).removeWin(fighters.get(winner));
        sortFightersByWins();
        setUpIds();

    }

    public static FightMarker isWin(int fighter1, int fighter2) {
        if (fighters.get(fighter1).getWins().contains(fighters.get(fighter2))) {
            return FightMarker.WIN;
        }
        else if (fighters.get(fighter2).getWins().contains(fighters.get(fighter1))) {
            return FightMarker.LOSE;
        }
        else {
            return FightMarker.NO;
        }
    }
    public static void sortFightersByWins() {
        Collections.sort(fighters, new Comparator<Fighter>() {
            @Override
            public int compare(Fighter fighter1, Fighter fighter2) {
                return Integer.compare(fighter2.getWins().size(), fighter1.getWins().size());
            }
        });
    }
}
