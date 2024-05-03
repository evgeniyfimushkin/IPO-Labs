package edu.evgen;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Fighter {
    @JsonField
    private Integer id;
    @JsonField
    private String name;
    @JsonField
    private final List<Fighter> wins = new ArrayList<>();
    @JsonField
    private final List<Integer> ints = new ArrayList<>();

    public Fighter(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fighter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void addWin(Fighter fighter) {
        wins.add(fighter);
    }
    public void removeWin(Fighter fighter){
        wins.remove(fighter);
    }

}
