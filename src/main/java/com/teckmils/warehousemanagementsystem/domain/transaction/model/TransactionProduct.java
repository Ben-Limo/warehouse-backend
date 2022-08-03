package com.teckmils.warehousemanagementsystem.domain.transaction.model;


import com.teckmils.warehousemanagementsystem.domain.product.model.Product;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transaction_products")
public class TransactionProduct {
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    public TransactionProduct() {
    }

    public TransactionProduct(final Transaction transaction, final Product product, final long quantity) {
        this.transaction = transaction;
        this.product = product;
        this.quantity = quantity;
    }

    @NonNull
    public UUID getId() {
        return this.id;
    }

    public Product getProduct() {
        return this.product;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }
}
