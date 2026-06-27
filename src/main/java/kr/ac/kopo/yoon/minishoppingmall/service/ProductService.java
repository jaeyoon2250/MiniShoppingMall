package kr.ac.kopo.yoon.minishoppingmall.service;

import kr.ac.kopo.yoon.minishoppingmall.entity.Product;
import kr.ac.kopo.yoon.minishoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * category가 비어있거나 "전체"이면 전체 상품을, 아니면 해당 카테고리 상품만 반환합니다.
     */
    public List<Product> findByCategory(String category) {
        if (category == null || category.isBlank() || category.equals("전체")) {
            return findAll();
        }
        return productRepository.findByCategory(category);
    }

    public List<String> findAllCategories() {
        return productRepository.findDistinctCategories();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("상품을 찾을 수 없습니다: " + id));
    }
}
