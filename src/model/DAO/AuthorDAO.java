package model.DAO;

import model.Data;
import model.beans.Author;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Le DAO des auteurs. Il compile un ensemble de méthodes pour manipuler les Auteurs de Data
 *
 * @see model.Data
 */
public class AuthorDAO {


    /**
     * Renvoie l'auteur possédant l'id en paramètre
     * @see Data
     * @param id l'id à trouver
     * @return l'auteur possédant l'id en paramètre ou null si aucun ne correspond
     */
    public Author getAuthor(long id) {
        // On récupèe la liste des auteurs
        Data data = Data.getInstance();
        ArrayList<Author> authors = data.getAuthors();

        //On parcoure la liste jusqu'à ce qu'on trouve l'auteur ou qu'on soit arrivé à la fin de la liste
        Author author = null;
        Boolean found = false;
        Iterator it = authors.listIterator();
        while (it.hasNext() && !found) {
            Author temp_author = (Author) it.next();
            if (temp_author.getId() == (id)) {
                //Si l'auteur parcouru à l'id cherché alors on l'a trouvé
                author = temp_author;
                found = true;
            }
        }
        return author;
    }

    /**
     * Retourne la liste des auteurs
     * @see Data
     * @return la liste des auteurs
     */
    public ArrayList<Author> getAuthors() {
        return Data.getInstance().getAuthors();
    }

    /**
     * Ajoute un auteur aux données
     * @see Data
     * @param author le nouvel auteur
     * @throws IOException Si l'instance des données n'a pas pu être sauvegardée
     */
    public void createAuthor(Author author) throws IOException {
        Data data = Data.getInstance();
        data.getAuthors().add(author);
        data.saveInstance();
    }
}
