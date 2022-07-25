package com.teckmils.warehousemanagementsystem.domain.material.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "materials_stock")
@Entity
public class MaterialStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "stock", nullable = false)
    private long stock;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false)
    private Material material;

    public MaterialStock() {
    }

    public MaterialStock(final Material material, long stock) {
        this.stock = stock;
        this.material = material;
    }

    public MaterialStock(final long stock) {
        this.stock = stock;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(final long stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "MaterialStock{" +
                "id=" + id +
                ", stock=" + stock +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
