package kr.ac.kopo.yoon.minishoppingmall.controller;

import kr.ac.kopo.yoon.minishoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 메인(상품 목록) 화면 컨트롤러입니다. 상품 데이터는 ProductService를 통해 DB에서 조회합니다.
 */
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ProductService productService;

    @GetMapping("/")
    public String index(@RequestParam(required = false) String category, Model model) {
        model.addAttribute("products", productService.findByCategory(category));
        model.addAttribute("categories", productService.findAllCategories());
        model.addAttribute("selectedCategory", (category == null || category.isBlank()) ? "전체" : category);
        return "index";
    }
}
