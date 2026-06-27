package kr.ac.kopo.yoon.minishoppingmall.service;

import kr.ac.kopo.yoon.minishoppingmall.entity.CartItem;
import kr.ac.kopo.yoon.minishoppingmall.entity.Member;
import kr.ac.kopo.yoon.minishoppingmall.entity.Product;
import kr.ac.kopo.yoon.minishoppingmall.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public List<CartItem> findCartItems(Member member) {
        return cartItemRepository.findByMember(member);
    }

    public long countItems(Member member) {
        if (member == null) {
            return 0;
        }
        return cartItemRepository.countByMember(member);
    }

    @Transactional
    public void addToCart(Member member, Long productId, int quantity) {
        Product product = productService.findById(productId);
        cartItemRepository.findByMemberAndProduct(member, product)
                .ifPresentOrElse(
                        item -> item.increase(quantity),
                        () -> cartItemRepository.save(CartItem.builder()
                                .member(member)
                                .product(product)
                                .quantity(quantity)
                                .build())
                );
    }

    @Transactional
    public void changeQuantity(Member member, Long cartItemId, int quantity) {
        getOwnedItem(member, cartItemId).changeQuantity(quantity);
    }

    @Transactional
    public void remove(Member member, Long cartItemId) {
        cartItemRepository.delete(getOwnedItem(member, cartItemId));
    }

    @Transactional
    public void clear(Member member) {
        cartItemRepository.deleteByMember(member);
    }

    private CartItem getOwnedItem(Member member, Long cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 항목을 찾을 수 없습니다."));
        if (!item.getMember().getId().equals(member.getId())) {
            throw new SecurityException("본인의 장바구니만 수정할 수 있습니다.");
        }
        return item;
    }
}
