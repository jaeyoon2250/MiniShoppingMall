package kr.ac.kopo.yoon.minishoppingmall.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 엔티티입니다.
 * price는 정가, discountRate는 할인율(%)이며 null이면 할인이 없는 상품입니다.
 */
@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer price;

    private Integer discountRate;

    @Column(length = 50)
    private String category;

    @Column(length = 50)
    private String tag;

    @Column(nullable = false)
    private Integer stock;

    @Builder
    public Product(String name, Integer price, Integer discountRate, String category, String tag, Integer stock) {
        this.name = name;
        this.price = price;
        this.discountRate = discountRate;
        this.category = category;
        this.tag = tag;
        this.stock = stock;
    }

    /**
     * 할인이 적용된 실제 판매가를 계산합니다. 할인율이 없으면 정가를 그대로 반환합니다.
     */
    public int getDiscountedPrice() {
        if (discountRate == null || discountRate == 0) {
            return price;
        }
        return price - (price * discountRate / 100);
    }
}
