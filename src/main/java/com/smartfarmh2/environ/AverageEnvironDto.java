package com.smartfarmh2.environ;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AverageEnvironDto {
    @NotNull
    private Double temp;

    @NotNull
    private Double humid;

    @NotNull
    private Double soil;
}
