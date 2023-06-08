package it.unibz.infosec.examproject.order.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchOrders {

    private final OrderRepository orderRepository;

    @Autowired
    public SearchOrders(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findById(Long id) {
        final Optional<Order> searchedOrder = orderRepository.findById(id);
        if (searchedOrder.isEmpty()) {
            throw new IllegalArgumentException("Order with id " + id + " is not present in the database");
        }
        return searchedOrder.get();
    }

    public List<Order> findAll() {
        List<Order> list = orderRepository.findAll();
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No orders in database");
        }
        return list;
    }
}
