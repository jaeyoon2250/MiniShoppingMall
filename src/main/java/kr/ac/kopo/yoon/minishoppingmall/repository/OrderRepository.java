package kr.ac.kopo.yoon.minishoppingmall.repository;

import kr.ac.kopo.yoon.minishoppingmall.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
