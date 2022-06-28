package com.mobile.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Display {

    private int density;
    private float screenSize;
    private String screenType;
}
