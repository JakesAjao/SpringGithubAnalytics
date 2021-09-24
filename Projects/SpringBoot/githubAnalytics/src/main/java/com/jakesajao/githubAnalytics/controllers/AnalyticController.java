package com.jakesajao.githubAnalytics.controllers;

import com.jakesajao.githubAnalytics.models.Committer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;
@Controller
public class AnalyticController {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    @GetMapping("/analytic")
    public String Analytic(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Committer> committerList = (List<Committer>)  session.getAttribute("committerList");
        System.out.println("Sesssion commiter list: "+committerList.size());
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
    public static void countFrequencies(ArrayList<String> list)
    {
        // hash set is created and elements of
        // arraylist are insertd into it
        Set<String> st = new HashSet<String>(list);
        for (String s : st)
            System.out.println(s + ": " + Collections.frequency(list, s));
    }
//    public static void main(String[] args)
//    {
//        ArrayList<String> list = new ArrayList<String>();
//        list.add("Geeks");
//        list.add("for");
//        list.add("Geeks");
//        countFrequencies(list);
//    }
}