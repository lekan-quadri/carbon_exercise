package com.carbon.overdue.repositories;

import com.carbon.overdue.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
