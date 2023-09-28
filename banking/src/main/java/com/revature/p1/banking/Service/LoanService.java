package com.revature.p1.banking.Service;

import com.revature.p1.banking.DAO.LoanDAO;
import com.revature.p1.banking.DAO.TransactionDAO;
import com.revature.p1.banking.Models.Loan;
import com.revature.p1.banking.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private LoanDAO lDAO;
    @Autowired
    public LoanService(LoanDAO loanDAO)  { this.lDAO = loanDAO; }

    public List<Loan> findAll(){ return lDAO.findAll();  }
    public Loan save(Loan l){ return lDAO.save(l); }
    public Loan findById(Long id){ return lDAO.getReferenceById(id); }

    /*
    public List<Loan> findByApproved(Boolean approved) {

        Optional<User> u = lDAO.findBy();

        // If the returned user is present in the optional,
        if(u.isPresent()) { return u.get(); }
        else              { throw new IllegalArgumentException(); }
    }*/

    public Loan updateEntireLoan(Loan u) {
        // Updating in spring data uses the save() method. It's not just for inserts...
        // if save() is used on an existing record, it will update the record instead of creating a new one.
        Optional<Loan> loanFromDataBase = lDAO.findById(u.getLoanId());
        if(loanFromDataBase.isPresent()) { return lDAO.save(u); }
        else { throw new IllegalArgumentException("Loan not found."); }
    }

    public Loan deleteById(long id) {
        Optional<Loan> loanOptional = lDAO.findById(id);
        if (!loanOptional.isPresent()) { return null; }
        Loan loan = loanOptional.get();
        lDAO.deleteById(id);
        return loan;
    }

}
