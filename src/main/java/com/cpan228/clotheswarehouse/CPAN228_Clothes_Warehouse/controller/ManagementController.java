package com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.controller;

import com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.model.User;
import com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.repository.ClothesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/management")
public class ManagementController {

    @GetMapping
    public String addItem() {
        return "management";
    }
    @Autowired
    private ClothesRepository clothesRepository;
    @PostMapping("/deleteAllItems")
    @PreAuthorize("hasRole('ADMIN')")
    public String processFightersDeletion(@AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
//        log.info("Deleting all fighters for user: {}", user.getAuthorities());
        clothesRepository.deleteAll();
        redirectAttributes.addFlashAttribute("deletionCompleted", true);
        return "redirect:/management";
    }
}
