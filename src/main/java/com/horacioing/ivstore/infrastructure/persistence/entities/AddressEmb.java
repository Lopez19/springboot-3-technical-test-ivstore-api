package com.horacioing.ivstore.infrastructure.persistence.entities;


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AddressEmb {
    private String street;
    private String city;
    private String department;
    private String country;
}
