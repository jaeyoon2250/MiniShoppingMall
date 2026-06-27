package kr.ac.kopo.yoon.minishoppingmall.service;

import kr.ac.kopo.yoon.minishoppingmall.entity.CartItem;
import kr.ac.kopo.yoon.minishoppingmall.entity.Member;
import kr.ac.kopo.yoon.minishoppingmall.entity.Order;
import kr.ac.kopo.yoon.minishoppingmall.entity.OrderItem;
import kr.ac.kopo.yoon.minishoppingmall.entity.Product;
import kr.ac.kopo.yoon.minishoppingmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;

    /**
     * 장바구니에 담긴 상품 전체로 주문을 생성하고, 성공하면 장바구니를 비웁니다.
     * 결제 연동은 없으며 주문 생성 = 결제 완료로 간단히 처리합니다.
     */
    @Transactional
    public Long createFromCart(Member member) {
        List<CartItem> items = cartService.findCartItems(member);
        if (items.isEmpty()) {
            throw new IllegalStateException("장바구니가 비어 있습니다.");
        }

        Order order = Order.builder().member(member).totalPrice(0).build();
        for (CartItem cartItem : items) {
            order.addItem(OrderItem.builder()
                    .product(cartItem.getProduct())
                    .quantity(cartItem.getQuantity())
                    .price(cartItem.getProduct().getDiscountedPrice())
                    .build());
        }
        recalculateTotal(order);
        orderRepository.save(order);

        cartService.clear(member);
        return order.getId();
    }

    /**
     * 장바구니를 거치지 않고 상품 1개를 바로 주문합니다("바로 구매하기").
     */
    @Transactional
    public Long createDirectOrder(Member member, Long productId, int quantity) {
        Product product = productService.findById(productId);

        Order order = Order.builder().member(member).totalPrice(0).build();
        order.addItem(OrderItem.builder()
                .product(product)
                .quantity(Math.max(quantity, 1))
                .price(product.getDiscountedPrice())
                .build());
        recalculateTotal(order);

        orderRepository.save(order);
        return order.getId();
    }

    private void recalculateTotal(Order order) {
        int total = order.getOrderItems().stream()
                .mapToInt(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.changeTotalPrice(total);
    }
}
