package edu.java.dao.vue;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import edu.java.dao.controleurs.controleurFilm.ControleurFilm;
import edu.java.dao.models.modelFilm.Film;
import java.util.*;
import java.awt.Color;
import javax.swing.JScrollPane;

public class Chercher {

	private JFrame frame;
	private JTextField txtChercher;
	private JTable tableFilms;
	private ArrayList<Film> listeFilms;
	ControleurFilm instance = ControleurFilm.getControleurFilm();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chercher window = new Chercher();
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
	public Chercher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 531, 375);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblchercher = new JLabel("Chercher :");
		lblchercher.setBounds(39, 32, 65, 14);
		frame.getContentPane().add(lblchercher);

		txtChercher = new JTextField();
		txtChercher.setBounds(105, 29, 235, 20);
		frame.getContentPane().add(txtChercher);
		txtChercher.setColumns(10);

		JCheckBox chckbxidchercher = new JCheckBox("Par ID");
		chckbxidchercher.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxidchercher.setBounds(69, 60, 97, 23);
		frame.getContentPane().add(chckbxidchercher);

		JCheckBox chckbxtitrechercher = new JCheckBox("Par Titre");
		chckbxtitrechercher.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxtitrechercher.setBounds(210, 60, 97, 23);
		frame.getContentPane().add(chckbxtitrechercher);

		// Créer un model pour la table film
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Id Film");
		model.addColumn("Titre");
		model.addColumn("Durée");
		model.addColumn("Réalisateur");
		model.addColumn("Pochette");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 92, 416, 176);
		frame.getContentPane().add(scrollPane);

		JButton btnrecherche = new JButton("Chercher");
		btnrecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemAChercher = txtChercher.getText();

				if (chckbxidchercher.isSelected() == true) {
					ControleurFilm instance = ControleurFilm.getControleurFilm();
					Film lefilm = instance.CtrF_GetFilmById(Integer.parseInt(itemAChercher));
					DefaultTableModel model = (DefaultTableModel) tableFilms.getModel();

					model.addRow(new Object[] { lefilm.getIdf() + "", lefilm.getTitre(), lefilm.getDuree() + "",
							lefilm.getRes(),
							lefilm.getPochette() });
				} else if (chckbxtitrechercher.isSelected() == true) {
					Film lefilm = instance.CtrF_GetFilmByTitre(itemAChercher);
					DefaultTableModel model = (DefaultTableModel) tableFilms.getModel();
					model.addRow(new Object[] { lefilm.getIdf(), lefilm.getTitre(), lefilm.getDuree(), lefilm.getRes(),
							lefilm.getPochette() });
				} else {
					JOptionPane.showMessageDialog(null,
							"Vous avez oublié de selectionner le type de recherche par ID ou par Titre.",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnrecherche.setBounds(382, 28, 89, 23);
		frame.getContentPane().add(btnrecherche);

		// Creer la table
		tableFilms = new JTable(model);
		scrollPane.setViewportView(tableFilms);
		JButton btnModifier = new JButton("Modifier");

		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numeroFilm = JOptionPane.showInputDialog(null, "Entrez le numéro du film à modifier :",
						"MODIFIER FILM",
						JOptionPane.PLAIN_MESSAGE);

				ControleurFilm instance = ControleurFilm.getControleurFilm();
				Film lefilm = instance.CtrF_GetFilmById(Integer.parseInt(numeroFilm));
				Modifierfilm.main(lefilm);
			}

		});

		btnModifier.setBounds(77, 287, 89, 23);
		frame.getContentPane().add(btnModifier);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		btnAnnuler.setBounds(351, 287, 89, 23);
		frame.getContentPane().add(btnAnnuler);

		JButton btnenlever = new JButton("Enlever");
		btnenlever.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numeroFilm = JOptionPane.showInputDialog(null, "Entrez le numéro du film à enlever :",
						"ENLEVER FILM",
						JOptionPane.PLAIN_MESSAGE);
				ControleurFilm instance = ControleurFilm.getControleurFilm();
				int reponse = instance.CtrF_Enlever(Integer.parseInt(numeroFilm));
				if (reponse == -1) {
					JOptionPane.showMessageDialog(null, "Le film a été enlevé");
				} else {
					JOptionPane.showMessageDialog(null,
							"Le film n'a pas été enlevé. Vérifier la connexion \nà la base de données. Sinon, consulter votre administrateur");
				}
			}
		});
		btnenlever.setBounds(218, 287, 89, 23);
		frame.getContentPane().add(btnenlever);
	}


}
