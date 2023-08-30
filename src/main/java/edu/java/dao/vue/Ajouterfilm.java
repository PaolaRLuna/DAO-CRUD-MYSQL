package edu.java.dao.vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import edu.java.dao.controleurs.controleurFilm.ControleurFilm;
import edu.java.dao.models.modelFilm.Film;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class Ajouterfilm extends JFrame {

	private JPanel contentPane;
	private JTextField txtidfilm;
	private JTextField txttitref;
	private JTextField txtduree;
	private JTextField texrest;
	private JTextField txtPochette;
	ControleurFilm instance = ControleurFilm.getControleurFilm();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajouterfilm frame = new Ajouterfilm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ajouterfilm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 300);

		JMenuBar menuBarfenajouterfilm = new JMenuBar();
		setJMenuBar(menuBarfenajouterfilm);

		JMenu mnMenuoptions = new JMenu("Options");
		menuBarfenajouterfilm.add(mnMenuoptions);

		JMenuItem mnMenusortir = new JMenuItem("Sortir");
		mnMenuoptions.add(mnMenusortir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbidf = new JLabel("Id Film :");
		lbidf.setBounds(6, 5, 84, 36);
		contentPane.add(lbidf);

		txtidfilm = new JTextField();
		txtidfilm.setBounds(88, 10, 84, 26);
		contentPane.add(txtidfilm);
		txtidfilm.setColumns(10);

		JLabel lblTitre = new JLabel("Titre Film :");
		lblTitre.setBounds(184, 5, 84, 36);
		contentPane.add(lblTitre);

		txttitref = new JTextField();
		txttitref.setBounds(247, 5, 153, 36);
		contentPane.add(txttitref);
		txttitref.setColumns(10);

		JLabel FieldQualifForme = new JLabel("Durée :");
		FieldQualifForme.setBounds(6, 52, 114, 43);
		FieldQualifForme.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(FieldQualifForme);

		txtduree = new JTextField();
		txtduree.setBounds(90, 59, 219, 29);
		contentPane.add(txtduree);
		txtduree.setColumns(10);

		JLabel lblres = new JLabel("Réalisateur :");
		lblres.setBounds(6, 106,76, 14);
		contentPane.add(lblres);

		texrest = new JTextField();
		texrest.setBounds(88, 103, 221, 20);
		contentPane.add(texrest);
		texrest.setColumns(10);

		JLabel lblpochette = new JLabel("Pochette :");
		lblpochette.setBounds(6, 151, 72, 14);
		contentPane.add(lblpochette);

		txtPochette = new JTextField();
		txtPochette.setBounds(88, 148, 221, 20);
		contentPane.add(txtPochette);
		txtPochette.setColumns(10);

		JButton btnajouterfilm = new JButton("Enregistrer");
		btnajouterfilm.setBounds(93, 191, 109, 23);
		contentPane.add(btnajouterfilm);
		btnajouterfilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idf = txtidfilm.getText();
				String titre = txttitref.getText();
				String duree = txtduree.getText();
				String res = texrest.getText();
				String pochette = txtPochette.getText();
				Film unnouveauFilm = new Film(Integer.parseInt(idf), titre, Integer.parseInt(duree), res, pochette);
				ControleurFilm instance = ControleurFilm.getControleurFilm();
				String msg = instance.CtrF_Enregistrer(unnouveauFilm);
				if (msg != null) {
					JOptionPane.showMessageDialog(null, msg);
					txtidfilm.setText("");
					txttitref.setText("");
					txtduree.setText("");
					texrest.setText("");
					txtPochette.setText("");
					Ajouterfilm.this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Le film n'a pas été enregistré. Vérifiez la connection à la bd.\nOu bien consulter l'administrateur.");
				}
			}
		});

		JButton btnannuler = new JButton("Annuler");
		btnannuler.setBounds(253, 191, 89, 23);
		contentPane.add(btnannuler);
		btnannuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
