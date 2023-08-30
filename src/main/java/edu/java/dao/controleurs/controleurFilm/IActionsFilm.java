package edu.java.dao.controleurs.controleurFilm;
import java.util.ArrayList;

import edu.java.dao.models.modelFilm.Film;

//dans asp.net est toujours extends, une interface commence par la lettre I

public interface IActionsFilm {
    // Pour le CRUD - Create Read Update Delete

    // Create
    public String CtrF_Enregistrer(Film film);
    
    // // Read
    public ArrayList<Film> CtrF_GetAllFilms();

    public Film CtrF_GetFilmById(int idf);

    public Film CtrF_GetFilmByTitre(String titre);

    // // Update
    public int CtrF_Modifier(Film user);

    // // Delete
    public int CtrF_Enlever(int idf); 
}
