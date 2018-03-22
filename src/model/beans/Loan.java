package model.beans;

import model.Data;

import java.util.Date;

public class Loan {
    private long id;
    private Copy copy;
    private Date startDate;
    private User user;

    public Loan(Copy copy, Date startDate, User user) {
        this.id = Data.getInstance().getLoansSerialVersionUID();
        Data.getInstance().incrementLoansSerialVersionUID();
        this.copy = copy;
        this.startDate = startDate;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(id < Data.getInstance().getLoansSerialVersionUID()) {
            this.id = id;
        } else {
            Data.getInstance().setLoansSerialVersionUID(id+1);
            this.id = id;
        }
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
