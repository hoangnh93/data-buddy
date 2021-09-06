package com.example.databuddy.controller;

import com.example.databuddy.service.BillingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/billings")
public class BillingController {

    private BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<List<Map<String, ?>>> getCost(
            @PathVariable("phoneNumber") String phoneNumber,
            @RequestParam("period") Optional<Integer> period) {
        // Validate if user can get information for this phone number
        // TODO
        // Validate phone number with information extract from token

        // Get cost corresponds with billing circle
        List<Map<String, ?>> costInfo = billingService.getCost(phoneNumber, period);

        return ResponseEntity.ok(costInfo);
    }
}
