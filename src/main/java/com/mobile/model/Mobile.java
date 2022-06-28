package com.mobile.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mobile {

    private int version;
    private String modelName;
    private int ramCode;
    private int displayCode;
}
