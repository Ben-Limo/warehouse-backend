package com.teckmils.warehousemanagementsystem.domain.customer.service;

import com.teckmils.warehousemanagementsystem.domain.category.model.Category;
import com.teckmils.warehousemanagementsystem.domain.customer.dto.CustomerRespItem;
import com.teckmils.warehousemanagementsystem.domain.customer.dto.UpdateCustomer;
import com.teckmils.warehousemanagementsystem.domain.customer.model.Customer;
import com.teckmils.warehousemanagementsystem.domain.customer.model.CustomerType;
import com.teckmils.warehousemanagementsystem.domain.customer.repository.CustomerRepository;
import com.teckmils.warehousemanagementsystem.domain.customer.repository.CustomerTypeRepository;
import com.teckmils.warehousemanagementsystem.domain.material.dto.AddMaterialStockItem;
import com.teckmils.warehousemanagementsystem.domain.material.model.Material;
import com.teckmils.warehousemanagementsystem.domain.material.model.MaterialStock;
import com.teckmils.warehousemanagementsystem.domain.product.dto.AddProductItem;
import com.teckmils.warehousemanagementsystem.domain.product.model.Product;
import com.teckmils.warehousemanagementsystem.domain.product.model.ProductMaterial;
import com.teckmils.warehousemanagementsystem.domain.user.dto.UpdateUser;
import com.teckmils.warehousemanagementsystem.domain.user.dto.UserResponse;
import com.teckmils.warehousemanagementsystem.domain.user.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerTypeRepository customerTypeRepository;

    public CustomerService(CustomerRepository customerRepository, CustomerTypeRepository customerTypeRepository) {
        this.customerRepository = customerRepository;
        this.customerTypeRepository = customerTypeRepository;
    }

    public List<CustomerRespItem> getCustomers() {
        final List<Customer> customers = this.customerRepository.findAll();
        List<CustomerRespItem> customerRespItems = new ArrayList<>();

        customers.forEach(customer -> customerRespItems.add(
                new CustomerRespItem(
                        customer.getId(),
                        customer.getCustomerName(),
                        customer.getContactPerson(),
                        customer.getPhoneNumber(),
                        customer.getEmail(),
                        customer.getCustomerType().getItemName(),
                        customer.getCity(),
                        customer.getCountry(),
                        customer.getCreatedAt(),
                        customer.getUpdatedAt()
                )
        ));

        return customerRespItems;
    }

    public CustomerRespItem getCustomersById(final UUID id) {
        final Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new CustomerRespItem(
                customer.getId(),
                customer.getCustomerName(),
                customer.getContactPerson(),
                customer.getPhoneNumber(),
                customer.getEmail(),
                customer.getCustomerType().getItemName(),
                customer.getCity(),
                customer.getCountry(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    public void addCustomers(final Collection<UpdateCustomer> customerReq) {
        customerReq.forEach(this::addSingleCustomer);
    }

    private void addSingleCustomer(final UpdateCustomer updateCustomer) {
        final CustomerType customerType = this.customerTypeRepository.findById(updateCustomer.customerType())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry! cant find requested customer type."));

        var customer = new Customer(
                updateCustomer.name(),
                updateCustomer.contact(),
                updateCustomer.phone(),
                updateCustomer.email(),
                updateCustomer.city(),
                updateCustomer.country(),
                customerType
                );


        this.customerRepository.save(customer);

    }
    @Transactional
    public ResponseEntity<String> deleteCustomerById(final UUID id) {
        this.customerRepository.deleteById(id);
        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }

    public ResponseEntity<String> updateCustomer(final UpdateCustomer request) {
        final var email = request.email();
        return this.customerRepository.findByEmail(email)
                .map(cust -> this.updateUser(cust,
                        request.name(),
                        request.contact(),
                        request.phone(),
                        request.email(),
                        request.city(),
                        request.country(),
                        request.customerType()))
                .orElseThrow(() -> new UsernameNotFoundException("could not find email: " + email));

    }

    private ResponseEntity<String> updateUser(final Customer customer,
                                              final String updateName,
                                              final String updateContact,
                                              final String updatePhone,
                                              final String updateEmail,
                                              final String updateCity,
                                              final String updateCountry,
                                              final UUID updateCustomerType) {
        final var customerType = this.customerTypeRepository.findById(updateCustomerType)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        customer.setCustomerName(updateName);
        customer.setContactPerson(updateContact);
        customer.setEmail(updateEmail);
        customer.setPhoneNumber(updatePhone);
        customer.setCity(updateCity);
        customer.setCity(updateCountry);
        customer.setCustomerType(customerType);

        this.customerRepository.save(customer);
        return new ResponseEntity<>("Customer updated successfully!", HttpStatus.OK);
    }
}
