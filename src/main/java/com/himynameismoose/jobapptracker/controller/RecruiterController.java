package com.himynameismoose.jobapptracker.controller;

import com.himynameismoose.jobapptracker.model.Company;
import com.himynameismoose.jobapptracker.model.Recruiter;
import com.himynameismoose.jobapptracker.repository.CompanyRepository;
import com.himynameismoose.jobapptracker.repository.RecruiterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RecruiterController {
    private final RecruiterRepository recruiterRepository;

    private final CompanyRepository companyRepository;

    public RecruiterController(RecruiterRepository recruiterRepository, CompanyRepository companyRepository) {
        this.recruiterRepository = recruiterRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/recruiters/new")
    public String showCreateNewRecruiterForm(Model model) {
        List<Company> companyList = companyRepository.findAll();
        model.addAttribute("companyList", companyList);
        model.addAttribute("recruiter", new Recruiter());
        return "recruiter_form";
    }

    @PostMapping("/recruiters/save")
    public String saveRecruiter(Recruiter recruiter) {
        recruiterRepository.save(recruiter);
        return "redirect:/recruiters";
    }

    @GetMapping("/recruiters")
    public String listRecruiterss(Model model) {
        List<Recruiter> recruiterList = recruiterRepository.findAll();
        model.addAttribute("recruiterList", recruiterList);
        return "recruiters";
    }

    @GetMapping("/recruiters/edit/{id}")
    public String showEditRecruiterForm(@PathVariable("id") Integer id, Model model) {
        List<Company> companyList = companyRepository.findAll();
        Recruiter recruiter = recruiterRepository.findById(id).get();
        model.addAttribute("companyList", companyList);
        model.addAttribute("recruiter", recruiter);
        return "recruiter_form";
    }
}
