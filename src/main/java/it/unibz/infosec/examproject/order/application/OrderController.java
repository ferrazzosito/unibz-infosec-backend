package it.unibz.infosec.examproject.order.application;

import it.unibz.infosec.examproject.order.domain.ManageOrders;
import it.unibz.infosec.examproject.order.domain.Order;
import it.unibz.infosec.examproject.order.domain.SearchOrders;
import it.unibz.infosec.examproject.product.domain.Product;
import it.unibz.infosec.examproject.user.domain.Role;
import it.unibz.infosec.examproject.user.domain.UserEntity;
import it.unibz.infosec.examproject.user.domain.UserRepository;
import it.unibz.infosec.examproject.util.RESTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/orders")
public class OrderController {

    private final ManageOrders manageOrders;
    private final SearchOrders searchOrders;
    private final UserRepository userRepository;

    //TODO: offer a method to check digital signature and approve order

    @Autowired
    public OrderController(ManageOrders manageOrders, SearchOrders searchOrders, UserRepository userRepository) {
        this.manageOrders = manageOrders;
        this.searchOrders = searchOrders;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") Long id) {
        return manageOrders.readOrder(id);
    }

    @PostMapping("/create")
    public Order createNewOrder(@RequestBody CreateOrderDTO dto) {
        final UserEntity loggedUser = RESTUtils.getLoggedUser(userRepository);
        if (loggedUser.getRole() != Role.CUSTOMER) {
            throw new IllegalArgumentException("Wrong user type for this operation");
        }
        return manageOrders.createOrder(dto.getProductId(), loggedUser.getId());
    }

    @DeleteMapping("/delete/{id}")
    public Order deleteOrder(@PathVariable("id") Long id)  {
        return manageOrders.deleteOrder(id, RESTUtils.getLoggedUser(userRepository).getId());
    }

    @GetMapping("/getAll")
    public List<Order> findAll(){
        return searchOrders.findAll();
    }

    @GetMapping("/mine")
    public List<Order> getAllForCustomer() {
        final UserEntity loggedUser = RESTUtils.getLoggedUser(userRepository);
        if (loggedUser.getRole() != Role.CUSTOMER) {
            throw new IllegalArgumentException("Wrong user type for this operation");
        }
        return manageOrders.getByCustomer(loggedUser.getId());
    }
}
