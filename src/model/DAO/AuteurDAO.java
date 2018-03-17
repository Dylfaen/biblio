package model.DAO;

import model.beans.Auteur;

import java.sql.*;

public class AuteurDAO {
    Connection connection;

    public AuteurDAO(Connection connection) {
        this.connection = connection;
    }

    public Auteur getAuteur(int id) {
        Auteur auteur = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM auteur WHERE ref_auteur=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            int idAuteur = result.getInt("ref_auteur");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            Date naissance = result.getDate("naissance");
            String nationalite = result.getString("nationalite");

            auteur = new Auteur(idAuteur, nom, prenom, naissance, nationalite);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auteur;

    }
}
