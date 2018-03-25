package controller.Util;

/**
 * Exception levée si la suppression d'un élément est interdite
 */
public class CannotRemoveItemException extends Exception {

    /**
     * Construit l'exception
     *
     * @param message Le message d'erreur
     */
    public CannotRemoveItemException(String message) {
        super(message);
    }
}
