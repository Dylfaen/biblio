package model.beans;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private static long serialVersionUID = 1L;
    private long id;
    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private Date birthdate;
    private String address;
    private boolean isAdmin;

    public User(String username, String password, String lastname, String firstname, Date birthdate, String address, boolean isAdmin) {
        this.id = serialVersionUID;
        serialVersionUID++;

        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.address = address;
        this.isAdmin = isAdmin;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        if(id < serialVersionUID) {
            this.id = id;
        } else {
            serialVersionUID = id+1;
            this.id = id;
        }
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }
    public String getAddress() {
        return address;
    }
    public String getLastname() {
        return lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public JsonObjectBuilder toJson() {

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("username", this.username)
                .add("lastname", this.lastname)
                .add("firstname", this.firstname)
                .add("birthdate", this.birthdate.toString())
                .add("address", this.address)
                .add("isAdmin", this.isAdmin);
    }
}
