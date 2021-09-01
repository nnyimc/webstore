package fr.nnyimc.service;

import fr.nnyimc.dto.ProduitRequestDTO;
import fr.nnyimc.dto.ProduitResponseDTO;
import org.springframework.data.domain.Page;


public interface ProduitService {
    ProduitResponseDTO save(ProduitRequestDTO produitRequestDTO);
    ProduitResponseDTO delete(String id);
    ProduitResponseDTO findOne(String id);
    Page<ProduitResponseDTO> findAll(int index, int range);
}
