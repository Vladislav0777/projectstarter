package ru.vzaytsev.studythumeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vzaytsev.studythumeleaf.model.entity.ProductDetailsEntity;
import ru.vzaytsev.studythumeleaf.model.entity.ProductEntity;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Long> {
}