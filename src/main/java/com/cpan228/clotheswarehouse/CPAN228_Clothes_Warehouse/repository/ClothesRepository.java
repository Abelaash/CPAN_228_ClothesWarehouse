package com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.repository;

import com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothesRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i WHERE i.brand LIKE :brand% AND i.year_of_creation = :year")
    List<Item> findByBrandStartsWithAndYear(@Param("brand") String brand, @Param("year") int year_of_creation);

}
