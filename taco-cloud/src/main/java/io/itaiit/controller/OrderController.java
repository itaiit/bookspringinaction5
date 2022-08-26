package io.itaiit.controller;

import io.itaiit.data.OrderRepository;
import io.itaiit.domain.Order;
import io.itaiit.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author itaiit
 * @date 2022/8/23 17:41
 */
@Slf4j
@Controller
@RequestMapping("orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@SessionAttribute("order") Order order, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info("authentication uesr: " + user);
        order.setUser(user);
        order.setStreet(user.getStreet());
        order.setCity(user.getCity());
        order.setState(user.getState());
        order.setZip(user.getZip());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(Order order, SessionStatus sessionStatus) {
        log.info("Order submitted: " + order);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
