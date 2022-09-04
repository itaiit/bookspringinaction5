package io.itaiit.controller;

import io.itaiit.data.OrderRepository;
import io.itaiit.domain.Order;
import io.itaiit.domain.User;
import io.itaiit.message.OrderMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

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

    @Autowired
    private OrderMessagingService orderMessagingService;

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

        orderMessagingService.sendOrder(order);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        List<Order> orders = orderRepository.findByUserOrderByPlaceAtDesc(user);
        model.addAttribute("orders", orders);
        return "orderList";
    }

}
