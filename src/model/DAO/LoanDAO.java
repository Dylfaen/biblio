package model.DAO;

import model.Data;
import model.beans.Book;
import model.beans.Copy;
import model.beans.Loan;
import model.beans.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class LoanDAO {

    public Loan getLoan(User user) {
        Data data = Data.getInstance();
        ArrayList<Loan> loans = data.getLoans();

        Loan loan = null;
        Boolean found = false;
        Iterator it = loans.listIterator();
        while(it.hasNext() && !found) {
            Loan temp_loan = (Loan) it.next();
            User temp_user = temp_loan.getUser();
            if(temp_user.equals(user)) {
                loan = temp_loan;
                found = true;
            }
        }
        return loan;
    }

    public Loan getLoan(Copy copy) {
        Data data = Data.getInstance();
        ArrayList<Loan> loans = data.getLoans();

        Loan loan = null;
        Boolean found = false;
        Iterator it = loans.listIterator();
        while(it.hasNext() && !found) {
            Loan temp_loan = (Loan) it.next();
            Copy temp_copy = temp_loan.getCopy();
            if(temp_copy.equals(copy)) {
                loan = temp_loan;
                found = true;
            }
        }
        return loan;
    }

    public void createLoan(Loan loan) throws FileNotFoundException {
        Data data = Data.getInstance();
        data.getLoans().add(loan);
        try {
            data.saveInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
