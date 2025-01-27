package com.rungroup.SpringMVCPt1.controller;

import com.rungroup.SpringMVCPt1.dto.ClubDto;
import com.rungroup.SpringMVCPt1.models.Club;
import com.rungroup.SpringMVCPt1.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    //Create a GET endpoint to display the list of all clubs
    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);

        return "clubs-list";
    }

    //Create a GET endpoint to display a list of clubs that were searched for
    @GetMapping("/clubs/search")
    public String listSearchedClubs(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);

        return "clubs-list";
    }

    //Create a GET endpoint to display a specific club
    @GetMapping("/clubs/{clubId}")
    public String clubDetails(@PathVariable("clubId") Long clubId, Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);

        return "clubs-detail";
    }

    //Create a GET endpoint to delete a club
    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId) {
        clubService.deleteClub(clubId);

        return "redirect:/clubs";
    }

    @GetMapping("/clubs/new") //To display the form to create a club (NOT a POST request mapping)
    public String createClubForm(Model model) {
        //Create a new empty club and pass it to the model. This is what the user will fill in
        Club club = new Club();
        model.addAttribute("club", club);

        return "clubs-create"; //Return the HTML code for the club creation page
    }

    @PostMapping("/clubs/new") //Since it is a POST, we can have the same URL here. We press the "create" button, and then the POST request occurs
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, //Recall: this clubDto comes from the model attribute "club" which was assigned in createClubForm()
                           BindingResult result) { //Used for validation
        //First validate
        if (result.hasErrors()) {
            return "clubs-create"; // Re-render the create form with error messages
        }

        //Create and use a save() method in the service (Essentially, the "C" in CRUD) if no errors found
        clubService.saveClub(clubDto);
        return "redirect:/clubs"; //Redirects to "/clubs" after the save. Should show the new club if one was created
    }

    //Create a GET endpoint for updating clubs
    @GetMapping("/clubs/{clubId}/edit") //We are going to need a clubId so that we know which one to edit
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model) { //As a general rule, use @PathVariable for when query parameters are mandatory and on the URL path, and @RequestParam when that's not the case
        //Create and use a findClubById() method in clubService
        ClubDto club = clubService.findClubById(clubId);

        //Add the club to be updated to the model
        model.addAttribute("club", club);

        //return HTML
        return "clubs-edit";
    }

    //Create a POST endpoint for updating clubs. Each time we POST, we will validate each field and print error messages if needed.
    @PostMapping("/clubs/{clubId}/edit") //Again, you can have the same URL for GET and POST.
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result) { //Recall: @ModelAttribute will associate the parameter with the attribute of the model with the provided name

        //First, the @Valid annotation will ensure that the club is "valid" per our validation annotations
        //Do our checks
        if (result.hasErrors()) {
            return "clubs-edit"; // Re-render the update form with error messages
        }

        //Process the form if valid
        club.setId(clubId);
        clubService.updateClub(club);

        return "redirect:/clubs";
    }
}
