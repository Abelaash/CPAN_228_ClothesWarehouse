package com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse;

import com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.model.Item;
import com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.repository.ClothesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Cpan228ClothesWarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cpan228ClothesWarehouseApplication.class, args);
	}


	@Bean
	public CommandLineRunner dataLoader(ClothesRepository itemRepository) {
		return args -> {

			itemRepository
					.save(Item.builder().name("Small Nomad Bag")
							.year_of_creation(2024)
							.brand("DIOR")
							.price(new BigDecimal(4700))
							.build());
			itemRepository
					.save(Item.builder()
							.name("B30 Sneaker")
							.year_of_creation(2023)
							.brand("GUCCI")
							.price(new BigDecimal(1350))
							.build());
			itemRepository
					.save(Item.builder()
							.name("Sweater")
							.year_of_creation(2022)
							.brand("DIOR")
							.price(new BigDecimal(4000))
							.build());
		};
	}
}
