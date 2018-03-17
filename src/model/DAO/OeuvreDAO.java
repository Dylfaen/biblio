package model.DAO;

import model.beans.Auteur;
import model.beans.Exemplaire;
import model.beans.Oeuvre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OeuvreDAO {

    private Connection connection;

    public OeuvreDAO(Connection connection) {
        this.connection = connection;
    }

    public Oeuvre getOeuvre(int id) {
        Oeuvre oeuvre = null;
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM oeuvre WHERE ref_oeuvre=?");
            statement.setInt(1, id);
            ResultSet resultOeuvre = statement.executeQuery();


            if (resultOeuvre.getFetchSize() > 0) {
                oeuvre = this.createOeuvre(resultOeuvre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oeuvre;
    }

    public ArrayList<Oeuvre> getOeuvres() {
        ArrayList<Oeuvre> oeuvres = new ArrayList<>();
        try {
            PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM oeuvre");
            ResultSet resultOeuvre = statement.executeQuery();

            while (resultOeuvre.next()) {
                oeuvres.add(this.createOeuvre(resultOeuvre));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oeuvres;
    }

    private Oeuvre createOeuvre(ResultSet resultOeuvre) throws SQLException {
        ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connection);
        AuteurDAO auteurDAO = new AuteurDAO(connection);
        int idOeuvre = resultOeuvre.getInt("ref_oeuvre");
        int idAuteur = resultOeuvre.getInt("ref_auteur");
        String titre = resultOeuvre.getString("titre");

        ArrayList<Exemplaire> exemplaires = exemplaireDAO.getExemplairesByOeuvre(idOeuvre);
        Auteur auteur = auteurDAO.getAuteur(idAuteur);

        return new Oeuvre(idOeuvre, auteur, titre, exemplaires);
    }
}
