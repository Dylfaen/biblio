package model.DAO;

import controller.Util.CannotRemoveItemException;
import model.Data;
import model.beans.Book;
import model.beans.Copy;
import model.beans.Loan;
import model.beans.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class LoanDAO {

    public ArrayList<Loan> getLoans(User user) {
        Data data = Data.getInstance();
        ArrayList<Loan> loans = data.getLoans();

        ArrayList<Loan> myLoans = new ArrayList<>();

        for (Loan temp_loan : loans) {
            User temp_user = temp_loan.getUser();
            if (temp_user.equals(user)) {
                myLoans.add(temp_loan);
            }
        }
        return myLoans;
    }

    public ArrayList<Loan> getLoans(Book book) {
        ArrayList<Loan> loans = new ArrayList<>();

        for (Copy copy : book.getCopies()) {
            loans.addAll(getLoans(copy));
        }

        return loans;
    }

    public Loan getLoan(long id) {
        Data data = Data.getInstance();
        ArrayList<Loan> loans = data.getLoans();

        Loan loan = null;

        boolean found = false;

        Iterator it = loans.listIterator();


        while (it.hasNext() && !found) {
            Loan temp_loan = (Loan) it.next();
            if (id == temp_loan.getId()) {
                found = true;
                loan = temp_loan;
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
        while (it.hasNext() && !found) {
            Loan temp_loan = (Loan) it.next();
            Copy temp_copy = temp_loan.getCopy();
            if (temp_copy.equals(copy)) {
                loan = temp_loan;
                found = true;
            }
        }
        return loan;
    }

    public ArrayList<Loan> getLoans(Copy copy) {
        Data data = Data.getInstance();
        ArrayList<Loan> loans = data.getLoans();

        ArrayList<Loan> copyLoans = new ArrayList<>();
        Boolean found = false;
        Iterator it = loans.listIterator();
        while (it.hasNext()) {
            Loan temp_loan = (Loan) it.next();
            Copy temp_copy = temp_loan.getCopy();
            if (temp_copy.equals(copy)) {
                copyLoans.add(temp_loan);
            }
        }
        return copyLoans;
    }

    public void createLoan(Loan loan) {
        Data data = Data.getInstance();
        data.getLoans().add(loan);
        try {
            data.saveInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void returnLoan(long id) throws IOException {
        getLoan(id).setReturned(true);
        Data.getInstance().saveInstance();

    }

    public void removeLoan(Loan loan) throws CannotRemoveItemException, IOException {
        if(loan.isReturned()) {
            Data.getInstance().getLoans().remove(loan);
            Data.getInstance().saveInstance();
        } else {
            throw new CannotRemoveItemException("Can't remove unreturned loans");
        }

    }

    public void removeLoans(ArrayList<Loan> loans) throws IOException, CannotRemoveItemException {

        ArrayList<Loan> dataLoans = Data.getInstance().getLoans();

        for (Loan loan : loans) {
            removeLoan(loan);
        }
        Data.getInstance().saveInstance();

    }


}
