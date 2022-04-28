package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MedicamentDAO {


    //Attribut appartient a la classe
    Connection con;

    // constructeur
    public MedicamentDAO() {


        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/gsb_java", "root", "root");
            System.out.println("Success");

        }
        catch (SQLException ex) {
            ex.printStackTrace();


        }


    }

    // ici elle sera global car on sort du constructeur

    public List<Medicament> getMedicaments(){
        List<Medicament> medicamentList = new ArrayList<Medicament>();

        //remplire la liste
        //ecrire la requete
        //executer la requete avec prepareStatement
        //stocker le resultat dans ResultSet
        //parcourir le resultset pour remplir la liste


        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from medicament");

            Medicament m;
            while(rs.next()){
                m = new Medicament(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                medicamentList.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return medicamentList;
    }
}

