package edu.cit.queddeng.jamesadriane.campusequipmentloan.Service;

import edu.cit.queddeng.jamesadriane.campusequipmentloan.Entity.*;
import edu.cit.queddeng.jamesadriane.campusequipmentloan.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final EquipmentRepository equipmentRepository;
    private final StudentRepository studentRepository;

    public LoanService(LoanRepository loanRepository, EquipmentRepository equipmentRepository, StudentRepository studentRepository) {
        this.loanRepository = loanRepository;
        this.equipmentRepository = equipmentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Loan createLoan(Long equipmentId, Long studentId) {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Business Rule: Max 2 ACTIVE loans per student
        List<Loan> activeLoans = loanRepository.findByStudentAndStatus(student, "ACTIVE");
        if (activeLoans.size() >= 2) {
            throw new RuntimeException("Max 2 active loans per student");
        }

        if (!equipment.isAvailable()) {
            throw new RuntimeException("Equipment is not available");
        }

        LocalDate startDate = LocalDate.now();
        LocalDate dueDate = startDate.plusDays(7);

        Loan loan = new Loan(equipment, student, startDate, dueDate, "ACTIVE");
        equipment.setAvailable(false);

        equipmentRepository.save(equipment);
        return loanRepository.save(loan);
    }

    @Transactional
    public Loan returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(LocalDate.now());
        loan.setStatus("RETURNED");

        // Mark equipment available again
        Equipment equipment = loan.getEquipment();
        equipment.setAvailable(true);
        equipmentRepository.save(equipment);

        return loanRepository.save(loan);
    }

    public long calculatePenalty(Loan loan) {
        if (loan.getReturnDate() == null || !loan.getReturnDate().isAfter(loan.getDueDate())) {
            return 0;
        }
        long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
        return daysLate * 50; // ₱50/day penalty
    }
}
