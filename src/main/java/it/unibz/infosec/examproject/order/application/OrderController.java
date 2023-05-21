package it.unibz.infosec.examproject.order.application;

import it.unibz.infosec.examproject.order.domain.ManageOrders;
import it.unibz.infosec.examproject.order.domain.Order;
import it.unibz.infosec.examproject.order.domain.SearchOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/orders")
public class OrderController {

    private final ManageOrders manageOrders;
    private final SearchOrders searchOrders;

    //TODO: offer a method to check digital signature and approve order

    @Autowired
    public OrderController (ManageOrders manageOrders, SearchOrders searchOrders) {
        this.manageOrders = manageOrders;
        this.searchOrders = searchOrders;
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") Long id) {
        return manageOrders.readOrder(id);
    }

    @PostMapping("/create")
    public Order createNewOrder(@RequestBody CreateOrderDTO dto) {
        return manageOrders.createOrder(dto.getProductId(), dto.getClientId());
    }

    @DeleteMapping("/delete/{id}")
    public Order deleteOrder(@PathVariable("id") Long id)  {
        return manageOrders.deleteOrder(id);
    }

    @GetMapping("/getAll")
    public List<Order> findAll(){
        return searchOrders.findAll();
    }
}
