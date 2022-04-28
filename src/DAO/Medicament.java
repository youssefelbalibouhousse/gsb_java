package DAO;

public class Medicament {

    private String code;
    private String commerce;
    private String famille;
    private String compos;
    private String prix;
    private String effets;
    private String contre;

    //constructeur avec parametres
    public Medicament(String code, String commerce, String famille, String compos, String effets, String contre, String prix) {
        this.code = code;
        this.commerce = commerce;
        this.famille = famille;
        this.compos = compos;
        this.prix = prix;
        this.effets = effets;
        this.contre = contre;
    }
    //Getter et setter des attributs
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCommerce() {
        return commerce;
    }

    public void setCommerce(String commerce) {
        this.commerce = commerce;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public String getCompos() {
        return compos;
    }

    public void setCompos(String compos) {
        this.compos = compos;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getEffets() {
        return effets;
    }

    public void setEffets(String effets) {
        this.effets = effets;
    }

    public String getContre() {
        return contre;
    }

    public void setContre(String contre) {
        this.contre = contre;
    }

}
