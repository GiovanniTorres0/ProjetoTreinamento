package com.mscheckout.checkout.repository;

import com.mscheckout.checkout.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}