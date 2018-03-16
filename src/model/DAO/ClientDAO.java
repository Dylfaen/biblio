package model.DAO;

import model.beans.Client;
import model.requests.Connexion;

import java.sql.*;

public class ClientDAO {
    private Connection connection;

    public ClientDAO() {
        connection = Connexion.connect();
    }

    public Client getClient(String email, String hashed_password) {
        Client client = null;
        try {
            PreparedStatement statement = connection.prepareStatement( "SELECT * FROM user where identifiant=? and password=?;");

            statement.setString(1, email);
            statement.setString(2, hashed_password);

            ResultSet resultat = statement.executeQuery();


            if(resultat.next()) {

                int id = resultat.getInt("idUser");
                String identifiant = resultat.getString("identifiant");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                Date naissance =  resultat.getDate("naissance");
                String adresse = resultat.getString("adresse");

                client = new Client(id, identifiant, nom, prenom, naissance, adresse);
            } else {
                client = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

}
