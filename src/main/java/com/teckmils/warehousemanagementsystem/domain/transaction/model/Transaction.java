package com.teckmils.warehousemanagementsystem.domain.transaction.model;

import com.teckmils.warehousemanagementsystem.domain.customer.model.Customer;
import com.teckmils.warehousemanagementsystem.domain.user.model.Role;
import com.teckmils.warehousemanagementsystem.domain.user.model.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TransactionProduct> transactionProducts;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method", referencedColumnName = "id")
    private PaymentMethod paymentMethod;

    public Transaction() {
    }

    public Transaction(String status,PaymentMethod paymentMethod, Customer customer, User user) {
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.customer = customer;
        this.user = user;
    }

    public UUID getId() {
        return this.id;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Customer getCustomer() {
        return customer;
    }

    public User getUser() {
        return user;
    }

    public Set<TransactionProduct> getTransactionProducts() {
        return this.transactionProducts;
    }

    public void setTransactionProducts(final Set<TransactionProduct> transactionProducts) {
        this.transactionProducts = transactionProducts;
    }
}