package edu.java.dao.controleurs.controleurFilm;
import edu.java.dao.models.modelFilm.Film;

import java.util.ArrayList;

import edu.java.dao.models.modelFilm.DaoFilm;

public class ControleurFilm implements IActionsFilm {

    protected static final String instance = null;
    private static ControleurFilm CtrF_Instance = null;
    private static DaoFilm Dao_Instance = null; 
    
    private ControleurFilm(){} 

    public static synchronized ControleurFilm getControleurFilm() { 
        try {
            if (CtrF_Instance == null) { 
                CtrF_Instance = new ControleurFilm();
                Dao_Instance = DaoFilm.getFilmDao(); 
            } 
            return CtrF_Instance;
        } catch (Exception e) {
            return null;
        }
    }
    public String CtrF_Enregistrer(Film film) {
        String message = null;
        message = Dao_Instance.MdlF_Enregistrer(film); 
        return message;
    }

    public ArrayList<Film> CtrF_GetAllFilms(){
        try{
            Dao_Instance = DaoFilm.getFilmDao();
            return (ArrayList<Film>) Dao_Instance.MdlF_GetAll();
        }catch(ClassCastException e){
            return null;
        }
    };

    public Film CtrF_GetFilmById(int idf){
        Dao_Instance = DaoFilm.getFilmDao();
        return Dao_Instance.MdlF_GetById(idf);
    };

    public Film CtrF_GetFilmByTitre(String titre){
        return Dao_Instance.MdlF_GetByTitre(titre);
    };

    public int CtrF_Modifier(Film filmaModifier){
        return Dao_Instance.MdlF_Modifier(filmaModifier);
    };

    public int CtrF_Enlever(int idf){
        return Dao_Instance.MdlF_Supprimer(idf);
    }; 
}
