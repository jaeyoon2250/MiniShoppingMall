package kr.ac.kopo.yoon.minishoppingmall.config;

import kr.ac.kopo.yoon.minishoppingmall.entity.Product;
import kr.ac.kopo.yoon.minishoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 애플리케이션 시작 시 상품 테이블이 비어 있으면 기존 UI 화면에서 쓰던 더미 상품 8개를
 * 그대로 DB에 시드 데이터로 넣어줍니다. (H2 인메모리 DB라 재시작 시마다 다시 채워집니다.)
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        if (productRepository.count() > 0) {
            return;
        }

        productRepository.save(Product.builder()
                .name("오버사이즈 코튼 셔츠").price(49750).discountRate(20)
                .category("의류").tag("MINIMALL BASIC").stock(50).build());
        productRepository.save(Product.builder()
                .name("스트레이트 데님 팬츠").price(59000).discountRate(null)
                .category("의류").tag("MINIMALL DENIM").stock(40).build());
        productRepository.save(Product.builder()
                .name("레더 스니커즈").price(99000).discountRate(10)
                .category("신발").tag("MINIMALL SHOES").stock(30).build());
        productRepository.save(Product.builder()
                .name("미니멀 크로스백").price(68000).discountRate(null)
                .category("가방").tag("MINIMALL BAG").stock(35).build());
        productRepository.save(Product.builder()
                .name("하프넥 니트").price(45000).discountRate(null)
                .category("의류").tag("MINIMALL KNIT").stock(45).build());
        productRepository.save(Product.builder()
                .name("실버 체인 목걸이").price(30000).discountRate(15)
                .category("액세서리").tag("MINIMALL ACC").stock(60).build());
        productRepository.save(Product.builder()
                .name("울 블렌드 코트").price(128000).discountRate(null)
                .category("의류").tag("MINIMALL OUTER").stock(20).build());
        productRepository.save(Product.builder()
                .name("컬러 포인트 양말 3종").price(12000).discountRate(null)
                .category("라이프").tag("MINIMALL SOCKS").stock(100).build());
        productRepository.save(Product.builder()
                .name("와이드 슬랙스").price(52000).discountRate(10)
                .category("의류").tag("MINIMALL BASIC").stock(38).build());
        productRepository.save(Product.builder()
                .name("후드 집업 재킷").price(76000).discountRate(null)
                .category("의류").tag("MINIMALL OUTER").stock(25).build());
        productRepository.save(Product.builder()
                .name("캔버스 스니커즈").price(65000).discountRate(null)
                .category("신발").tag("MINIMALL SHOES").stock(42).build());
        productRepository.save(Product.builder()
                .name("첼시 부츠").price(119000).discountRate(20)
                .category("신발").tag("MINIMALL SHOES").stock(18).build());
        productRepository.save(Product.builder()
                .name("러닝 스니커즈").price(89000).discountRate(null)
                .category("신발").tag("MINIMALL SHOES").stock(33).build());
        productRepository.save(Product.builder()
                .name("미니 버킷백").price(72000).discountRate(10)
                .category("가방").tag("MINIMALL BAG").stock(22).build());
        productRepository.save(Product.builder()
                .name("페이크 레더 토트백").price(89000).discountRate(null)
                .category("가방").tag("MINIMALL BAG").stock(27).build());
        productRepository.save(Product.builder()
                .name("캔버스 에코백").price(25000).discountRate(null)
                .category("가방").tag("MINIMALL BAG").stock(70).build());
        productRepository.save(Product.builder()
                .name("무선 이어폰 케이스").price(18000).discountRate(null)
                .category("액세서리").tag("MINIMALL ACC").stock(55).build());
        productRepository.save(Product.builder()
                .name("가죽 벨트").price(35000).discountRate(20)
                .category("액세서리").tag("MINIMALL ACC").stock(40).build());
        productRepository.save(Product.builder()
                .name("머그컵 세트").price(21000).discountRate(null)
                .category("라이프").tag("MINIMALL LIFE").stock(60).build());
        productRepository.save(Product.builder()
                .name("디퓨저").price(32000).discountRate(15)
                .category("라이프").tag("MINIMALL LIFE").stock(34).build());
    }
}
