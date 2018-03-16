package model.requests;

import java.sql.*;

public class Connexion {
    public static Statement connect() {
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }

        /* Connexion à la base de données */
        String url = "jdbc:mysql://localhost:3306/biblio";
        String utilisateur = "biblio";
        String motDePasse = "root";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            /* Création de l'objet gérant les requêtes */
            statement = connexion.createStatement();
        } catch (SQLException e ) {
            e.printStackTrace();
        }

        return statement;
    }

}