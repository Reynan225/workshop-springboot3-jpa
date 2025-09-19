package com.educandoweb.course.repositories;

import com.educandoweb.course.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
