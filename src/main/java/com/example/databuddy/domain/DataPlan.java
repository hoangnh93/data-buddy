package com.example.databuddy.domain;

import java.util.Objects;

public class DataPlan {
    private int id;
    private String provider;
    private String description;
    private int dataFreeInGB;
    private int billingCycleInDays;
    private float price;
    private float excessChargePerMB;

    public DataPlan() {
    }

    public DataPlan(int id, String provider, String description, int dataFreeInGB, int billingCycleInDays, float price, float excessChargePerMB) {
        this.id = id;
        this.provider = provider;
        this.description = description;
        this.dataFreeInGB = dataFreeInGB;
        this.billingCycleInDays = billingCycleInDays;
        this.price = price;
        this.excessChargePerMB = excessChargePerMB;
    }

    public int getId() {
        return id;
    }

    public String getProvider() {
        return provider;
    }

    public String getDescription() {
        return description;
    }

    public int getDataFreeInGB() {
        return dataFreeInGB;
    }

    public int getBillingCycleInDays() {
        return billingCycleInDays;
    }

    public float getPrice() {
        return price;
    }

    public float getExcessChargePerMB() {
        return excessChargePerMB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataPlan dataPlan = (DataPlan) o;
        return id == dataPlan.id && dataFreeInGB == dataPlan.dataFreeInGB && billingCycleInDays == dataPlan.billingCycleInDays && Float.compare(dataPlan.price, price) == 0 && Float.compare(dataPlan.excessChargePerMB, excessChargePerMB) == 0 && Objects.equals(provider, dataPlan.provider) && Objects.equals(description, dataPlan.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, provider, description, dataFreeInGB, billingCycleInDays, price, excessChargePerMB);
    }

    @Override
    public String toString() {
        return "DataPlan{" +
                "id=" + id +
                ", provider='" + provider + '\'' +
                ", description='" + description + '\'' +
                ", dataFreeInGB=" + dataFreeInGB +
                ", billingCycleInDays=" + billingCycleInDays +
                ", price=" + price +
                ", excessChargePerMB=" + excessChargePerMB +
                '}';
    }
}
