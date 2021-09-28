package com.jakesajao.githubAnalytics.controllers;

import com.jakesajao.githubAnalytics.models.Chart;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class AnalyticController {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static Map<String, Integer> committerNoOfOccurence;
    @GetMapping("/analytic")
    public String Analytic(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Committer> committerList = (List<Committer>)  session.getAttribute("committerList");
        System.out.println("Sesssion commiter list: "+committerList.size());

        countFrequencies(committerList);

        model.addAttribute("chartData", getChartData());
        //System.out.println("Get Chart Data controller");
        return "analytic";
    }

    private List<Object> getChartData() {
        List<Object>paraList= new ArrayList<>();

        System.out.println("committerNoOfOccurence string: "+committerNoOfOccurence.toString());
        for (Map.Entry<String, Integer> val : committerNoOfOccurence.entrySet()) {
            String[] arr =new String[2];
            String key = val.getKey();
            arr[0] = key;
            arr[1] = val.getValue().toString();

            List<Object> list = Arrays.asList(arr);

            paraList.add(list);
        }
        System.out.println("param List string: "+paraList);
       return paraList;
    }
    public static Map<String, Integer> countFrequencies(List<Committer> list)
    {
        // hashmap to store the frequency of element
        Map<String, Integer> hm = new HashMap<String, Integer>();

        for (Committer i : list) {
            Integer j = hm.get(i.getName());
            hm.put(i.getName(), (j == null) ? 1 : j + 1);
        }
        // displaying the occurrence of elements in the arraylist
        for (Map.Entry<String, Integer> val : hm.entrySet()) {
            System.out.println("Element " + val.getKey() + " "
                    + "occurs"
                    + ": " + val.getValue() + " times");
        }
        committerNoOfOccurence = hm;
        return hm;
    }
}