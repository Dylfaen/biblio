package model.beans;


import model.Data;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Copy {
    private long id;
    private long idOeuvre;

    public Copy(long idOeuvre) {
        this.id = Data.getInstance().getCopiesSerialVersionUID();
        Data.getInstance().incrementCopiesSerialVersionUID();
        this.idOeuvre = idOeuvre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(id < Data.getInstance().getCopiesSerialVersionUID()) {
            this.id = id;
        } else {
            Data.getInstance().setCopiesSerialVersionUID(id+1);
            this.id = id;
        }
    }

    public long getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(long idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public JsonObjectBuilder toJson() {

        return Json.createObjectBuilder()
                .add("id", this.id)
                .add("idOeuvre", this.idOeuvre);
    }
}
