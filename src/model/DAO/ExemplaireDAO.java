package model.DAO;

import model.beans.Copy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExemplaireDAO {

    private Connection connection;

    public ExemplaireDAO() {
        this.connection = model.requests.Connection.getInstance();
    }

    public ArrayList<Copy> getExemplairesByOeuvre(int idOeuvre) {
        ArrayList<Copy> copies = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM exemplaire WHERE id_oeuvre=?");
            statement.setInt(1, idOeuvre);
            ResultSet result = statement.executeQuery();

            while(result.next()) {

                int idExemplaire = result.getInt(1);
                int refOeuvre = result.getInt(2);
                copies.add(new Copy(idExemplaire, idOeuvre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return copies;
    }
}
