package com.horacioing.ivstore.domain.models.product;


import lombok.Getter;

@Getter
public enum Size {
    S("Small"),
    M("Medium"),
    L("Large"),
    XL("Extra Large"),
    XXL("Extra Extra Large"),
    XXXL("Extra Extra Extra Large");

    private final String text;

    Size(String text) {
        this.text = text;
    }
}
