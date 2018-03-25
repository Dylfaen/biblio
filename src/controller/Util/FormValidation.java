package controller.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe utile à la validation des formulaires
 */
public class FormValidation {

    /**
     * Vérifie que la dateToCheck est au bon format et que c'est une date valide
     *
     * @param dateToCheck La chaîne de caractère contenant la date vérifier
     * @param format      Le format de la date
     * @return true si la date est au bon format et valide (Ex : "2018-02-31" retourne faux car la date est invalide)
     */
    public static boolean checkDate(String dateToCheck, String format) {
        boolean isValid = false;

        //Si la date est nulle on retourne faux
        if(dateToCheck != null) {
            //On définit le formatteur
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            formatter.setLenient(false); //Il vérifiera la validité de la date
            try {
                //Une ParseException est envoyée si la date est invalide ou au mauvais format
                formatter.parse(dateToCheck);
                isValid = true;
            } catch (ParseException e) {
                e.printStackTrace();
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Vérifie que l'identifiant est rempli
     *
     * @param id L'identifiant à verifier
     * @throws Exception Contient le message d'erreur
     */
    public static void validationIdentifiant(String id) throws Exception {
        if (id == null || id.equals("")) {
            throw new Exception("Identifiant obligatoire");
        }
    }

    /**
     * Vérifie que l'identifiant est rempli
     *
     * @param password Le mot de passe à vérifier
     * @throws Exception Contient le message d'erreur
     */
    public static void validationPassword(String password) throws Exception {
        if (password == null || password.equals("")) {
            throw new Exception("Mot de Passe obligatoire");
        }
    }
}
