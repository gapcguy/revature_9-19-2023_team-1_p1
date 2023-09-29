package com.revature.p1.banking.Service;

import com.revature.p1.banking.Controller.AuthController;
import com.revature.p1.banking.DAO.LoanDAO;
import com.revature.p1.banking.DTO.LoanDTO;
import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.Loan;
import com.revature.p1.banking.Models.Transaction;
import com.revature.p1.banking.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private LoanDAO lDAO;
    @Autowired
    public LoanService(LoanDAO loanDAO,AccountService aServ)  {
        this.lDAO = loanDAO; }

    public List<Loan> findAll(AccountService accountService){
        User currUser = AuthController.getUser();
        if(currUser.getRole()==(User.USER)){
            List<Loan> result = new ArrayList<Loan>();
            List<Account> accounts = accountService.findAll();
            for(int i = 0;i<accounts.size();i++){
                Loan loan = new Loan();
                loan.setRecipientAccount(accounts.get(i));
                Example<Loan> query = Example.of(loan);
                result.addAll(lDAO.findAll(query));
            }
            return result;
        }else{
            return lDAO.findAll();
        }
    }
    public Loan save(Loan l){ return lDAO.save(l); }
    public Loan findById(int id){ return lDAO.getReferenceById(id); }

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

    public Loan deleteById(int id) {
        Optional<Loan> loanOptional = lDAO.findById(id);
        if (!loanOptional.isPresent()) { return null; }
        Loan loan = loanOptional.get();
        lDAO.deleteById(id);
        return loan;
    }



    public Loan createLoanRequest(User currentUser, Account account, BigDecimal loanAmount) {



        // Create the newLoan object.
        Loan newLoan = new Loan();
        // Set the newLoan parameters.
        newLoan.setRecipientAccount(account);
        newLoan.setLoanAmount(loanAmount);
        newLoan.setLoanDateTime(new Timestamp(System.currentTimeMillis()));
        newLoan.setApproved(false);

        try {
            // Save the new loan request to the database.
            newLoan = lDAO.save(newLoan);

            // Return the newLoan object
            return newLoan;
        } catch (Exception e) {
            // Handle any exceptions.
            e.printStackTrace();
            // Finally, return null to indicate a failure.
            return null;
        }
    }


    public Loan apply(LoanDTO lDTO, Account account) throws Exception {
        User currentUser = AuthController.getUser();
        // If there is no user session set, deny access.
        if (currentUser == null) {
            throw new Exception("Must be logged in");
        }

        // Second check - See Use case diagram.
        // It is expected that the user is the only role that can request a loan.
        if (currentUser.getRole() != 'U') {
            throw new Exception("Only users can apply for loan");
        }
        Integer accountId = Math.toIntExact(lDTO.getAccountId());
        BigDecimal loanAmount = lDTO.getLoanAmount();

        Loan newLoan = createLoanRequest(currentUser, account, loanAmount);

        if (newLoan != null) {
            return newLoan;
        } else {
            throw new Exception(" Unknown Error Ocurred While Applying");
        }
    }

    private boolean acceptHelper(User user, int loanId) {
        Optional<Loan> loanOptional = lDAO.findById(loanId);

        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();

            // Check to see if the loan is already approved.
            if (loan.isApproved()) {
                return false; // LOAN WAS ALREADY APPROVED. WHY ARE YOU DOING THIS?
            }

            // Set the loan to be approved.
            loan.setApproved(true);
            // Set who approved the loan.
            loan.setApprovedByUser(user);
            // Now save the loan update.
            lDAO.save(loan);

            return true; // Loan has been successfully approved
        } else {
            return false; // LOAN WITH THE SPECIFIED ID HAS NOT BEEN FOUND.
        }

    }

    public Transaction acceptLoan(int loanId, TransactionService transactionService) throws Exception {
        User currentUser = AuthController.getUser();
        if (currentUser == null) {
            throw new Exception("You must be logged in to do that.");
        }

        // See Use case diagram for role permissions. The User *must* be a manager or employee to proceed
        if (currentUser.getRole() != User.MANAGER || currentUser.getRole() != User.EMPLOYEE) {
            throw new Exception("You must be a Manager to perform this action");
        }

        if (acceptHelper(currentUser, loanId)) {
            Transaction transaction = transactionService.createTransaction(
                    loanId,
                    findById(loanId).getRecipientAccount().getAccountId(),
                    findById(loanId).getLoanAmount()
            );

            if (transaction != null) { return transaction; }
            else { throw new Exception("Failed to create transaction"); }
        } else { throw new Exception("Failed to accept loan"); }
    }
    }

