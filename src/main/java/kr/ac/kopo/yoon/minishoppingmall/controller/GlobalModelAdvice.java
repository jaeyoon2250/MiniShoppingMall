package kr.ac.kopo.yoon.minishoppingmall.controller;

import kr.ac.kopo.yoon.minishoppingmall.entity.Member;
import kr.ac.kopo.yoon.minishoppingmall.security.CustomUserDetails;
import kr.ac.kopo.yoon.minishoppingmall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 모든 화면(헤더 fragment 포함)에서 로그인 여부/장바구니 개수를 쓸 수 있도록
 * 공통 모델 속성으로 주입합니다.
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAdvice {

    private final CartService cartService;

    @ModelAttribute("currentMember")
    public Member currentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getMember();
        }
        return null;
    }

    @ModelAttribute("cartCount")
    public long cartCount() {
        Member member = currentMember();
        return cartService.countItems(member);
    }
}
