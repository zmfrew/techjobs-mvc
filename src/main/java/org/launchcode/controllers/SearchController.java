package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "/results")
    //?searchType=@{searchType}&searchTerm=@{searchTerm}
    public String searchResults(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {

        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> items = JobData.findByValue(searchTerm);
            model.addAttribute("jobs", items);
            model.addAttribute("columns", ListController.columnChoices);
        } else {

            ArrayList<HashMap<String, String>> items = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("jobs", items);
            model.addAttribute("columns", ListController.columnChoices);
        }
        return "search";
    }

}
