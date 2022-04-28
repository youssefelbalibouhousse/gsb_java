import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class test {
    private JPanel Main;
    private JButton saveButton;
    private JTable table1;
    private JButton uptadeButton;
    private JButton supprimerButton;
    private JButton rechercherButton;
    private JTextField txtid;
    private JTextField txtNom;
    private JTextField txtSalaire;
    private JTextField txtMobile;
    private JScrollPane table_1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setContentPane(new test().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;

    public void connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/rbcompany", "root","root");
            System.out.println("Success");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    void table_load(){
        try{
            pst = con.prepareStatement("SELECT * FROM employe");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }





    public test() {
        connect();
        table_load();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nom,salaire,mobile;

                nom = txtNom.getText();
                salaire = txtSalaire.getText();
                mobile = txtMobile.getText();

                try {
                    pst = con.prepareStatement("insert into employe(nom,salaire,mobile)values(?,?,?)");
                    pst.setString(1, nom);
                    pst.setString(2, salaire);
                    pst.setString(3, mobile);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Nouveau employé bien enregistrer !");
                    txtNom.setText("");
                    txtSalaire.setText("");
                    txtMobile.setText("");
                    txtNom.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }

            }
        });


        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String employe_id = txtid.getText();

                    pst = con.prepareStatement("select nom,salaire,mobile from employe where id = ?");
                    pst.setString(1, employe_id);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String employe_nom = rs.getString(1);
                        String employe_salaire = rs.getString(2);
                        String employe_mobile = rs.getString(3);

                        txtNom.setText(employe_nom);
                        txtSalaire.setText(employe_salaire);
                        txtMobile.setText(employe_mobile);

                    }
                    else
                    {
                        txtNom.setText("");
                        txtSalaire.setText("");
                        txtMobile.setText("");
                        JOptionPane.showMessageDialog(null,"ID employé inéxistant !");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }




            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employe_id;
                employe_id = txtid.getText();

                try {
                    pst = con.prepareStatement("delete from employe  where id = ?");

                    pst.setString(1, employe_id);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Employé supprimé !");
                    table_load();
                    txtNom.setText("");
                    txtSalaire.setText("");
                    txtMobile.setText("");
                    txtNom.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }
        });
        uptadeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employe_id,employe_nom,employe_salaire,employe_mobile;
                employe_nom = txtNom.getText();
                employe_salaire = txtSalaire.getText();
                employe_mobile = txtMobile.getText();
                employe_id = txtid.getText();

                try {
                    pst = con.prepareStatement("update employe set nom = ?,salaire = ?,mobile = ? where id = ?");
                    pst.setString(1, employe_nom);
                    pst.setString(2, employe_salaire);
                    pst.setString(3, employe_mobile);
                    pst.setString(4, employe_id);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updateee!!!!!");
                    table_load();
                    txtNom.setText("");
                    txtSalaire.setText("");
                    txtMobile.setText("");
                    txtNom.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }
}
