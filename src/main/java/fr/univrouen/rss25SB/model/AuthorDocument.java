package fr.univrouen.rss25SB.model;

public class AuthorDocument {
    private String nom;
    private String github;
    private String githubPicture;

    // Constructeurs, getters, setters

    public AuthorDocument(String nom, String github) {
        this.nom = nom;
        this.github = github;
        this.githubPicture = github + ".png"; // GitHub redirige l'URL .png vers la photo de profil
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getGithubPicture() {
        return githubPicture;
    }

    public void setGithubPicture(String githubPicture) {
        this.githubPicture = githubPicture;
    }
}
