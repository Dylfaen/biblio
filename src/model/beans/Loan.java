package model.beans;

import model.Data;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Loan {
    private long id;
    private Copy copy;
    private Date startDate;
    private User user;
    private boolean isReturned;

    public Loan(Copy copy, Date startDate, User user) {
        this.id = Data.getInstance().getLoansSerialVersionUID();
        Data.getInstance().incrementLoansSerialVersionUID();
        this.copy = copy;
        this.startDate = startDate;
        this.user = user;
        this.isReturned = false;
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

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public JsonObjectBuilder toJson() {

        String startDateStr = "";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        startDateStr = formatter.format(this.startDate);

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("copy", this.copy.toJsonFull())
                .add("date", startDateStr)
                .add("user", this.user.toJson())
                .add("isReturned", this.isReturned);
    }
}
