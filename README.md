# carbon_exercise
This is the hospitality service for managing and calculating overdue fees
# Endpoint
- GET ALL BOOKINGS: GET http://localhost:8080/api/booking

- ADD BOOKING: POST http://localhost:8080/api/booking
Sample Request Body:
     {
        "reservation_id": 12004,
        "room_type": "deluxe",
        "customer_id": 12393,
        "hourly_rate": 230000,
        "status": "paid",
        "expected_checkin_time": "2022-03-11T23:00:00.000+00:00",
        "expected_checkout_time": "2022-03-15T10:00:00.000+00:00"
    }
    
- CHECKOUT/CALCULATE OVERDUE FEES: 
POST http://localhost:8080/api/booking/checkout/{id}/calculate_overdue_fee
Sample Request Body:
    {
        "checkout_time":"16:25"
    }
Note: there is a Path parameter expected which is the reservation_id

# Description
I used Srping for my solution. I have provided three methods on the API.
To calculate the fee, I have assumed that the necessary input id the actual checkout time which is on the same day as the expected checkout.
I also assume that the weekday and weekend rates are higher percentagewise than the usual charge.

