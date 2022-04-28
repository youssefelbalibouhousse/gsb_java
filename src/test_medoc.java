import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class test_medoc {
    private JPanel Main;
    private JTextField txtCommerce;
    private JTextField txtComposition;
    private JTextField txtPrix;
    private JButton précédentButton;
    private JButton suivantButton;
    private JButton fermerButton;
    private JTextField txtCode;
    private JTextField txtFamille;
    private JButton rechercherButton;

    private JTextArea txtEffet;
    private JTextArea txtContre;


    public static void main(String[] args) {
        JFrame frame = new JFrame("test_medoc");
        frame.setContentPane(new test_medoc().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/gsb_java", "root", "root");
            System.out.println("Success");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public test_medoc() {
        connect();
        précédentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        rechercherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String id = txtCode.getText();

                    pst = con.prepareStatement("SELECT * FROM `medicament` where MED_DEPOTLEGAL = ?");
                    pst.setString(1, id);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String code = rs.getString(1);
                        String commerce = rs.getString(2);
                        String famille = rs.getString(3);
                        String composition = rs.getString(4);
                        String effets = rs.getString(5);
                        String contre = rs.getString(6);
                        String prix = rs.getString(7);
                        txtCode.setText(code);
                        txtCommerce.setText(commerce);
                        txtFamille.setText(famille);
                        txtComposition.setText(composition);
                        txtEffet.setText(effets);
                        txtContre.setText(contre);
                        txtPrix.setText(prix);

                    }
                    else
                    {
                        txtCode.setText("");
                        txtCommerce.setText("");
                        txtFamille.setText("");
                        txtComposition.setText("");
                        txtEffet.setText("");
                        txtContre.setText("");
                        txtPrix.setText("");
                        JOptionPane.showMessageDialog(null,"Médicaments non existant !");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

