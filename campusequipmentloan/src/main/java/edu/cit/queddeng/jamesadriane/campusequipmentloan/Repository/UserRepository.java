package edu.cit.queddeng.jamesadriane.campusequipmentloan.Repository;

import edu.cit.queddeng.jamesadriane.campusequipmentloan.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}