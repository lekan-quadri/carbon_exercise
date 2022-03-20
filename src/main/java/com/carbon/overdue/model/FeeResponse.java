package com.carbon.overdue.model;

public class FeeResponse {

    private boolean isOverdue;
    private Double overdue_fee;
    private int hours_overdue;

    public FeeResponse() {
    }

    public FeeResponse(boolean isOverdue) {
        this.isOverdue = isOverdue;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }

    public Double getOverdue_fee() {
        return overdue_fee;
    }

    public void setOverdue_fee(Double overdue_fee) {
        this.overdue_fee = overdue_fee;
    }

    public int getHours_overdue() {
        return hours_overdue;
    }

    public void setHours_overdue(int hours_overdue) {
        this.hours_overdue = hours_overdue;
    }
}
