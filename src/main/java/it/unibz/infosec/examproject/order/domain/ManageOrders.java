package it.unibz.infosec.examproject.order.domain;

import it.unibz.infosec.examproject.product.domain.Product;
import it.unibz.infosec.examproject.product.domain.ProductRepository;
import it.unibz.infosec.examproject.product.domain.SearchProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManageOrders {

    private OrderRepository orderRepository;
    private SearchOrders searchOrders;

    @Autowired
    public ManageOrders(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private Order validateOrder (Long id) {

        Optional<Order> maybeOrder = orderRepository.findById(id);

        if(maybeOrder.isEmpty())
            throw new IllegalArgumentException("Order with id '" + id + "' does not exist yet!");

        return maybeOrder.get();
    }

    public Order createOrder(Long productId, Long clientId) {

        return orderRepository.save(new Order(productId, clientId));
    }

    public Order readOrder(Long id) {

        Order order = validateOrder(id);

        return order;

    }

    public Order deleteOrder (Long id) {

        Order order = validateOrder(id);

        orderRepository.delete(order);

        return order;
    }

}
