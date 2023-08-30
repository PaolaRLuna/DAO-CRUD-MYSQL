package edu.java.dao.vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.dao.controleurs.controleurFilm.ControleurFilm;
import edu.java.dao.models.modelFilm.Film;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modifierfilm extends JFrame{
    
    private JPanel contentPane;
	private JTextField txtidfilm;
	private JTextField txttitref;
	private JTextField txtduree;
	private JTextField texrest;
	private JTextField textFieldpochette;
    private static Film lefilm;

	/**
	 * Launch the application.
	 */
	public static void main(Film film) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lefilm = film;
					Modifierfilm frame = new Modifierfilm();
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
	public Modifierfilm() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 300);
		
		JMenuBar menuBarfenModifierfilm = new JMenuBar();
		setJMenuBar(menuBarfenModifierfilm);
		
		JMenu mnMenuoptions = new JMenu("Options");
		menuBarfenModifierfilm.add(mnMenuoptions);
		
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
        txtidfilm.setText(String.valueOf(lefilm.getIdf()));
		
		JLabel lblTitre = new JLabel("Titre Film :");
		lblTitre.setBounds(184, 5, 84, 36);
		contentPane.add(lblTitre);
		
		txttitref = new JTextField();
		txttitref.setBounds(247, 5, 153, 36);
		contentPane.add(txttitref);
		txttitref.setColumns(10);
        txttitref.setText(lefilm.getTitre());
		
		JLabel FieldQualifForme = new JLabel("Durée :");
		FieldQualifForme.setBounds(6, 52, 114, 43);
		FieldQualifForme.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(FieldQualifForme);
		
		txtduree = new JTextField();
		txtduree.setBounds(90, 59, 219, 29);
		contentPane.add(txtduree);
		txtduree.setColumns(10);
        txtduree.setText(String.valueOf(lefilm.getDuree()));
		
		JLabel lblres = new JLabel("Res :");
		lblres.setBounds(6, 106, 46, 14);
		contentPane.add(lblres);
		
		texrest = new JTextField();
		texrest.setBounds(88, 103, 221, 20);
		contentPane.add(texrest);
		texrest.setColumns(10);
        texrest.setText(lefilm.getRes());
		
		JLabel lblpochette = new JLabel("Pochette :");
		lblpochette.setBounds(6, 151, 72, 14);
		contentPane.add(lblpochette);
		
		textFieldpochette = new JTextField();
		textFieldpochette.setBounds(88, 148, 221, 20);
		contentPane.add(textFieldpochette);
		textFieldpochette.setColumns(10);
        textFieldpochette.setText(lefilm.getPochette());
		
		JButton btnModifierfilm = new JButton("Modifier");
		btnModifierfilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String idf = txtidfilm.getText();
				String titre = txttitref.getText();
				String duree = txtduree.getText();
				String res = texrest.getText();
				String pochette = textFieldpochette.getText();
				Film unnouveauFilm = new Film(Integer.parseInt(idf),titre,Integer.parseInt(duree),res,pochette);
				ControleurFilm instance = ControleurFilm.getControleurFilm();
				int validation = instance.CtrF_Modifier(unnouveauFilm);
				if (validation !=-1){
					JOptionPane.showMessageDialog(null, "Le film a été modifié");
					dispose();
				} else {
                    JOptionPane.showMessageDialog(null, "Le film n'a pas été modifié. Consulter votre administrateur");
                }
			}
		});
		btnModifierfilm.setBounds(93, 191, 89, 23);
		contentPane.add(btnModifierfilm);
		
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
