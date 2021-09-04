package fr.nnyimc.web;

import fr.nnyimc.dto.*;
import fr.nnyimc.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProduitRestController {
    private final ProduitService produitService;

    public ProduitRestController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/home")
    public String index() {
        return "home";
    }

    @GetMapping("/produits")
    public String paginate(
            Model model,
            @RequestParam( name="index", defaultValue="0" ) String index,
            @RequestParam( name="range", defaultValue="10" ) String range
    ) {
        List<ProduitResponseDTO> listProduits = produitService.findAll();
        model.addAttribute("produits",listProduits);
        return "produits";
    }

    @PutMapping("/produits")
    public String update(
            Model model,
            @RequestBody ProduitRequestDTO produitRequestDTO
    ) {
        ProduitResponseDTO fetchedDTO = produitService.findOne(produitRequestDTO.getId());
        if (fetchedDTO == null) {
            model.addAttribute("produits", new ProduitResponseDTO());
        }
        model.addAttribute("produits", produitRequestDTO);
        produitService.delete(fetchedDTO.getId());
        produitService.save(produitRequestDTO);
        return "redirect:/produits";
    }

    @PostMapping("/produits")
    public String add(
            Model model,
            @RequestBody ProduitRequestDTO produitRequestDTO
    ) {
        produitService.save(produitRequestDTO).toString();
        model.addAttribute("produits", produitRequestDTO);
        return "redirect:/produits";
    }

    @DeleteMapping("/produits/{id}")
    public String delete(
            Model model,
            @PathVariable String id
    ) {
        model.addAttribute("produits", produitService.findOne(id));
        produitService.delete(id);
        return "redirect:/produits";

    }

}

