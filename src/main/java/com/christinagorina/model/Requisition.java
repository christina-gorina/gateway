package com.christinagorina.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Requisition implements Serializable {

    private String name;
    private Float income;
    private Boolean isConstant;
    private String currency;

}
