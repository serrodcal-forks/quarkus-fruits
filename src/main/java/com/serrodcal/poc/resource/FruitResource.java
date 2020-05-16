package com.serrodcal.poc.resource;

import javax.enterprise.context.ApplicationScoped;

import com.serrodcal.poc.service.FruitService;
import org.jboss.logging.Logger;

import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RoutingExchange;
import io.vertx.core.http.HttpMethod;

@ApplicationScoped
public class FruitResource {

    private static final Logger logger = Logger.getLogger(FruitResource.class);

    private FruitService fruitService;

    FruitResource(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @Route(path = "/api/v1/fruit",methods = HttpMethod.GET)
    void getFruits(RoutingExchange ex) {
        logger.info("FruitResource.getFruits()");
        this.fruitService.getFruits().subscribe().with(
                result -> ex.ok(result.toString()),
                failure -> ex.serverError()
        );
    }
}