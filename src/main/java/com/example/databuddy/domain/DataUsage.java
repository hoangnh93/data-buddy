package com.example.databuddy.domain;

import java.time.LocalDate;
import java.util.Objects;

public class DataUsage {
    private LocalDate date;
    private int usageInMB;

    public DataUsage() {
    }

    public DataUsage(LocalDate date, int usageInMB) {
        this.date = date;
        this.usageInMB = usageInMB;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getUsageInMB() {
        return usageInMB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataUsage dataUsage = (DataUsage) o;
        return usageInMB == dataUsage.usageInMB && Objects.equals(date, dataUsage.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, usageInMB);
    }

    @Override
    public String toString() {
        return "DataUsage{" +
                "date=" + date +
                ", usageInMB=" + usageInMB +
                '}';
    }
}
