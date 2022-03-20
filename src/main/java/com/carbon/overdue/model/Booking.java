package com.carbon.overdue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name="bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long reservation_id;
    private String room_type;
    private long customer_id;
    private int hourly_rate;
    private String status;
    private Date expected_checkin_time;
    private Date expected_checkout_time;

    public Booking() {
    }

    public long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public int getHourly_rate() {
        return hourly_rate;
    }

    public void setHourly_rate(int hourly_rate) {
        this.hourly_rate = hourly_rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpected_checkin_time() {
        return expected_checkin_time;
    }

    public void setExpected_checkin_time(Date expected_checkin_time) {
        this.expected_checkin_time = expected_checkin_time;
    }

    public Date getExpected_checkout_time() {
        return expected_checkout_time;
    }

    public void setExpected_checkout_time(Date expected_checkout_time) {
        this.expected_checkout_time = expected_checkout_time;
    }
}
