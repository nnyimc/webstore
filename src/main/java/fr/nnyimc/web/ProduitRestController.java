package fr.nnyimc.web;

import fr.nnyimc.dto.*;
import fr.nnyimc.service.ProduitService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProduitRestController {
    private final ProduitService produitService;

    public ProduitRestController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/produits")
    public Page<ProduitResponseDTO> paginate(
            @RequestParam( name="index", defaultValue="0" ) String index,
            @RequestParam( name="range", defaultValue="10" ) String range
    ) {
        return produitService.findAll(Integer.parseInt(index), Integer.parseInt(range));
    }

    @PutMapping("/produits")
    public ProduitResponseDTO update(@RequestBody ProduitRequestDTO produitRequestDTO) {
        ProduitResponseDTO fetchedDTO = produitService.findOne(produitRequestDTO.getId());
        if (fetchedDTO == null) {
            return new ProduitResponseDTO();
        }
        return fetchedDTO;
    }

    @PostMapping("/produits")
    public ProduitResponseDTO add(@RequestBody ProduitRequestDTO produitRequestDTO) {
        return produitService.save(produitRequestDTO);
    }

    @DeleteMapping("/produits/{id}")
    public ProduitResponseDTO delete(@PathVariable String id) {
        return produitService.delete(id);
    }

}

