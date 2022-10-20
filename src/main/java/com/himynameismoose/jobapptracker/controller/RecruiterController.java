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

/**
 * Controls Recruiter data
 */
@Controller
public class RecruiterController {
    // injections
    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private CompanyRepository companyRepository;

    /**
     * new recruiter form
     * @param model store recruiter data
     * @return view of recruiter form
     */
    @GetMapping("/recruiters/new")
    public String showAddNewRecruiterForm(Model model) {
        // get all the companies for the new recruiter to be assigned to
        List<Company> listCompanies = companyRepository.findAll();
        // share list of recruiters and save recruiter data
        model.addAttribute("listCompanies", listCompanies);
        model.addAttribute("recruiter", new Recruiter());
        return "recruiter_form";
    }

    /**
     * save recruiter data from new/edit/update
     * @param recruiter the recruiter to save
     * @return redirect to a list of recruiters
     */
    @PostMapping("/recruiters/save")
    public String saveRecruiters(Recruiter recruiter) {
        recruiterRepository.save(recruiter);
        return "redirect:/recruiters";
    }

    /**
     * view a list of recruiters
     * @param model share recruiter data
     * @return a view of list of recruiters
     */
    @GetMapping("/recruiters")
    public String listRecruiters(Model model) {
        List<Recruiter> listRecruiters = recruiterRepository.findAll();
        model.addAttribute("listRecruiters", listRecruiters);
        return "recruiters";
    }

    /**
     * show the same form as new recruiter
     * @param id the id of the recruiter to edit
     * @param model share recruiter details with the form view
     * @return form view (same as new)
     */
    @GetMapping("/recruiters/edit/{id}")
    public String showEditRecruiterForm(@PathVariable("id") Integer id, Model model) {
        List<Company> listCompanies = companyRepository.findAll();
        Recruiter recruiter = recruiterRepository.findById(id).get();
        model.addAttribute("listCompanies", listCompanies);
        model.addAttribute("recruiter", recruiter);
        return "recruiter_form";
    }
}
