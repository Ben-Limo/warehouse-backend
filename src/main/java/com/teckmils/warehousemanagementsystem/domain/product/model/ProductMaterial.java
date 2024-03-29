package com.teckmils.warehousemanagementsystem.domain.product.model;

import com.teckmils.warehousemanagementsystem.domain.material.model.Material;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "product_materials")
@Entity
public class ProductMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "count", nullable = false)
    private Long count;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductMaterial() {
    }

    public ProductMaterial(final Product product, Material material, final long count) {
        this.count = count;
        this.material = material;
        this.product = product;
    }

    public Material getMaterial() {
        return this.material;
    }

    public Long getCount() {
        return this.count;
    }
}
