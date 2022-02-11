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
public class Answer implements Serializable {

    private String name;
    private String checkResult;
    private String inputInfo;
}
