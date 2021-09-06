package com.example.databuddy.service;

import com.example.databuddy.config.MessageProperties;
import com.example.databuddy.domain.DataPlan;
import com.example.databuddy.domain.DataUsage;
import com.example.databuddy.domain.Subscriber;
import com.example.databuddy.exception.ErrorResponse;
import com.example.databuddy.exception.ResourceNotFoundException;
import com.example.databuddy.util.Constant;
import org.apache.tomcat.jni.Local;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BillingService {
    private List<DataPlan> dataPlans;
    private List<Subscriber> subscribers;
    private MessageProperties messageProperties;

    public BillingService(List<DataPlan> dataPlans, List<Subscriber> subscribers, MessageProperties messageProperties) {
        this.dataPlans = dataPlans;
        this.subscribers = subscribers;
        this.messageProperties = messageProperties;
    }

    public List<Map<String, ?>> getCost(String phoneNumber, Optional<Integer> period) {
        int days = period.isPresent() ? period.get() : 30;

        // Get subscriber corresponds with given phone number
        Subscriber subscriber = subscribers.stream().filter(x -> x.getPhoneNumber().equals(phoneNumber)).findFirst().orElse(null);
        if (subscriber == null) {
            // If phone number does not exist -> throw exception
            throw new ResourceNotFoundException(new ErrorResponse(Constant.NOT_FOUND, messageProperties.getMessage("NOT_FOUND")));
        }

        // Get plan corresponds with plan id
        DataPlan dataPlan = dataPlans.stream().filter(i -> i.getId() == subscriber.getPlanId()).findFirst().orElse(null);
        if (dataPlan == null) {
            // If there is no plan for given phone number -> throw exception
            throw new ResourceNotFoundException(new ErrorResponse(Constant.NOT_FOUND, messageProperties.getMessage("NOT_FOUND")));
        }

        List<Map<String, ?>> billings = new ArrayList<>();

        // Get billings circle during the period
        LocalDate endDate = LocalDate.now();
        int daysOfCircle = dataPlan.getBillingCycleInDays();

        while (days > 0) {
            LocalDate startDate = null;
            if (daysOfCircle >= 30) {
                // if circle is 30 days, start date of the billing circle is the first day of month
                startDate = endDate.minusDays(endDate.getDayOfMonth() + 1);
            } else {
                // start date of a billing circle will be calculated from the first day of a month
                int test = endDate.getDayOfMonth() % daysOfCircle;
                int minusDays = endDate.getDayOfMonth() % daysOfCircle == 0 ? daysOfCircle - 1 : endDate.getDayOfMonth() % daysOfCircle - 1;
                startDate = endDate.minusDays(minusDays);
            }

            // Get billing in a circle
            Map<String, ?> billing = getBillingInCircle(startDate, endDate, subscriber, dataPlan);
            billings.add(billing);

            // Continue for previous circle
            days = days - (endDate.getDayOfMonth() - startDate.getDayOfMonth() + 1);
            endDate = startDate.minusDays(1);
        }

        return billings;
    }

    private Map<String,?> getBillingInCircle(LocalDate startDate, LocalDate endDate, Subscriber subscriber, DataPlan dataPlan) {
        int usagesAmount = subscriber.getUsage().stream().filter(data -> !data.getDate().isBefore(startDate) && !data.getDate().isAfter(endDate))
                .mapToInt(DataUsage::getUsageInMB).sum();

        BigDecimal amount = BigDecimal.ZERO;
        if (usagesAmount > dataPlan.getDataFreeInGB() * 1000) {
            amount = BigDecimal.valueOf(dataPlan.getPrice() + (usagesAmount - dataPlan.getDataFreeInGB() * 1000) * dataPlan.getExcessChargePerMB());
        } else {
            amount = BigDecimal.valueOf(dataPlan.getPrice());
        }

        return Map.of(
                "startDate", startDate,
                "endDate", endDate,
                "amount", amount
        );
    }
}
