package com.example.databuddy.service;

import com.example.databuddy.domain.DataUsage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DataUsageGenerator {
    public List<DataUsage> generateDataUsage(int numberOfDays) {
        var usage = new ArrayList<DataUsage>();
        var today = LocalDate.now();
        for (int i = numberOfDays; i > 0; i--) {
            usage.add(new DataUsage(today.minusDays(i), randomUsage()));
        }

        return usage;
    }

    private int randomUsage() {
        Random rand = new Random();
        var minUsage = 100;
        var maxUsage = 2000;
        return rand.nextInt((maxUsage - minUsage) + 1) + minUsage;
    }
}
