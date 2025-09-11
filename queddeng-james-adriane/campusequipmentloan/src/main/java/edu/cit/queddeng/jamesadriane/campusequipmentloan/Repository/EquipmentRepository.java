package edu.cit.queddeng.jamesadriane.campusequipmentloan.Repository;

import edu.cit.queddeng.jamesadriane.campusequipmentloan.Entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByAvailableTrue();
}
