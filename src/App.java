import DAO.MedicamentDAO;
import IHM.accueil;

public class App {
    public static void main(String[] args) {
        accueil page_accueil = new accueil();

        MedicamentDAO medDAO = new MedicamentDAO();
    }
}
