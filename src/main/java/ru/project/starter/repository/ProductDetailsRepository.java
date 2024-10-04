package ru.project.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.starter.model.entity.ProductDetailsEntity;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Long> {
}
