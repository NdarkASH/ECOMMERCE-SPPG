package com.darknash.ecommerce.dtos.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {

    private String name;

    private String slug;
}
