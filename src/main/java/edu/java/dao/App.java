package edu.java.dao;

import edu.java.dao.controleurs.controleurFilm.ControleurFilm;
import edu.java.dao.models.modelFilm.Film; // on import Film.java du modelFilm
//name space est un package + le dossier + le fichier qui nous interesse
import edu.java.dao.vue.GestionFilms;

//on a la structure de dao (controleur et model)
public final class App {
    // private App() {
    // }

    public static void main(String[] args) {
        // Selon le choix de l'utilisateur faudra appeler la bonne méthode
        // du contrôleur.
        // CAS 1 : Enregistrer un film
        // Film film = new Film ();
        // film.setTitre("Conan");
        // film.setDuree(90);
        // film.setRes("Arnold");
        // film.setPochette("https://ia.media-imdb.com/images/M/MV5BMTYwOTEwNjA...");

        // //Singleton, d'une classe donné je vais creer un objet
        // ControleurFilm CtrF = ControleurFilm.getControleurFilm(); // on appel le
        // controleur (getcontro est methode statique) apres avoir importé les modeles
        // String message = CtrF.CtrF_Enregistrer(film);
        // System.out.println(message);
        GestionFilms.main(null);

    }
}
