package com.serrodcal.poc.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.serrodcal.poc.service.FruitService;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;

@Path("api/v1/fruit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    private static final Logger logger = Logger.getLogger(FruitResource.class);

    private FruitService fruitService;

    FruitResource(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GET
    @Timeout(250)
    @Retry(maxRetries = 3, retryOn = RuntimeException.class)
    public Uni<Response> getFruits() {
        logger.info("FruitResource.getFruits()");
        try {
            return Uni.createFrom().item(this.fruitService.getFruits())
                    .map(fruits -> {
                        if (fruits.size() > 0) {
                            return Response.status(Response.Status.OK).entity(fruits).build();
                        } else {
                            logger.error("No content returned in FruitResource.getFruits()");
                            return Response.status(Response.Status.NO_CONTENT).entity(fruits).build();
                        }
                    });
        } catch (RuntimeException e) {
            logger.error("Circuit Breaker opens in FruitService.getFruits()");
            return Uni.createFrom().item(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .encoding("{\"error\": \"Circuit breaker opens in FruitService.getFruits()\"}")
                    .build()
            );
        }
    }
}