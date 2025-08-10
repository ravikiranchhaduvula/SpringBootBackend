package com.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteOption {
    private String routeName;
    private double cost;
    private int timeInSeconds;
    private double fxRate;
}