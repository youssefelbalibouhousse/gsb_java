package IHM;

import DAO.Medicament;
import DAO.MedicamentDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class medicamentFrame implements ActionListener {

    private JLabel lblCode;
    private JLabel lblnomCommerciale;
    private JButton precedentButton;
    private JButton suivantButton;
    private JTextField textFCode;
    private JTextField textFNomCommerciale;
    private JTextField textFamille;
    private JTextField textFComposition;
    private JTextField textFEffetIndesirable;
    private JTextField textFContreIndication;
    private JButton fermerButton;
    private JTextField textFPrix;
    private JPanel Panel;
    private JTextArea textAEffetsIndesirable;
    private JTextArea textAContreIndications;
    private JButton rechercherButton;
    private JLabel lblTitre;

    private JFrame medicamentFrame2;

    //declaration des liste pour recuperer les donnée sql sous arraylist
    private List<Medicament> medicamentList;
    private MedicamentDAO medicamentDAO;

    private int actualMedicamentIndex;
    private Medicament actualMedicament;

    Connection con;
    PreparedStatement pst;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gsb_java", "root", "root");
            System.out.println("Success");


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public medicamentFrame() {
        this.medicamentFrame2 = new JFrame("Médicaments");
        this.medicamentFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.medicamentFrame2.setPreferredSize(new Dimension(500, 500));
        this.medicamentFrame2.setResizable(true);
        this.medicamentFrame2.add(Panel);
        this.medicamentFrame2.pack();
        this.medicamentFrame2.setLocationRelativeTo(null);
        this.medicamentFrame2.setVisible(true);


        // instancie medicamentDAO pour recuperer la LISTE transforme medicament de la BDD
        this.medicamentDAO=new MedicamentDAO();
        this.medicamentList = this.medicamentDAO.getMedicaments();

        this.actualMedicamentIndex = 0;
        this.setMedicament( this.medicamentList.get(actualMedicamentIndex));




        //gestion des evenement click
        this.suivantButton.addActionListener(this);
        this.precedentButton.addActionListener(this);
        this.fermerButton.addActionListener(this);
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                medicamentFrame2.dispose();
                accueil Homepage = new accueil();

            }
        });

        connect();
        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechercherButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        for(Medicament medicament : medicamentList) {
                            if (medicament.getCode().equals(textFCode.getText())) {
                                //operation sur l'objet trouver : attribution des données aux formulaires

                                textFamille.setText(medicament.getFamille());
                                textAContreIndications.setText(medicament.getContre());
                                textAEffetsIndesirable.setText(medicament.getEffets());
                                textFComposition.setText(medicament.getCompos());
                                textFNomCommerciale.setText(medicament.getCommerce());
                            }
                        }



                    }
                });
            }
        });
    }

    public void setMedicament(Medicament medicament){
        this.textFCode.setText(medicament.getCode());
        this.textFNomCommerciale.setText(medicament.getCommerce());
        this.textFamille.setText(medicament.getFamille());
        this.textFComposition.setText(medicament.getCompos());
        this.textAEffetsIndesirable.setText(medicament.getEffets());
        this.textAContreIndications.setText(medicament.getContre());
        this.textFPrix.setText(medicament.getPrix());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.suivantButton){
            this.actualMedicamentIndex+=1;
        }else if (e.getSource()==this.precedentButton){
            this.actualMedicamentIndex-=1;

        }

        this.setMedicament( this.medicamentList.get(actualMedicamentIndex));
    }
}


