package com.example.databuddy.controller;

import com.example.databuddy.domain.DataPlan;
import com.example.databuddy.service.DataPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DataPlanControllerTest {
    private DataPlanService dataPlanService;
    private DataPlanController controller;

    @BeforeEach
    void setUp() {
        var dataPlans = List.of(
                new DataPlan(1, "Starhub", "5GB free every week", 5, 7, 7, 0.01f),
                new DataPlan(2, "Starhub", "50GB free every month", 50, 30, 50, 0.01f),
                new DataPlan(3, "Singtel", "1GB free every day", 1, 1, 1, 0.015f)
        );
        dataPlanService = new DataPlanService(dataPlans);
        controller = new DataPlanController(dataPlanService);
    }

    @Test
    void shouldReturnAllAvailablePlansGivenNoProvider() {
        ResponseEntity<List<DataPlan>> response = controller.getDataPlans(Optional.empty());

        assertThat(response.getBody()).isEqualTo(dataPlanService.getAllAvailablePlans());
    }

    @Test
    void shouldReturnAvailablePlansForTheGivenProvider() {
        var provider = "Starhub";
        ResponseEntity<List<DataPlan>> response = controller.getDataPlans(Optional.of(provider));

        assertThat(response.getBody()).isEqualTo(dataPlanService.findAllPlansByProvider(provider));
    }
}