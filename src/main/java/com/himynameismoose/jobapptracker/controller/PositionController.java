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

/**
 * This controls the position mappings, pulling data using the repository
 */
@Controller
public class PositionController {
    // injections
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private CompanyRepository companyRepository;

    /**
     * Show New Position form
     * @param model carry position data
     * @return position form
     */
    @GetMapping("/positions/new")
    public String showNewPositionForm(Model model) {
        // grab current list of companies
        List<Company> listCompanies = companyRepository.findAll();
        // position and list of companies for view
        model.addAttribute("position", new Position());
        model.addAttribute("listCompanies", listCompanies);
        return "position_form";
    }

    /**
     * Save potions
     * @param position the position to be saved
     * @param request serves as an input stream
     * @return redirect to a list of positions
     */
    @PostMapping("/positions/save")
    public String savePosition(Position position, HttpServletRequest request) {
        // store position details
        String[] detailsIDs = request.getParameterValues("detailID");
        String[] detailStatuses = request.getParameterValues("detailStatus");

        for (int i = 0; i < detailStatuses.length; i++) {
            // updating details
            if (detailsIDs != null && detailsIDs.length > 0) {
                position.setDetail(Integer.valueOf(detailsIDs[i]), detailStatuses[i]);
            } else { // new details -> new position
                position.addDetail(detailStatuses[i]);
            }
        }
        // save to database
        positionRepository.save(position);
        return "redirect:/positions";
    }

    /**
     * View the list of positions
     * @param model carrying position data
     * @return list of positions view
     */
    @GetMapping("/positions")
    public String listPositions(Model model) {
        // get a list of positions from the database
        List<Position> listPositions = positionRepository.findAll();
        // data shared to view
        model.addAttribute("listPositions", listPositions);
        return "positions";
    }

    /**
     * Edit position (uses the same form for new position)
     * @param id the id of the position to be edited/updated
     * @param model share position data
     * @return form view of position
     */
    @GetMapping("/positions/edit/{id}")
    public String showEditPositionForm(@PathVariable("id") Integer id, Model model) {
        // grab the position by id
        Position position = positionRepository.findById(id).get();
        // share position data
        model.addAttribute("position", position);
        // grab a list of companies
        List<Company> listCompanies = companyRepository.findAll();
        model.addAttribute("listCompanies", listCompanies);
        return "position_form";
    }

    /**
     * Delete position by id
     * @param id the id of the position to be deleted
     * @return redirect the page to view the lsit of positions
     */
    @GetMapping("/positions/delete/{id}")
    public String deletePosition(@PathVariable("id") Integer id) {
        positionRepository.deleteById(id);
        return "redirect:/positions";
    }
}
