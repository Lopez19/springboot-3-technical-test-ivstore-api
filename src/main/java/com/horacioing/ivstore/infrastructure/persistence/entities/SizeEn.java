package com.horacioing.ivstore.infrastructure.persistence.entities;


import lombok.Getter;

@Getter
public enum SizeEn {
    S("Small"),
    M("Medium"),
    L("Large"),
    XL("Extra Large"),
    XXL("Extra Extra Large"),
    XXXL("Extra Extra Extra Large");

    private final String text;

    SizeEn(String text) {
        this.text = text;
    }
}
