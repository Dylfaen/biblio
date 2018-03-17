package model.DAO;

import model.beans.Exemplaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExemplaireDAO {

    private Connection connection;

    public ExemplaireDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Exemplaire> getExemplairesByOeuvre(int idOeuvre) {
        ArrayList<Exemplaire> exemplaires = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM exemplaire WHERE id_oeuvre=?");
            statement.setInt(1, idOeuvre);
            ResultSet result = statement.executeQuery();

            while(result.next()) {

                int idExemplaire = result.getInt(1);
                int refOeuvre = result.getInt(2);
                exemplaires.add(new Exemplaire(idExemplaire, idOeuvre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exemplaires;
    }
}
