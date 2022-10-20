package com.himynameismoose.jobapptracker.controller;

import com.himynameismoose.jobapptracker.model.Application;
import com.himynameismoose.jobapptracker.model.Company;
import com.himynameismoose.jobapptracker.repository.ApplicationRepository;
import com.himynameismoose.jobapptracker.repository.CompanyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ApplicationController {
    private final ApplicationRepository applicationRepository;
    private final CompanyRepository companyRepository;

    public ApplicationController(ApplicationRepository applicationRepository, CompanyRepository companyRepository) {
        this.applicationRepository = applicationRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/applications/new")
    public String showNewApplicationForm(Model model) {
        List<Company> companyList = companyRepository.findAll();
        model.addAttribute("application", new Application());
        model.addAttribute("companyList", companyList);
        return "application_form";
    }

    @PostMapping("/applications/save")
    public String saveApplication(Application application) {
        applicationRepository.save(application);
        return "redirect:/applications";
    }

    @GetMapping("/applciations")
    public String listApplications(Model model) {
        List<Application> applicationList = applicationRepository.findAll();
        model.addAttribute("applicationList", applicationList);
        return "applications";
    }

    @GetMapping("/applications/edit/{id}")
    public String showEditApplicaitonForm(@PathVariable("id") Integer id, Model model) {
        Application application = applicationRepository.findById(id).get();
        model.addAttribute("application", application);
        List<Company> companyList = companyRepository.findAll();
        model.addAttribute("companyList", companyList);
        return "application_form";
    }
}
