package com.hyecheon.iloveyouboss.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooleanAnswer {
    private int questionId;
    private boolean value;

}
