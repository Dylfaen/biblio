package controller.Util;

/**
 * Exception levée lorsqu'un nom d'utilisateur n'est pas disponible
 */
public class UsernameTakenException extends Exception {
    /**
     * Construit l'exception
     *
     * @param message Le message d'erreur
     */
    public UsernameTakenException(String message) {
        super(message);
    }
}
