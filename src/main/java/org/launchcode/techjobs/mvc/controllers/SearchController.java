package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        ArrayList<Job> jobs;
        String title;

        jobs = JobData.findByColumnAndValue(searchType, searchTerm);

        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);

        if (searchType.equals("coreCompetency")) {
            title = "Jobs With Skill: " + searchTerm;
        } else {
            title = "Jobs With " + columnChoices.get(searchType) + ": " + searchTerm;
        }
        model.addAttribute("title", title);


        return "search";
    }

}
