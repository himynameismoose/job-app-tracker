package com.himynameismoose.jobapptracker.controller;

import com.himynameismoose.jobapptracker.model.Company;
import com.himynameismoose.jobapptracker.repository.CompanyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CompanyController {
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/companies")
    public String listCompanies(Model model) {
        List<Company> companyList = companyRepository.findAll();
        model.addAttribute("companyList", companyList);
        return "companies";
    }

    @GetMapping("/companies/new")
    public String showCompanyNewForm(Model model) {
        model.addAttribute("company", new Company());
        return "company_form";
    }

    @PostMapping("companies/save")
    public String saveCompany(Company company) {
        companyRepository.save(company);
        return "redirect:/companies";
    }
}
