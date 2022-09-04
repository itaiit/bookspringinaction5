package io.itaiit.message.controller;

import io.itaiit.domain.Order;
import io.itaiit.message.RabbitOrderReceiver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderReceiverController {

    private final RabbitOrderReceiver orderReceiver;

    @GetMapping("/receive")
    public String receiveOrder(Model model) {
        Order order = orderReceiver.receiveOrder();
        if (order != null) {
            model.addAttribute("order", order);
            return "receiveOrder";
        }
        return "noOrder";
    }


}
