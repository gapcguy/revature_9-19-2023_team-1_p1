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

@Service                    // Mark this class as a Spring service component.
public class LoanService {

    // Declare a private variable to hold an instance of the LoanDAO
    private LoanDAO lDAO;

    /**
     * Constructor with dependency injection of LoanDAO and AccountService.
     * @param loanDAO an instance of the LoanDAO class.
     */
    @Autowired
    public LoanService(LoanDAO loanDAO)  { this.lDAO = loanDAO; }

    // Method to find all loans, filtered by the user's role.
    public String findAll(AccountService accountService){
        // Get the current authorized user and assign it as the current user.
        User currUser = AuthController.getUser();

        // If the current user is a customer, instantiate a new ArrayList and feed it all associative bank accounts.
        List<Loan> result = new ArrayList<>();
        if(currUser.getRole()==(User.USER)){
            result = new ArrayList<>();
            List<Account> accounts = accountService.findAll();

            // Gather all transactions.
            result = lDAO.findLoanByAccountList(accounts);

            // Create a header
            String statement = "=================    " + currUser.getFirstName().toUpperCase()
                    + " "
                    +currUser.getLastName().toUpperCase()+ "'S LOANS     ===============\n";

            // Iterate through the list of transactions on the account and build the transaction table.
            for(int i = 0; i < result.size(); i++) { statement += result.get(i).toString(); }
            // return the completed statement.
            return statement;
        // if the Current user is either an employee or manager, get all loans.
        } else if (currUser.getRole() == ( User.EMPLOYEE ) ||
                   currUser.getRole() == ( User.MANAGER ) )
        { // Create a header
            result = lDAO.findAll();
            String statement = "=================    ALL LOANS     ===============\n";

            // Iterate through the list of transactions on the account and build the transaction table.
            for(int i = 0; i < result.size(); i++) { statement += result.get(i).toString(); }
            // return the completed statement.
            return statement; }
        // If the current user has no active sessions, don't return anything.
        else { return null; }
    }

    /**
     * Save the loan.
     * @param l is an instance of the Loan class.
     * @return the saved loan.
     */
    public Loan save(Loan l){ return lDAO.save(l); }

    /**
     * Finds a loan by its id.
     * @param id is the id of the loan
     * @return the loan, referenced by its id.
     */
    public Loan findById(int id){ return lDAO.getReferenceById(id); }

    /*
    public List<Loan> findByApproved(Boolean approved) {

        Optional<User> u = lDAO.findBy();

        // If the returned user is present in the optional,
        if(u.isPresent()) { return u.get(); }
        else              { throw new IllegalArgumentException(); }
    }*/

    /**
     * Performs a full update to a loan
     * @param u is the updated loan instance
     * @return the saved loan after being updated, or an exception stating the loan is not present.
     */
    public Loan updateEntireLoan(Loan u) {
        // Updating in spring data uses the save() method. It's not just for inserts...
        // if save() is used on an existing record, it will update the record instead of creating a new one.
        Optional<Loan> loanFromDataBase = lDAO.findById(u.getLoanId());
        if(loanFromDataBase.isPresent()) { return lDAO.save(u); }
        else { throw new IllegalArgumentException("Loan not found."); }
    }

    /**
     * selects a loan to Close out (Delete) by its id.
     * @param id the ID of the loan to close out.
     * @return the deleted loan.
     */
    public Loan deleteById(int id) {
        // Check to see if a loan is present by the specified ID.
        Optional<Loan> loanOptional = lDAO.findById(id);

        // If it is not present, don't return anything...
        if (!loanOptional.isPresent()) { return null; }

        // ... otherwise, get the loan...
        Loan loan = loanOptional.get();

        // then delete it...
        lDAO.deleteById(id);

        // and return the loan that's been deleted.
        return loan;
    }

    /**
     * Creates a request for a loan
     * @param currentUser the current user.
     * @param account     the account the loan will be applied to
     * @param loanAmount  the amount of the loan.
     * @return            the loan or a thrown exception.
     */
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

    /**
     * Allows for applying for a loan
     * @param lDTO          is an instance of the Loan Data Transfer Object
     * @param account       is the account for which the loan will be requested.
     * @return              the loan that's been applied for.
     * @throws Exception    three, depending on whether there's an active user session, whether the account is a user,
     *                      and if there was an error applying for the loan.
     */
    public Loan apply(LoanDTO lDTO, Account account) throws Exception {
        User currentUser = AuthController.getUser();
        // If there is no user session set, deny access.
        if (currentUser == null) {
            throw new Exception("Must be logged in");
        }





        // Second check - See Use case diagram.
        // It is expected that the user is the only role that can request a loan.
        if (currentUser.getRole() != 'U') { throw new Exception("Only users can apply for loan"); }

        Integer accountId = lDTO.getAccountId();
        BigDecimal loanAmount = lDTO.getLoanAmount();

        Loan newLoan = createLoanRequest(currentUser, account, loanAmount);

        if (newLoan != null) {
            return newLoan;
        } else {
            throw new Exception(" Unknown Error occurred While Applying");
        }
    }

    /**
     * A helper method for acceptLoan.
     * @param user      is a user object
     * @param loanId    is the id of the loan
     * @return          a true/false value indicating that the loan has/has not been accepted
     */
    private boolean acceptHelper(User user, int loanId) {
        // Locate the loan by its id.
        Optional<Loan> loanOptional = lDAO.findById(loanId);

        // If the loan can be found,
        if (loanOptional.isPresent()) {
            // ... retrieve it.
            Loan loan = loanOptional.get();

            // With the loan retrieved, check to see if the loan is already approved.
            if (loan.isApproved()) {
                return false; // If it's already approved, it does not need to be re-approved.
            }

            // Set the loan approval flag.
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

    /**
     * the loan acceptance method.
     * @param loanId             is the ID of the loan
     * @param transactionService an instance of the transaction service.
     * @return                   a new transaction for the loan.
     * @throws Exception         Indicates that a user session must be present, that the user role must be
     *                           a manager, that the transaction can be created, and that the loan is accepted.
     */
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
                    findById(loanId).getRecipientAccount(),
                    findById(loanId).getLoanAmount()
            );

            if (transaction != null) { return transaction; }
            else { throw new Exception("Failed to create transaction"); }
        } else { throw new Exception("Failed to accept loan"); }
    }
}