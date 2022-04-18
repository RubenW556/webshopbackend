package com.s1127833.webshop.repository;

import com.s1127833.webshop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query
    public List<Item> findByDeletedFalse();

}
