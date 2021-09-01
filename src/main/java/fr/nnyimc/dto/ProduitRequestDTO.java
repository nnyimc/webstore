package fr.nnyimc.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProduitRequestDTO {
    private String id;
    private String name;
    private double price;
    private int quantity;
}
