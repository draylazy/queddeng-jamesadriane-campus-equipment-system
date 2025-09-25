package edu.cit.queddeng.jamesadriane.campusequipmentloan.Repository;

import edu.cit.queddeng.jamesadriane.campusequipmentloan.Entity.Loan;
import edu.cit.queddeng.jamesadriane.campusequipmentloan.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStudentAndStatus(Student student, String status);
}
