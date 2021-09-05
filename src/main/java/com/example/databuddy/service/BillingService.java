package com.example.databuddy.service;

import com.example.databuddy.domain.DataPlan;
import com.example.databuddy.domain.Subscriber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {
    private List<DataPlan> dataPlans;
    private List<Subscriber> subscribers;

    public BillingService(List<DataPlan> dataPlans, List<Subscriber> subscribers) {
        this.dataPlans = dataPlans;
        this.subscribers = subscribers;
    }
}
