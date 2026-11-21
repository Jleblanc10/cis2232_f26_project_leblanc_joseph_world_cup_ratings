
package ca.hccis.wc.controllers;

import ca.hccis.wc.bo.WCRatingsBO;
import ca.hccis.wc.entity.Player;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// Sprint 3 edits added 

@Controller
@RequestMapping("/wcratings")
public class WCRatingsController {

    private final WCRatingsBO wCRatingsBO;

    public WCRatingsController(WCRatingsBO wCRatingsBO) {
        this.wCRatingsBO = wCRatingsBO;
    }

    /**
     * Display all player ratings.
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("players", wCRatingsBO.processSelectAll());
        return "wcratings/list";
    }

    /**
     * Display the add-player form.
     */
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("player", new Player());
        return "wcratings/add";
    }

    /**
     * Save a new player rating.
     */
    @PostMapping("/add")
    public String addSubmit(
            @Valid @ModelAttribute("player") Player player,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "wcratings/add";
        }

        wCRatingsBO.processSave(player);
        return "redirect:/wcratings";
    }

    /**
     * Display the edit-player form.
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Player player = wCRatingsBO.processFindById(id);

        if (player == null) {
            return "redirect:/wcratings";
        }

        model.addAttribute("player", player);
        return "wcratings/edit";
    }

    /**
     * Save changes to an existing player rating.
     */
    @PostMapping("/edit/{id}")
    public String editSubmit(
            @PathVariable int id,
            @Valid @ModelAttribute("player") Player player,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "wcratings/edit";
        }

        player.setId(id);
        wCRatingsBO.processSave(player);

        return "redirect:/wcratings";
    }

    /**
     * Delete a player rating.
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        wCRatingsBO.processDelete(id);
        return "redirect:/wcratings";
    }
}
