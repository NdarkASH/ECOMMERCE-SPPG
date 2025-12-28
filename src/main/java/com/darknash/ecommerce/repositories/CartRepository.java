package com.darknash.ecommerce.repositories;

import com.darknash.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Cart findCartByUser_UserName(String userUserName);
}
