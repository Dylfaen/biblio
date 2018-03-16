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
                client = new Client(
                        resultat.getInt(0),
                        resultat.getString(1),
                        resultat.getString(3),
                        resultat.getString(4),
                        resultat.getDate(5),
                        resultat.getString(6)
                );
            } else {
                client = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

}
