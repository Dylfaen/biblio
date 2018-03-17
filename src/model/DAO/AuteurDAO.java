package model.DAO;

import model.beans.Auteur;

import java.sql.*;

public class AuteurDAO {
    private Connection connection;

    public AuteurDAO() {
        this.connection = model.requests.Connection.getInstance();
    }

    public Auteur getAuteur(int id) {
        ResultSet result = null;
        PreparedStatement statement = null;

        Auteur auteur = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM auteur WHERE ref_auteur=?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            result.next();

            int idAuteur = result.getInt("ref_auteur");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            Date naissance = result.getDate("naissance");
            String nationalite = result.getString("nationalite");

            auteur = new Auteur(idAuteur, nom, prenom, naissance, nationalite);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { result.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
        }

        return auteur;

    }
}
