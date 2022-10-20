package com.himynameismoose.jobapptracker.controller;

import com.himynameismoose.jobapptracker.model.Company;
import com.himynameismoose.jobapptracker.model.Recruiter;
import com.himynameismoose.jobapptracker.repository.CompanyRepository;
import com.himynameismoose.jobapptracker.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RecruiterController {
    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/recruiters/new")
    public String showAddNewRecruiterForm(Model model) {
        List<Company> listCompanies = companyRepository.findAll();
        model.addAttribute("listCompanies", listCompanies);
        model.addAttribute("recruiter", new Recruiter());
        return "recruiter_form";
    }

    @PostMapping("/recruiters/save")
    public String saveRecruiters(Recruiter recruiter) {
        recruiterRepository.save(recruiter);
        return "redirect:/recruiters";
    }

    @GetMapping("/recruiters")
    public String listRecruiters(Model model) {
        List<Recruiter> listRecruiters = recruiterRepository.findAll();
        model.addAttribute("listRecruiters", listRecruiters);
        return "recruiters";
    }

    @GetMapping("/recruiters/edit/{id}")
    public String showEditRecruiterForm(@PathVariable("id") Integer id, Model model) {
        List<Company> listCompanies = companyRepository.findAll();
        Recruiter recruiter = recruiterRepository.findById(id).get();
        model.addAttribute("listCompanies", listCompanies);
        model.addAttribute("recruiter", recruiter);
        return "recruiter_form";
    }
}
