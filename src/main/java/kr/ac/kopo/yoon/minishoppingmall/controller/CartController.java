package kr.ac.kopo.yoon.minishoppingmall.controller;

import kr.ac.kopo.yoon.minishoppingmall.entity.CartItem;
import kr.ac.kopo.yoon.minishoppingmall.security.CustomUserDetails;
import kr.ac.kopo.yoon.minishoppingmall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart")
    public String cart(@AuthenticationPrincipal CustomUserDetails principal, Model model) {
        var member = principal.getMember();
        List<CartItem> items = cartService.findCartItems(member);
        int totalPrice = items.stream().mapToInt(CartItem::getTotalPrice).sum();
        model.addAttribute("cartItems", items);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @PostMapping("/cart/add")
    public String add(@AuthenticationPrincipal CustomUserDetails principal,
                       @RequestParam Long productId,
                       @RequestParam(defaultValue = "1") int quantity) {
        cartService.addToCart(principal.getMember(), productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/items/{id}")
    public String update(@AuthenticationPrincipal CustomUserDetails principal,
                          @PathVariable Long id,
                          @RequestParam int quantity) {
        cartService.changeQuantity(principal.getMember(), id, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/items/{id}/delete")
    public String delete(@AuthenticationPrincipal CustomUserDetails principal,
                          @PathVariable Long id) {
        cartService.remove(principal.getMember(), id);
        return "redirect:/cart";
    }
}
