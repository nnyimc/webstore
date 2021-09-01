package fr.nnyimc.dto;
import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProduitResponseDTO {
    private String id;
    private String name;
    private double price;
    private int quantity;
}
