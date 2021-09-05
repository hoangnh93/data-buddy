package com.example.databuddy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
class ApplicationTests {
    @LocalServerPort
    int port;

    @Test
    void shouldReturnAllDataPlans() {
        given().port(port)
                .when()
                .get("/data-plans")
                .then()
                .log().body()
                .statusCode(200)
                .body("size()", equalTo(6))

                .body("[0].provider", equalTo("Starhub"))
                .body("[0].description", equalTo("5GB free every week"))
                .body("[0].dataFreeInGB", equalTo(5))
                .body("[0].billingCycleInDays", equalTo(7))
                .body("[0].price", equalTo(7f))
                .body("[0].excessChargePerMB", equalTo(0.01f))

                .body("[1].provider", equalTo("Starhub"))
                .body("[1].description", equalTo("50GB free every month"))
                .body("[1].dataFreeInGB", equalTo(50))
                .body("[1].billingCycleInDays", equalTo(30))
                .body("[1].price", equalTo(50f))
                .body("[1].excessChargePerMB", equalTo(0.01f))

                .body("[2].provider", equalTo("Singtel"))
                .body("[2].description", equalTo("1GB free every day"))
                .body("[2].dataFreeInGB", equalTo(1))
                .body("[2].billingCycleInDays", equalTo(1))
                .body("[2].price", equalTo(1f))
                .body("[2].excessChargePerMB", equalTo(0.015f))

                .body("[3].provider", equalTo("Singtel"))
                .body("[3].description", equalTo("25GB free every month"))
                .body("[3].dataFreeInGB", equalTo(25))
                .body("[3].billingCycleInDays", equalTo(30))
                .body("[3].price", equalTo(28f))
                .body("[3].excessChargePerMB", equalTo(0.012f))

                .body("[4].provider", equalTo("M1"))
                .body("[4].description", equalTo("7GB free every week"))
                .body("[4].dataFreeInGB", equalTo(7))
                .body("[4].billingCycleInDays", equalTo(7))
                .body("[4].price", equalTo(10f))
                .body("[4].excessChargePerMB", equalTo(0.012f))

                .body("[5].provider", equalTo("Circle Life"))
                .body("[5].description", equalTo("Pay as you go"))
                .body("[5].dataFreeInGB", equalTo(0))
                .body("[5].billingCycleInDays", equalTo(30))
                .body("[5].price", equalTo(0f))
                .body("[5].excessChargePerMB", equalTo(0.0015f));
    }

    @Test
    void shouldReturnAllDataPlansForAGivenProvider() {
        given().port(port)
                .when()
                .param("provider", "M1")
                .get("/data-plans")
                .then()
                .log().body()
                .statusCode(200)
                .body("size()", equalTo(1))

                .body("[0].provider", equalTo("M1"))
                .body("[0].description", equalTo("7GB free every week"))
                .body("[0].dataFreeInGB", equalTo(7))
                .body("[0].billingCycleInDays", equalTo(7))
                .body("[0].price", equalTo(10f))
                .body("[0].excessChargePerMB", equalTo(0.012f));
    }

}
