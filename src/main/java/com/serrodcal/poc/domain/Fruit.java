package com.serrodcal.poc.domain;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Arrays;

@RegisterForReflection
public class Fruit {

    public String name;
    public String description;

    public Fruit() { }

    public Fruit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean equals(Fruit fruit) {
        return this.name.equals(fruit.name) && this.description.equals(fruit.description);
    }

    public int hashCode() {
        String[] args = {this.name, this.description};
        return Arrays.hashCode(args);
    }

}
