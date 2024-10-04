package ru.vzaytsev.studythumeleaf.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(schema = "public", name = "brand")
public class BrandEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany
    @JoinColumn(name = "brand_id")
    private List<CategoryEntity> categories = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }
}
