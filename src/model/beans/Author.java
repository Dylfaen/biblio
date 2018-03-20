package model.beans;
import model.Data;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.Date;


public class Author {

    private long id;
    private String lastname;
    private String firstname;
    private Date birthdate;
    private String nationality;

    public Author(String lastname, String firstname, Date birthdate, String nationality) {
        this.id = Data.getInstance().getAuthorsSerialVersionUID();
        Data.getInstance().incrementAuthorsSerialVersionUID();
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.nationality = nationality;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(id < Data.getInstance().getAuthorsSerialVersionUID()) {
            this.id = id;
        } else {
            Data.getInstance().setAuthorsSerialVersionUID(id+1);
            this.id = id;
        }
    }

    public String getLastname() {
        return this.lastname;
    }
    public String getFirstname(){
        return this.firstname;
    }
    public Date getBirthdate(){
        return this.birthdate;
    }
    public String getNationality(){
        return this.nationality;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public JsonObjectBuilder toJson() {
        String naissance = "";
        if(this.birthdate != null) {
            naissance = this.birthdate.toString();
        }

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("lastname", this.lastname)
                .add("firstname", this.firstname)
                .add("birthdate", naissance)
                .add("nationality", this.nationality);
    }
}
