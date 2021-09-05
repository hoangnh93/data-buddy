package com.example.databuddy.service;

import com.example.databuddy.domain.DataPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DataPlanServiceTest {
    private List<DataPlan> dataPlans;
    private DataPlanService service;

    @BeforeEach
    void setUp() {
        dataPlans = List.of(
                new DataPlan(1, "Starhub", "5GB free every week", 5, 7, 7, 0.01f),
                new DataPlan(2, "Starhub", "50GB free every month", 50, 30, 50, 0.01f),
                new DataPlan(3, "Singtel", "1GB free every day", 1, 1, 1, 0.015f)
        );
        service = new DataPlanService(dataPlans);
    }

    @Test
    void shouldReturnAllAvailableDataPlans() {
        var availableDataPlans = service.getAllAvailablePlans();

        assertThat(availableDataPlans).containsExactlyElementsOf(dataPlans);
    }

    @Test
    void shouldReturnAllPlansForAGivenProvider() {
        var provider = "Singtel";
        var providerDataPlans = service.findAllPlansByProvider(provider);

        assertThat(providerDataPlans).containsExactly(
                new DataPlan(3, "Singtel", "1GB free every day", 1, 1, 1, 0.015f)
        );
    }
}