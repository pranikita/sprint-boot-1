package com.silverbar.marketplace.silverbarmarketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.silverbar.marketplace.silverbarmarketplace.model.Order;

@Repository
public interface OrdertRepository extends JpaRepository<Order, Integer> {

}
