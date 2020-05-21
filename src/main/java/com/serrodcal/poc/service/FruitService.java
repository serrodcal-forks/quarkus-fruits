package com.serrodcal.poc.service;

import com.serrodcal.poc.domain.Fruit;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class FruitService {

    private static final Logger logger = Logger.getLogger(FruitService.class);

    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitService() {
        fruits.add(new Fruit("Apple", "Winter fruit"));
        fruits.add(new Fruit("Pineapple", "Tropical fruit"));
    }

    @CircuitBreaker(requestVolumeThreshold = 4)
    public Uni<Set<Fruit>> getFruits() {
        logger.info("FruitService.getFruits()");
        return Uni.createFrom().item(this.fruits);
    }

    @CircuitBreaker(requestVolumeThreshold = 4)
    public Uni<Fruit> createFruit(Fruit fruit) {
        logger.info("FruitService.getFruits()");
        if(Objects.isNull(fruit)) {
            logger.warn("Fruit is null");
            return Uni.createFrom().failure(new RuntimeException("The fruit is null"));
        }

        boolean res = this.fruits.add(fruit);
        if (res) {
            logger.info("Fruit added");
            return Uni.createFrom().item(fruit);
        } else {
            logger.warn("Fruit already exists");
            return Uni.createFrom().failure(new RuntimeException("The fruit already exists"));
        }
    }

    /*
    // Call maybeFail() where you want open the circuit breaker

    private AtomicLong counter = new AtomicLong(0);

    private void maybeFail() {
        // introduce some artificial failures
        final Long invocationNumber = counter.getAndIncrement();
        if (invocationNumber % 4 > 1) { // alternate 2 successful and 2 failing invocations
            throw new RuntimeException("Service failed.");
        }
    }*/

}
