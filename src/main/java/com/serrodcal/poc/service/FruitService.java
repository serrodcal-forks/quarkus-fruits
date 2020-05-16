package com.serrodcal.poc.service;

import com.serrodcal.poc.domain.Fruit;
//import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.LinkedHashMap;
//import java.util.List;
import java.util.Set;

@ApplicationScoped
public class FruitService {

    private Set<Fruit> fruits = Collections
            .newSetFromMap(Collections
                    .synchronizedMap(new LinkedHashMap<>()));

    public FruitService() {
        fruits.add(new Fruit("Apple", "Winter fruit"));
        fruits.add(new Fruit("Pineapple", "Tropical fruit"));
    }

    public Uni<Set<Fruit>> getFruits() {
        return Uni.createFrom().item(this.fruits);
        //return Multi.createFrom().items(this.fruits.stream());
    }

}
