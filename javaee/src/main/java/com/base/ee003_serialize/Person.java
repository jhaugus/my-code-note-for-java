package com.base.ee003_serialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;



    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    public String play(String game) {
        System.out.println(name + ":" + game);
        return name + "喜欢玩" + game;
    }
}
