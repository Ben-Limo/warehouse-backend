package com.teckmils.warehousemanagementsystem.domain.customer.model;

import com.teckmils.warehousemanagementsystem.domain.store.model.Store;
import com.teckmils.warehousemanagementsystem.domain.user.model.Role;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "cust_name", nullable = false)
    private String customerName;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cust_type", referencedColumnName = "id")
    private CustomerType customerType;

    public Customer() {
    }

    public Customer(final String customerName,
                    final String contactPerson,
                    final String phoneNumber,
                    final String email,
                    final String city,
                    final String country,
                    final CustomerType customerType) {
        this.customerName = customerName;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.country = country;
        this.customerType = customerType;
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }


    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
