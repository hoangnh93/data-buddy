package com.example.databuddy.domain;

import java.util.List;
import java.util.Objects;

public class Subscriber {
    private String phoneNumber;
    private int planId;
    private List<DataUsage> usage;

    public Subscriber() {
    }

    public Subscriber(String phoneNumber, int planId, List<DataUsage> usage) {
        this.phoneNumber = phoneNumber;
        this.planId = planId;
        this.usage = usage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPlanId() {
        return planId;
    }

    public List<DataUsage> getUsage() {
        return usage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return planId == that.planId && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, planId);
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", planId=" + planId +
                '}';
    }
}
