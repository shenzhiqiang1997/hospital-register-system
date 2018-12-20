package priv.shen.hospitalregistersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.shen.hospitalregistersystem.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByTelNum(String telNum);
}
