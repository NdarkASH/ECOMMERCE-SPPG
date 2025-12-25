package com.darknash.ecommerce.dtos.request;

import com.darknash.ecommerce.entities.CartItem;
import com.darknash.ecommerce.entities.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    private User user;

    private List<UUID> items;
}
