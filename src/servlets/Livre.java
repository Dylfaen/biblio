public class Livre {

    private String reference;
    private String titre;
    private Auteur auteur;
    private String editeur;

    public Auteur getAuteur() {
        return auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public String getReference() {
        return reference;
    }

    public String getTitre() {
        return titre;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
