package com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.controller.rest;

import com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.model.Item;
import com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.model.User;
import com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.repository.ClothesRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/addItem")
public class ItemController {

    @Autowired
    private ClothesRepository clothesRepository;

    @GetMapping
    public String addItem() {
        return "addItem";
    }

    @ModelAttribute
    // This model attribute has a lifetime of a request
    public Item item() {
        return Item
                .builder()
                .build();
    }

    @ModelAttribute
    public void checkUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        model.addAttribute("username", username);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE')")
    public String processClotheAddition(@Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "addItem";
        }

        clothesRepository.save(item);
        return "redirect:/clothelist";
    }
    @PostMapping("/deleteAllItems")
    @PreAuthorize("hasRole('ADMIN')")
    public String processFightersDeletion(@AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
//        log.info("Deleting all fighters for user: {}", user.getAuthorities());
        clothesRepository.deleteAll();
        redirectAttributes.addFlashAttribute("deletionCompleted", true);
        return "redirect:/addItem";
    }
}


