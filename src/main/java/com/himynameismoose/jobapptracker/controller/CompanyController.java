package com.himynameismoose.jobapptracker.controller;

import com.himynameismoose.jobapptracker.model.Company;
import com.himynameismoose.jobapptracker.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Controls company data
 */
@Controller
public class CompanyController {
    // injections
    @Autowired
    private CompanyRepository companyRepository;

    /**
     * view of the list of companies
     * @param model hold company data
     * @return view of a list of companies
     */
    @GetMapping("/companies")
    public String listCompanies(Model model) {
        // get all companies
        List<Company> listCompanies = companyRepository.findAll();
        // share with view
        model.addAttribute("listCompanies", listCompanies);
        return "companies";
    }

    /**
     * new company
     * @param model store company data
     * @return new company form view
     */
    @GetMapping("/companies/new")
    public String showCompanyNewForm(Model model) {
        // company data
        model.addAttribute("company", new Company());
        return "company_form";
    }

    /**
     * save company data
     * @param company the company to be saved
     * @return redirect to a list of companies
     */
    @PostMapping("companies/save")
    public String saveCompany(Company company) {
        companyRepository.save(company);
        return "redirect:/companies";
    }
}
