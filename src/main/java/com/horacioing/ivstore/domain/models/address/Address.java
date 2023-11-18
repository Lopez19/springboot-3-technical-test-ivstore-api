package com.horacioing.ivstore.domain.models.address;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Address {
    private String street;
    private String city;
    private String department;
    private String country;
}
