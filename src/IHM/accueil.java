package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class accueil {
    private JPanel Main;
    private JCheckBox comptesRendusCheckBox;
    private JCheckBox visiteursCheckBox;
    private JCheckBox praticiensCheckBox;
    private JCheckBox médicamentsCheckBox;
    private JCheckBox quitterCheckBox;

    private JFrame accueil;






    public accueil() {
        accueil = new JFrame("Accueil");
        accueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        accueil.setPreferredSize(new Dimension(500,500));
        accueil.setResizable(false);
        accueil.add(Main);
        accueil.pack();
        accueil.setLocationRelativeTo(null);
        accueil.setVisible(true);

        //Gestion des evenements

        médicamentsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accueil.dispose();
                medicamentFrame medFrame =new medicamentFrame();


            }
        });
        quitterCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}


