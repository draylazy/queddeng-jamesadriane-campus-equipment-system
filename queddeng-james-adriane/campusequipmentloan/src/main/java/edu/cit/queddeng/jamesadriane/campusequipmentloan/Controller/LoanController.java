package edu.cit.queddeng.jamesadriane.campusequipmentloan.Controller;

import edu.cit.queddeng.jamesadriane.campusequipmentloan.Entity.Loan;
import edu.cit.queddeng.jamesadriane.campusequipmentloan.Service.LoanService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public Loan createLoan(@RequestParam Long equipmentId, @RequestParam Long studentId) {
        return loanService.createLoan(equipmentId, studentId);
    }

    @PostMapping("/{id}/return")
    public Loan returnLoan(@PathVariable Long id) {
        return loanService.returnLoan(id);
    }

    @GetMapping("/{id}/penalty")
    public long getPenalty(@PathVariable Long id) {
        Loan loan = loanService.returnLoan(id);
        return loanService.calculatePenalty(loan);
    }
}
