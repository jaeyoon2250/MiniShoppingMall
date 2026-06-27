package kr.ac.kopo.yoon.minishoppingmall.controller;

import kr.ac.kopo.yoon.minishoppingmall.security.CustomUserDetails;
import kr.ac.kopo.yoon.minishoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public String createFromCart(@AuthenticationPrincipal CustomUserDetails principal) {
        Long orderId = orderService.createFromCart(principal.getMember());
        return "redirect:/orders/" + orderId + "/complete";
    }

    @PostMapping("/orders/buy-now")
    public String buyNow(@AuthenticationPrincipal CustomUserDetails principal,
                          @RequestParam Long productId,
                          @RequestParam(defaultValue = "1") int quantity) {
        Long orderId = orderService.createDirectOrder(principal.getMember(), productId, quantity);
        return "redirect:/orders/" + orderId + "/complete";
    }

    @GetMapping("/orders/{id}/complete")
    public String complete(@PathVariable Long id, Model model) {
        model.addAttribute("orderId", id);
        return "order-complete";
    }
}
