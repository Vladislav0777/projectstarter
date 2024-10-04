package ru.vzaytsev.studythumeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vzaytsev.studythumeleaf.model.entity.OrderLineEntity;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Long> {
}
