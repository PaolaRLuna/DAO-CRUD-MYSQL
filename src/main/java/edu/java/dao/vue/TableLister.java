package edu.java.dao.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class TableLister {
    public static void afficher(String titre, String[][] data,
            String[] enTete) {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 128, 64));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        JLabel lblTitre = new JLabel(titre);
        lblTitre.setFont(new Font("Serif", Font.BOLD, 25));
        panel.add(lblTitre);
        JTable table = new JTable(data, enTete);
        table.setRowHeight(25);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Dialog", Font.BOLD, 18));
        JScrollPane scroll = new JScrollPane(table);
        JPanel sud = new JPanel();
        sud.setLayout(new GridLayout(2, 1));
        JPanel panelLabel = new JPanel();
        JLabel label = new JLabel("Nombre " + ": " + data.length);
        label.setFont(new Font("Serif", Font.BOLD, 25));
        panelLabel.add(label);
        sud.add(panelLabel);
        panelLabel.setBackground(new Color(255, 128, 64));
        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(new Color(255, 128, 64));
        sud.setBackground(Color.ORANGE);

        JButton retour = new JButton("Retour");
        panelBtn.add(retour);
        sud.add(panelBtn);
        retour.setFont(new Font("Serif", Font.BOLD, 20));

        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(scroll);
        frame.getContentPane().add(sud, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}
