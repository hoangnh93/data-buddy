package com.example.databuddy.service;

import com.example.databuddy.domain.DataPlan;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DataPlanService {
    private final List<DataPlan> dataPlans;

    public DataPlanService(List<DataPlan> dataPlans) {
        this.dataPlans = dataPlans;
    }

    public List<DataPlan> getAllAvailablePlans() {
        return dataPlans;
    }

    public List<DataPlan> findAllPlansByProvider(String provider) {
        return dataPlans.stream()
                .filter(plan -> plan.getProvider().equals(provider))
                .collect(toList());
    }
}
