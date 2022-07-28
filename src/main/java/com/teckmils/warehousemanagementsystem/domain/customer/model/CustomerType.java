package com.teckmils.warehousemanagementsystem.domain.customer.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "customer_types")
public class CustomerType {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    public CustomerType() {
    }

    public UUID getUid() {
        return this.id;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public String getItemName() {
        return this.itemName;
    }
}
