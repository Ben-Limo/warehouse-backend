package com.teckmils.warehousemanagementsystem.domain.transaction.service;


import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.teckmils.warehousemanagementsystem.domain.category.dto.CategoryResponse;
import com.teckmils.warehousemanagementsystem.domain.customer.dto.CustomerItem;
import com.teckmils.warehousemanagementsystem.domain.customer.dto.CustomerRespItem;
import com.teckmils.warehousemanagementsystem.domain.customer.model.Customer;
import com.teckmils.warehousemanagementsystem.domain.customer.model.CustomerType;
import com.teckmils.warehousemanagementsystem.domain.customer.repository.CustomerRepository;
import com.teckmils.warehousemanagementsystem.domain.customer.repository.CustomerTypeRepository;
import com.teckmils.warehousemanagementsystem.domain.product.dto.ProductResponse;
import com.teckmils.warehousemanagementsystem.domain.product.dto.SellProducts;
import com.teckmils.warehousemanagementsystem.domain.product.model.Product;
import com.teckmils.warehousemanagementsystem.domain.product.repository.ProductRepository;
import com.teckmils.warehousemanagementsystem.domain.transaction.TransactionStatus;
import com.teckmils.warehousemanagementsystem.domain.transaction.dto.TransactionResponseItem;
import com.teckmils.warehousemanagementsystem.domain.transaction.model.PaymentMethod;
import com.teckmils.warehousemanagementsystem.domain.transaction.model.Transaction;
import com.teckmils.warehousemanagementsystem.domain.transaction.model.TransactionProduct;
import com.teckmils.warehousemanagementsystem.domain.transaction.repository.PaymentMethodRepository;
import com.teckmils.warehousemanagementsystem.domain.transaction.repository.TransactionProductRepository;
import com.teckmils.warehousemanagementsystem.domain.transaction.repository.TransactionRepository;
import com.teckmils.warehousemanagementsystem.domain.user.dto.UserResponse;
import com.teckmils.warehousemanagementsystem.domain.user.model.User;
import com.teckmils.warehousemanagementsystem.domain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final TransactionProductRepository transactionProductRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final CustomerTypeRepository customerTypeRepository;

    private final PaymentMethodRepository paymentMethodRepository;

    public TransactionService(
            TransactionRepository transactionRepository,
            TransactionProductRepository transactionProductRepository,
            ProductRepository productRepository,
            UserRepository userRepository,
            CustomerRepository customerRepository,
            CustomerTypeRepository customerTypeRepository,
            PaymentMethodRepository paymentMethodRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionProductRepository = transactionProductRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.customerTypeRepository = customerTypeRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public List<TransactionResponseItem> getTransactions() {
        final List<Transaction> transactions = this.transactionRepository.findAll();
        List<TransactionResponseItem> transactionResponseItems = new ArrayList<>();

        transactions.forEach(transaction -> transactionResponseItems.add(
                new TransactionResponseItem(
                        transaction.getId(),
                        transaction.getStatus(),
                        transaction.getPaymentMethod().getItemName(),
                        TransactionService.mapResponseProductFromTransaction(transaction),
                        new CustomerRespItem(
                                transaction.getCustomer().getId(),
                                transaction.getCustomer().getCustomerName(),
                                transaction.getCustomer().getContactPerson(),
                                transaction.getCustomer().getPhoneNumber(),
                                transaction.getCustomer().getEmail(),
                                transaction.getCustomer().getCustomerType().getItemName(),
                                transaction.getCustomer().getCity(),
                                transaction.getCustomer().getCountry(),
                                transaction.getCustomer().getCreatedAt(),
                                transaction.getCustomer().getUpdatedAt()
                        ),
                        new UserResponse(
                                transaction.getUser().getId(),
                                transaction.getUser().getUserName(),
                                transaction.getUser().getFirstName(),
                                transaction.getUser().getLastName(),
                                transaction.getUser().getUserName(),
                                transaction.getUser().getEmail(),
                                transaction.getUser().getRole().getItemName(),
                                transaction.getUser().getCreatedAt(),
                                transaction.getUser().getUpdatedAt()
                        ),
                        transaction.getCreatedAt(),
                        transaction.getUpdatedAt()
                )
        ));

        return transactionResponseItems;
    }

    private static Collection<ProductResponse> mapResponseProductFromTransaction(final Transaction transaction) {
        return transaction.getTransactionProducts()
                .stream().map(product -> new ProductResponse(
                        product.getProduct().getId(),
                        product.getProduct().getName(),
                        product.getProduct().getImageURL(),
                        product.getProduct().getPrice(),
                        product.getProduct().getCalculatedProductStock(),
                        new CategoryResponse(
                                product.getProduct().getCategory().getId(),
                                product.getProduct().getCategory().getName(),
                                product.getProduct().getCategory().getDescription(),
                                product.getProduct().getCategory().getCreatedAt(),
                                product.getProduct().getCategory().getUpdatedAt()
                        ),
                        product.getProduct().getCreatedAt(),
                        product.getProduct().getUpdatedAt(),
                        product.getProduct().getProductMaterials()
                )).toList();
    }
    public TransactionResponseItem getTransactionByID(UUID id) {
        final Transaction transaction = this.transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find transaction with wanted UUID"));

        return new TransactionResponseItem(
                transaction.getId(),
                transaction.getStatus(),
                transaction.getPaymentMethod().getItemName(),
                TransactionService.mapResponseProductFromTransaction(transaction),
                new CustomerRespItem(
                        transaction.getCustomer().getId(),
                        transaction.getCustomer().getCustomerName(),
                        transaction.getCustomer().getContactPerson(),
                        transaction.getCustomer().getPhoneNumber(),
                        transaction.getCustomer().getEmail(),
                        transaction.getCustomer().getCustomerType().getItemName(),
                        transaction.getCustomer().getCity(),
                        transaction.getCustomer().getCountry(),
                        transaction.getCustomer().getCreatedAt(),
                        transaction.getCustomer().getUpdatedAt()
                ),
                new UserResponse(
                        transaction.getUser().getId(),
                        transaction.getUser().getUserName(),
                        transaction.getUser().getFirstName(),
                        transaction.getUser().getLastName(),
                        transaction.getUser().getUserName(),
                        transaction.getUser().getEmail(),
                        transaction.getUser().getRole().getItemName(),
                        transaction.getUser().getCreatedAt(),
                        transaction.getUser().getUpdatedAt()
                ),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
        );
    }

    public TransactionResponseItem createTransaction(final SellProducts request,
                                                     final Authentication authentication) {

        final User currentUser = this.userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find valid user to perform transaction with"));

        final Customer customer = this.customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final PaymentMethod paymentMethod = this.paymentMethodRepository.findByItemName(request.paymentMethod())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final var transaction = new Transaction(TransactionStatus.PENDING, paymentMethod, customer, currentUser);

        final HashMap<UUID, Long> requestedProductQty = new HashMap<>();
        final Collection<UUID> requestedProductIDs = new ArrayList<>();

        request.productsToBeSold()
                .forEach(item -> {
                    requestedProductQty.put(item.itemId(), item.quantity());
                    requestedProductIDs.add(item.itemId());
                });

        final Iterable<Product> requestedProducts = this.productRepository.findAllById(requestedProductIDs);

        if (StreamSupport.stream(requestedProducts.spliterator(), false).count() != requestedProductIDs.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find wanted item to perform sale operation on");
        }

        final Set<TransactionProduct> transactionProducts = StreamSupport.stream(requestedProducts.spliterator(), false)
                .map(item -> new TransactionProduct(
                        transaction,
                        item,
                        requestedProductQty.get(item.getId()))
                ).collect(Collectors.toSet());

        transaction.setTransactionProducts(transactionProducts);

        final Transaction updatedTransaction = this.transactionRepository.save(transaction);

        this.transactionProductRepository.saveAll(transactionProducts);

        return new TransactionResponseItem(
                updatedTransaction.getId(),
                updatedTransaction.getStatus(),
                updatedTransaction.getPaymentMethod().getItemName(),
                TransactionService.mapResponseProductFromTransaction(transaction),
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
                ),
                new UserResponse(
                        transaction.getUser().getId(),
                        transaction.getUser().getUserName(),
                        transaction.getUser().getFirstName(),
                        transaction.getUser().getLastName(),
                        transaction.getUser().getEmail(),
                        transaction.getUser().getRole().getItemName(),
                        transaction.getUser().getStore().getStoreName(),
                        transaction.getUser().getCreatedAt(),
                        transaction.getUser().getUpdatedAt()
                ),

                updatedTransaction.getCreatedAt(),
                updatedTransaction.getUpdatedAt()
        );
    }
}
