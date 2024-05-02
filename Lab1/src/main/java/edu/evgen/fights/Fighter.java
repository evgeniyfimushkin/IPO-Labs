package edu.evgen.fights;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
