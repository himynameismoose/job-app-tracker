package com.himynameismoose.jobapptracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is the main app controller
 */
@Controller
public class AppController {
    /**
     * Default view
     * @return index.html
     */
    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }
}
