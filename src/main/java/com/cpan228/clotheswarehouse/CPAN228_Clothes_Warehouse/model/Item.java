package com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    public enum Brand {
        BALENCIAGA("Balenciaga"), STONEISLAND("Stone Island"), DIOR("Dior"), GUCCI("Gucci"), LOUISVUITTON("Louis Vuitton"), OFFWHITE("Off-White"), SUPREME("Supreme"), VERSACE("Versace");
        private String title;
        private Brand(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }
    }

    @Min(2000)
    private int year_of_creation;

    @DecimalMin(value = "0.1", inclusive = true)
    private BigDecimal price;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();
}
