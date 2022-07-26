package com.teckmils.warehousemanagementsystem.domain.product.model;

import com.teckmils.warehousemanagementsystem.domain.category.model.Category;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProductMaterial> materials;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public Product() {
    }

    public Product(final String name, final Double price, final Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<ProductMaterial> getProductMaterials() {
        return this.materials;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getCalculatedProductStock() {
        final Collection<Long> listOfPossibleProducts = new ArrayList<>();
        if(this.materials == null || this.materials.isEmpty()) {
            return 0L;
        }

        this.materials.forEach(material -> {
            final long materialsRequested = material.getCount();
            final long availableMaterials = material.getMaterial().getStock();

            if(availableMaterials >= materialsRequested) {
                listOfPossibleProducts.add(availableMaterials/materialsRequested);
            }
        });

        if(listOfPossibleProducts.size() != this.materials.size()) {
            return 0l;
        }

        final Optional<Long> minAmountPossible = listOfPossibleProducts.stream().min(Comparator.naturalOrder());

        if(minAmountPossible.isEmpty()) {
            return 0L;
        }

        return minAmountPossible.get();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
