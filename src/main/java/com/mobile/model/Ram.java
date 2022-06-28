package com.mobile.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ram {

    private int size;
    private int speed;
    private String manufacturer;
}
