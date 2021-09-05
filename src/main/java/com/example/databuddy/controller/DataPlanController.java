package com.example.databuddy.controller;


import com.example.databuddy.domain.DataPlan;
import com.example.databuddy.service.DataPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data-plans")
public class DataPlanController {
    private final DataPlanService dataPlanService;

    public DataPlanController(DataPlanService dataPlanService) {
        this.dataPlanService = dataPlanService;
    }

    @GetMapping
    public ResponseEntity<List<DataPlan>> getDataPlans(@RequestParam("provider") Optional<String> provider) {
        if (provider.isPresent()) {
            return ResponseEntity.ok(dataPlanService.findAllPlansByProvider(provider.get()));
        }
        return ResponseEntity.ok(dataPlanService.getAllAvailablePlans());
    }
}
