package com.example.databuddy.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DataUsageGeneratorTest {
    @Test
    void shouldGenerateSampleDataUsageForTheGivenNumberOfDays() {
        var generator = new DataUsageGenerator();

        var usage = generator.generateDataUsage(40);

        assertThat(usage.size()).isEqualTo(40);
    }
}