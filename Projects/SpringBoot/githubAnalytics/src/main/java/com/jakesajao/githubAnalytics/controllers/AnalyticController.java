package com.jakesajao.githubAnalytics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Random;

@Controller
public class AnalyticController {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    @GetMapping("/analytic")
    public String Analytic(Model model) {
        model.addAttribute("chartData", getChartData());
        System.out.println("Get Chart Data controller");
        return "analytic";
    }

    private List<List<Object>> getChartData() {
        System.out.println("Get Chart Data controller 2");
        return List.of(
                List.of("Mushrooms", RANDOM.nextInt(5)),
                List.of("Onions", RANDOM.nextInt(5)),
                List.of("Olives", RANDOM.nextInt(5)),
                List.of("Zucchini", RANDOM.nextInt(5)),
                List.of("Pepperoni", RANDOM.nextInt(5))
        );
    }
}