package priv.shen.hospitalregistersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.shen.hospitalregistersystem.entity.User;

public interface UserRepository extends JpaRepository<User,String> {
}
