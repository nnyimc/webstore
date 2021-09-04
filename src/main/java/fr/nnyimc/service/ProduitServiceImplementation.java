package fr.nnyimc.service;

import fr.nnyimc.dao.ProduitRepository;
import fr.nnyimc.dto.*;
import fr.nnyimc.entities.Produit;
import fr.nnyimc.mappers.ProduitMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


@Service
@Transactional
public class ProduitServiceImplementation implements ProduitService {
    private final ProduitRepository produitRepository;
    private final ProduitMapper produitMapper;


    public ProduitServiceImplementation (
            ProduitRepository produitRepository,
            ProduitMapper produitMapper
    ) {
        this.produitMapper = produitMapper;
        this.produitRepository = produitRepository;
    }

    @Override
    public ProduitResponseDTO save(ProduitRequestDTO produitRequestDTO) {
        Produit fetchedProduit, savedProduit;
        initializeProduct();
        fetchedProduit = produitMapper.produitRequestDTOToProduit(produitRequestDTO);
        savedProduit = produitRepository.save(fetchedProduit);
        return produitMapper.produitToProduitResponseDTO(savedProduit);
    }

    @Override
    public ProduitResponseDTO delete(String id) {
        Produit fetchedProduit;
        initializeProduct();
        fetchedProduit = produitRepository.getById(Long.parseLong(id));
        produitRepository.delete(fetchedProduit);
        return produitMapper.produitToProduitResponseDTO(fetchedProduit);
    }

    @Override
    public ProduitResponseDTO findOne(String id) {
        Produit fetchedProduit;
        initializeProduct();
        fetchedProduit = produitRepository.getById(Long.parseLong(id));
        return produitMapper.produitToProduitResponseDTO(fetchedProduit);
    }

    @Override
    public Page<ProduitResponseDTO> findAll(int index, int range) {
        Page<Produit> produits = produitRepository.findAll(PageRequest.of(index,range));
        System.out.println(produits);
        List<ProduitResponseDTO> listProduitResponseDTO = new ArrayList<>();

        for(Produit p: produits) {
            listProduitResponseDTO.add(produitMapper.produitToProduitResponseDTO(p));
        }

        return new PageImpl<>(listProduitResponseDTO);
    }

    @Override
    public List<ProduitResponseDTO> findAll() {
        List<Produit> produits = produitRepository.findAll();
        List<ProduitResponseDTO> listProduitResponseDTO = new ArrayList<>();

        for(Produit p:produits) {
            listProduitResponseDTO.add(produitMapper.produitToProduitResponseDTO(p));
        }

        return listProduitResponseDTO;
    }

    private void initializeProduct() {
        Produit fetchedProduit;
        try {
            fetchedProduit = (Produit) Class
                    .forName("fr.nnyimc.entities.Produit")
                    .newInstance();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }
}
