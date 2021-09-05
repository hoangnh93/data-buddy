package com.example.databuddy;

import com.example.databuddy.domain.DataPlan;
import com.example.databuddy.domain.Subscriber;
import com.example.databuddy.service.DataUsageGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SeedingAppDataConfiguration {
    @Bean
    public List<DataPlan> dataPlans() {
        return List.of(
                new DataPlan(1, "Starhub", "5GB free every week", 5, 7, 7, 0.01f),
                new DataPlan(2, "Starhub", "50GB free every month", 50, 30, 50, 0.01f),
                new DataPlan(3, "Singtel", "1GB free every day", 1, 1, 1, 0.015f),
                new DataPlan(4, "Singtel", "25GB free every month", 25, 30, 28, 0.012f),
                new DataPlan(5, "M1", "7GB free every week", 7, 7, 10, 0.012f),
                new DataPlan(6, "Circle Life", "Pay as you go", 0, 30, 0, 0.0015f)
        );
    }

    @Bean
    public List<Subscriber> subscribers(DataUsageGenerator dataUsageGenerator) {
        return List.of(
                new Subscriber("80000000", 3, dataUsageGenerator.generateDataUsage(60)),
                new Subscriber("81111111", 2, dataUsageGenerator.generateDataUsage(90)),
                new Subscriber("82222222", 6, dataUsageGenerator.generateDataUsage(45)),
                new Subscriber("83333333", 1, dataUsageGenerator.generateDataUsage(35)),
                new Subscriber("84444444", 2, dataUsageGenerator.generateDataUsage(70)),
                new Subscriber("85555555", 2, dataUsageGenerator.generateDataUsage(50)),
                new Subscriber("86666666", 3, dataUsageGenerator.generateDataUsage(40)),
                new Subscriber("87777777", 4, dataUsageGenerator.generateDataUsage(100)),
                new Subscriber("88888888", 4, dataUsageGenerator.generateDataUsage(35)),
                new Subscriber("89999999", 5, dataUsageGenerator.generateDataUsage(40))
        );
    }
}
