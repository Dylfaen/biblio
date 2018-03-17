package model.requests;

import java.sql.*;

public class Connection {
    private static java.sql.Connection connexion;

    public static java.sql.Connection getInstance() {
        if(connexion == null) {
            Connection.initConnection();
        }
        return connexion;
    }

    private static void initConnection() {
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }

        /* Connexion à la base de données */
        String url = "jdbc:mysql://localhost:3306/biblio?autoReconnect=true&useSSL=false";
        String utilisateur = "biblio";
        String motDePasse = "root";
        Statement statement = null;
        ResultSet resultat = null;
        try {
            Connection.connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            /* Création de l'objet gérant les requêtes */
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }
}
