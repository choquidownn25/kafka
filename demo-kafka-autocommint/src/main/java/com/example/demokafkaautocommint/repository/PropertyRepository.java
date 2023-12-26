package com.example.demokafkaautocommint.repository;



import com.example.demokafkaautocommint.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findByPropertyKey(String key);
}
