package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/data")
    public DashboardData getDashboardData() {
        return new DashboardData(123, 456.78, 9);
    }
}


class DashboardData {
    private  int users;
    private  double sales;
    private  int tasks;

    public DashboardData(int i, double v, int i1) {
    }

    // Constructor, getters and setters here...
}
