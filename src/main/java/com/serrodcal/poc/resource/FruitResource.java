package com.serrodcal.poc.resource;

import javax.enterprise.context.ApplicationScoped;

import com.serrodcal.poc.domain.Fruit;
import com.serrodcal.poc.service.FruitService;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;

import io.quarkus.vertx.web.Route;
import io.vertx.core.http.HttpMethod;

@ApplicationScoped
public class FruitResource {

    private static final Logger logger = Logger.getLogger(FruitResource.class);

    private final String APPLICATION_JSON = "application/json";

    private FruitService fruitService;

    FruitResource(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @Timeout(250)
    @Retry(retryOn = RuntimeException.class)
    @Route(path = "/api/v1/fruit", methods = HttpMethod.GET)
    void getFruits(RoutingContext rc) {
        logger.info("FruitResource.getFruits()");
        this.fruitService.getFruits().subscribe().with(
                result -> rc.response().putHeader(HttpHeaders.CONTENT_TYPE, this.APPLICATION_JSON).end(Json.encode(result)),
                failure -> rc.fail(500)
        );
    }

    @Timeout(250)
    @Retry(retryOn = RuntimeException.class)
    @Route(path = "/api/v1/fruit", methods = HttpMethod.POST)
    void createFruit(RoutingContext rc) {
        logger.info("FruitResource.createFruit()");
        this.fruitService.createFruit(Json.decodeValue(rc.getBodyAsString(), Fruit.class)).subscribe().with(
                result -> rc.response().putHeader(HttpHeaders.CONTENT_TYPE, this.APPLICATION_JSON).end(Json.encode(result)),
                failure -> rc.response().setStatusCode(HttpStatus.SC_ACCEPTED).putHeader(HttpHeaders.CONTENT_TYPE, this.APPLICATION_JSON).end(failure.getMessage())
        );
    }
}