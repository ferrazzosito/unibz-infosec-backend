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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/sold")
    public List<Order> getSoldForVendor() {
        final UserEntity loggedUser = RESTUtils.getLoggedUser(userRepository);
        if (loggedUser.getRole() != Role.VENDOR) {
            throw new IllegalArgumentException("Wrong user type for this operation");
        }
        return manageOrders.getByVendor(loggedUser.getId());
    }

    @GetMapping("/approved")
    public List<Order> getApprovedForVendor() {
        final UserEntity loggedUser = RESTUtils.getLoggedUser(userRepository);
        if (loggedUser.getRole() != Role.VENDOR) {
            throw new IllegalArgumentException("Wrong user type for this operation");
        }
        return manageOrders.getApprovedByVendor(loggedUser.getId());
    }

    @GetMapping("/pending")
    public List<Order> getToBeApprovedForVendor() {
        final UserEntity loggedUser = RESTUtils.getLoggedUser(userRepository);
        if (loggedUser.getRole() != Role.VENDOR) {
            throw new IllegalArgumentException("Wrong user type for this operation");
        }
        return manageOrders.getToBeApprovedByVendor(loggedUser.getId());
    }

    @PostMapping("{id}/approve")
    public ResponseEntity<ApproveOrderResponseDTO> approveOrder(@PathVariable("id") Long id) {
        final UserEntity loggedUser = RESTUtils.getLoggedUser(userRepository);
        if (loggedUser.getRole() != Role.VENDOR) {
            throw new IllegalArgumentException("Wrong user type for this operation");
        }
        final Order order = manageOrders.readOrder(id);
        if (!order.getVendorId().equals(loggedUser.getId())) {
            throw new IllegalArgumentException("Only the issuer of an order can approve it");
        }
        if (order.isApproved()) {
            throw new IllegalArgumentException("This order has already been approved");
        }

        final Order approved;
        try {
            approved = manageOrders.approveOrder(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return new ResponseEntity<>(new ApproveOrderResponseDTO(
                true, approved.getId()), HttpStatus.OK);
    }
}
