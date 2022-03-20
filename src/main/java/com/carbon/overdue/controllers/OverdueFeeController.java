package com.carbon.overdue.controllers;

import com.carbon.overdue.model.Booking;
import com.carbon.overdue.model.FeeRequest;
import com.carbon.overdue.model.FeeResponse;
import com.carbon.overdue.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/booking")
public class OverdueFeeController {

    @Autowired
    private BookingRepository repository;

    @PostMapping
    public Booking addBooking(@RequestBody final Booking booking){
        return repository.saveAndFlush(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings(){
        return repository.findAll();
    }

    @PostMapping
    @RequestMapping("checkout/{id}/calculate_overdue_fee")
    public FeeResponse calculateFee(@RequestBody FeeRequest request, @PathVariable long id){

        Booking booking = repository.getById(id);
        FeeResponse response = cal(booking,request.getCheckout_time());
        return response;
    }

    public FeeResponse cal(Booking booking, String actualTime){

        FeeResponse response = new FeeResponse();
        long HR_IN_MS = 1000 * 60 * 60;
        long difference = 0;

        try {
            difference = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(booking.getExpected_checkout_time().toString().substring(0,11)+actualTime).getTime() - new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(booking.getExpected_checkout_time().toString()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int hours = 0;
        if(difference > 0) {
            while (difference > 0) {
                if (difference >= HR_IN_MS) {
                    difference -= HR_IN_MS;
                } else {
                    difference -= difference;
                }
                hours++;
            }

            response.setOverdue(true);
            response.setHours_overdue(hours);
            response.setOverdue_fee(hours * (booking.getHourly_rate() + (getRateIncrease(booking.getRoom_type(), getDayType(booking.getExpected_checkout_time().toString())) * booking.getHourly_rate()/100)));
        } else {
            response.setOverdue(false);
        }

        return response;
    }

    private double getRateIncrease(String roomType, String day){
        if(roomType.equalsIgnoreCase("regular") && day.equalsIgnoreCase("weekday")){
            return 7;
        } else if(roomType.equalsIgnoreCase("regular") && day.equalsIgnoreCase("weekend")){
            return 10;
        } else if(roomType.equalsIgnoreCase("deluxe") && day.equalsIgnoreCase("weekday")){
            return 8.5;
        } else if(roomType.equalsIgnoreCase("deluxe") && day.equalsIgnoreCase("weekend")){
            return 12;
        } else if(roomType.equalsIgnoreCase("palatial") && day.equalsIgnoreCase("weekday")){
            return 11;
        } else if(roomType.equalsIgnoreCase("palatial") && day.equalsIgnoreCase("weekend")){
            return 16;
        } else
            return 0;
    }

    private String getDayType(String expectedTime){
        Date now = null;
        try {
            now = new Date(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(expectedTime).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return "weekday";
            case 1:
            case 7:
                return "weekend";
        }
        return "";
    }
}
