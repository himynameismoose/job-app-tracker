package com.himynameismoose.jobapptracker.controller;

import com.himynameismoose.jobapptracker.model.Company;
import com.himynameismoose.jobapptracker.model.Position;
import com.himynameismoose.jobapptracker.repository.CompanyRepository;
import com.himynameismoose.jobapptracker.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PositionController {
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/positions/new")
    public String showNewPositionForm(Model model) {
        List<Company> listCompanies = companyRepository.findAll();
        model.addAttribute("position", new Position());
        model.addAttribute("listCompanies", listCompanies);
        return "position_form";
    }

    @PostMapping("/positions/save")
    public String savePosition(Position position, HttpServletRequest request) {
        String[] detailsIDs = request.getParameterValues("detailID");
        String[] detailStatuses = request.getParameterValues("detailStatus");

        for (int i = 0; i < detailStatuses.length; i++) {
            if (detailsIDs != null && detailsIDs.length > 0) {
                position.setDetail(Integer.valueOf(detailsIDs[i]), detailStatuses[i]);
            } else {
                position.addDetail(detailStatuses[i]);
            }
        }
        positionRepository.save(position);
        return "redirect:/positions";
    }

    @GetMapping("/positions")
    public String listPositions(Model model) {
        List<Position> listPositions = positionRepository.findAll();
        model.addAttribute("listPositions", listPositions);
        return "positions";
    }

    @GetMapping("/positions/edit/{id}")
    public String showEditPositionForm(@PathVariable("id") Integer id, Model model) {
        Position position = positionRepository.findById(id).get();
        model.addAttribute("position", position);
        List<Company> listCompanies = companyRepository.findAll();
        model.addAttribute("listCompanies", listCompanies);
        return "position_form";
    }

    @GetMapping("/positions/delete/{id}")
    public String deletePosition(@PathVariable("id") Integer id) {
        positionRepository.deleteById(id);
        return "redirect:/positions";
    }
}
