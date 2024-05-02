package edu.evgen.fights;

import lombok.Data;

@Data
public class Fighter {
    private Integer id;
    private String name;

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
}
