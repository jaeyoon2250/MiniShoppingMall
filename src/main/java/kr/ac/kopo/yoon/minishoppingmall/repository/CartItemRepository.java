package kr.ac.kopo.yoon.minishoppingmall.repository;

import kr.ac.kopo.yoon.minishoppingmall.entity.CartItem;
import kr.ac.kopo.yoon.minishoppingmall.entity.Member;
import kr.ac.kopo.yoon.minishoppingmall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByMember(Member member);
    Optional<CartItem> findByMemberAndProduct(Member member, Product product);
    long countByMember(Member member);
    void deleteByMember(Member member);
}
