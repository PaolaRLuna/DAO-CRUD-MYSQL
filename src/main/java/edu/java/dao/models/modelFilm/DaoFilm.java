package edu.java.dao.models.modelFilm;

// on appel le model
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//DATTA ACCESS OBJECT
public class DaoFilm implements IFilmDao {
    private static Connection conn = null;
    private static DaoFilm instanceDao = null;

    // MySQL
    // private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String URL_BD = "jdbc:mysql://localhost/mabdfilms";
    private static final String USAGER = "root";
    private static final String PASS = "";
    // on cree les requetes
    private static final String SUPPRIMER = "DELETE FROM films WHERE idf=?";
    private static final String GET_ALL = "SELECT * FROM films ORDER BY idf";
    private static final String GET_BY_ID = "SELECT * FROM films WHERE idf=?";
    private static final String GET_BY_TITRE = "SELECT * FROM films WHERE titre=?";
    private static final String ENREGISTRER = "INSERT INTO films VALUES(0,?, ?, ?, ?)";
    private static final String MODIFIER = "UPDATE films SET titre=?, duree=?, res=?, pochette=? WHERE idf=?";

    // Singleton de connexion à la BD
    // getConnexion() est devenu une zonne critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    private DaoFilm() {

    };

    public Connection getConnection() {
    try {
        if (conn == null) {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
        }
    } catch (Exception e) {

    }
    return conn;
    }

    public static synchronized DaoFilm getFilmDao() {
        try {
            // Class.forName(PILOTE);
            if (instanceDao == null) {
                instanceDao = new DaoFilm(); // quand je cree une instance la classe on cree une connexion
                conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
                // cela fait pas partie du singleton
            }
            return instanceDao;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    // Create
    public String MdlF_Enregistrer(Film film) {
        PreparedStatement stmt = null;
        try { // requete est dans enregistrer, pour obtenir la clé qui a été generé on utilise
              // return_generated keys
            //conn = getConnection();
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, film.getTitre()); //cada columna corresponde a los signos de interrogacion de la requete/query
            stmt.setInt(2, film.getDuree());
            stmt.setString(3, film.getRes());
            stmt.setString(4, film.getPochette());

            stmt.executeUpdate(); // il execute la requete
            ResultSet rs = stmt.getGeneratedKeys(); //

            if (rs.next()) {
                film.setIdf(rs.getInt(1)); // int est dans la premier colonne qui contient la clé, on veut la metre dans
                                           // la classe pour definir le num de film
            }
            return "Film bien enregistré";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
    }

    // Read
    // retourne une liste de films
    public List<Film> MdlF_GetAll() {
        PreparedStatement stmt = null;
        List<Film> listeFilms = new ArrayList<Film>();

        try {
            //conn = getConnection();
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) { // on obtient la liste de tous les films et on va ligne par ligne
                Film film = new Film();
                film.setIdf(rs.getInt("idf"));
                film.setTitre(rs.getString("titre"));
                film.setDuree(rs.getInt("duree"));
                film.setRes(rs.getString("res"));
                film.setPochette(rs.getString("pochette"));

                listeFilms.add(film);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }

        return listeFilms;
    }

    public Film MdlF_GetById(int idf) {
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            //conn = getConnection();// DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, idf);

            ResultSet rs = stmt.executeQuery();
            Film film = new Film();
            if (rs.next()) {
                film.setIdf(rs.getInt("idf"));
                film.setTitre(rs.getString("titre"));
                film.setDuree(rs.getInt("duree"));
                film.setRes(rs.getString("res"));
                film.setPochette(rs.getString("pochette"));

                return film;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
            // e.printStackTrace();
            // throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
    }

    public Film MdlF_GetByTitre(String titre) {
        PreparedStatement stmt = null;

        try {
            //conn = getConnection();
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_BY_TITRE);
            stmt.setString(1, titre);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Film film = new Film();
                film.setIdf(rs.getInt("idf"));
                film.setTitre(rs.getString("titre"));
                film.setDuree(rs.getInt("duree"));
                film.setRes(rs.getString("res"));
                film.setPochette(rs.getString("pochette"));

                return film;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
    }

    // Update, faudrat avant appeler MdlF_GetById(idf) pour obtenir
    // les données du film à modifier via une interface et après envoyer
    // ce film à MdlF_Modifier(film) pour faire la mise à jour.
    public int MdlF_Modifier(Film film) {
        PreparedStatement stmt = null;
        int reponse = -1;
        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            //conn = getConnection();
            stmt = conn.prepareStatement(MODIFIER); // on appele la requete modifier
            stmt.setString(1, film.getTitre());
            stmt.setInt(2, film.getDuree());
            stmt.setString(3, film.getRes());
            stmt.setString(4, film.getPochette());
            stmt.setInt(5, film.getIdf());

            reponse = stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
        return reponse;
    }

    // Delete
    public int MdlF_Supprimer(int idf) {
        PreparedStatement stmt = null;
        int reponse = -1;
        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            //conn = getConnection();
            stmt = conn.prepareStatement(SUPPRIMER);
            stmt.setInt(1, idf);

            reponse = stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
        return reponse;
    }

    private static void MdlF_Fermer(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void MdlF_Fermer(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
