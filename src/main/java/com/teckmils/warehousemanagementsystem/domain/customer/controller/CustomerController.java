package com.teckmils.warehousemanagementsystem.domain.customer.controller;

import com.teckmils.warehousemanagementsystem.domain.customer.dto.AddCustomer;
import com.teckmils.warehousemanagementsystem.domain.customer.dto.CustomerRespItem;
import com.teckmils.warehousemanagementsystem.domain.customer.dto.CustomerItem;
import com.teckmils.warehousemanagementsystem.domain.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/customers")
    public List<CustomerRespItem> getCustomers() {
        return this.customerService.getCustomers();
    }

    @GetMapping("/api/customers/{id}")
    public CustomerRespItem getCustomer(@PathVariable final UUID id) {
        return this.customerService.getCustomersById(id);
    }

    @PostMapping("/api/customers")
    public void addMaterials(@RequestBody @Valid final AddCustomer request) {
        this.customerService.addCustomers(request.customers());
    }

    @PatchMapping("/api/customers/update-customer")
    public ResponseEntity<String> updateCustomer(@RequestBody final CustomerItem request) {
        return this.customerService.updateCustomer(request);
    }

    @DeleteMapping("/api/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable @NotNull final UUID id) {
        return this.customerService.deleteCustomerById(id);
    }
}
