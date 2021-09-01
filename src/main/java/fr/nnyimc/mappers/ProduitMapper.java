package fr.nnyimc.mappers;

import fr.nnyimc.dto.*;
import fr.nnyimc.entities.Produit;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = Produit.class )
public interface ProduitMapper {

    @Mapping( target = "id", expression = "java( produit.getId().toString() )")
    ProduitResponseDTO produitToProduitResponseDTO( Produit produit );

    @Mapping( target = "id", source = "id")
    Produit produitRequestDTOToProduit( ProduitRequestDTO produitRequestDTO );
}
