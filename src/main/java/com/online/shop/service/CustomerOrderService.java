package com.online.shop.service;

import com.online.shop.dto.CustomerOrderDto;
import com.online.shop.dto.UserDetailsDto;
import com.online.shop.entities.*;
import com.online.shop.mapper.CustomerOrderMapper;
import com.online.shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ChosenProductRepository chosenProductRepository;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerOrderMapper customerOrderMapper;

    public void addCustomerOrder(String loggedInUserEmail, String shippingAddress) {
        Optional<User> optionalUser = userRepository.findByEmail(loggedInUserEmail);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserEmail(loggedInUserEmail);
        User user = optionalUser.get();

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setUser(user);
        if (shippingAddress.isBlank()) {
            customerOrder.setShippingAddress(user.getAddress());
        } else {
            customerOrder.setShippingAddress(shippingAddress);
        }
        customerOrderRepository.save(customerOrder);

        for (ChosenProduct chosenProduct : shoppingCart.getChosenProducts()) {
            updateShoppingCart(customerOrder, chosenProduct);
            updateStock(chosenProduct);
        }

    }

    private void updateShoppingCart(CustomerOrder customerOrder, ChosenProduct chosenProduct) {
        chosenProduct.setShoppingCart(null);
        chosenProduct.setCustomerOrder(customerOrder);
        chosenProductRepository.save(chosenProduct);
    }

    private void updateStock(ChosenProduct chosenProduct) {
        Integer orderedQuantity = chosenProduct.getChosenQuantity();
        Product product = chosenProduct.getProduct();
        product.setQuantity(product.getQuantity() - orderedQuantity);
        productRepository.save(product);
    }

    public List<CustomerOrderDto> getAllCustomerOrderDtos() {
        List<CustomerOrder> allCustomerOrders = customerOrderRepository.findAll();
        List<CustomerOrderDto> customerOrderDtoList = new ArrayList<>();
        for (CustomerOrder customerOrder : allCustomerOrders) {
            CustomerOrderDto customerOrderDto = customerOrderMapper.map(customerOrder);
            customerOrderDtoList.add(customerOrderDto);
        }
        return customerOrderDtoList;
    }


}

