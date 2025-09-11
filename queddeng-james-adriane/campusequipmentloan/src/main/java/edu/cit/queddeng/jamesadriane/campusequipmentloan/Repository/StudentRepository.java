package edu.cit.queddeng.jamesadriane.campusequipmentloan.Repository;

import edu.cit.queddeng.jamesadriane.campusequipmentloan.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
