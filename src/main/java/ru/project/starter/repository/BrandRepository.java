package ru.project.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.starter.model.entity.BrandEntity;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}
