package com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.repository;

import com.cpan228.clotheswarehouse.CPAN228_Clothes_Warehouse.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

