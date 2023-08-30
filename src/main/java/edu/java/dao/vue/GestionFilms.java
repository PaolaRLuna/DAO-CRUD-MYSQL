package edu.java.dao.vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import edu.java.dao.controleurs.controleurFilm.ControleurFilm;
import edu.java.dao.models.modelFilm.Film;
import java.util.*;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class GestionFilms {

	private JFrame frame;
	ControleurFilm instance = ControleurFilm.getControleurFilm();

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionFilms window = new GestionFilms();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionFilms() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblFilm = new JLabel("Gestion de films");
		lblFilm.setFont(new Font("Tahoma", Font.BOLD, 16));
		frame.getContentPane().add(lblFilm);

		JLabel label = new JLabel("");
		frame.getContentPane().add(label);

		JButton btnlisterfilms = new JButton("Lister films");
		btnlisterfilms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ControleurFilm instance = ControleurFilm.getControleurFilm();
				ArrayList<Film> films = instance.CtrF_GetAllFilms();
				String[][] tab = new String[films.size()][];
				int index = 0;
				for (Film film : films) {
					tab[index] = new String[] { film.getIdf() + "", film.getTitre(), film.getDuree() + "",
							film.getRes(), film.getPochette() };
					index++;
				}
				String[] enTete = { "Idclass", "Titre", "Durée", "Réalisateur",
						"Pochette" };
				TableLister.afficher("Films", tab, enTete);
			}
		});
		btnlisterfilms.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnlisterfilms);

		JButton btnAjouter = new JButton("Ajouter film");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ajouterfilm.main(null);
			}
		});
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnAjouter);

		JButton btnmodifier = new JButton("Modifier film");
		btnmodifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numeroFilm = JOptionPane.showInputDialog(null, "Entrez le numéro du film à modifier :",
						"MODIFIER FILM",
						JOptionPane.PLAIN_MESSAGE);
				if (numeroFilm != null){//pour gerer si on press cancel
					if (numeroFilm.length() > 0) {
					//System.out.println(1);
					ControleurFilm instance = ControleurFilm.getControleurFilm();
					Film lefilm = instance.CtrF_GetFilmById(Integer.parseInt(numeroFilm));
					//System.out.println(lefilm.getDuree());
					Modifierfilm.main(lefilm);
				} else {
					JOptionPane.showMessageDialog(null, "Rentrez un numéro du film",
							"MODIFIER FILM",
							JOptionPane.PLAIN_MESSAGE);
				}	
				} 			
			}
		});
		btnmodifier.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnmodifier);

		JButton btnchercher = new JButton("Chercher Film");
		btnchercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chercher.main(null);
			}
		});
		btnchercher.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnchercher);

		JButton btnenlever = new JButton("Enlever Film");
		btnenlever.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numeroFilm = JOptionPane.showInputDialog(null, "Entrez le numéro du film à enlever :",
						"ENLEVER FILM",
						JOptionPane.PLAIN_MESSAGE);
				if (numeroFilm != null){
				
					if (numeroFilm.length() > 0) {
						if (isNumeric(numeroFilm)) {
							ControleurFilm instance = ControleurFilm.getControleurFilm();
							int reponse = instance.CtrF_Enlever(Integer.parseInt(numeroFilm));
							if (reponse == -1) {
								JOptionPane.showMessageDialog(null, "Le film a été enlevé");
							} else {
								JOptionPane.showMessageDialog(null,
									"Le film n'a pas été enlevé, il se peut qu'il n'existe pas.");
							}
						}
					} else {
					JOptionPane.showInputDialog(null, "Rentrez un numéro du film",
							"MODIFIER FILM",
							JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		btnenlever.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnenlever);

		JButton btnsortir = new JButton("Sortir");
		btnsortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		btnsortir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnsortir);
	}

}
