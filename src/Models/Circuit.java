package Models;

public class Circuit {
    private int id;
    private String name;
    private String country;

    public Circuit(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Circuit(String name, String country, int id) {
        this.name = name;
        this.country = country;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public String getCountry() {
        return this.country;
    }

    public int getId() {
        return this.id;
    }

    public void showInfo(){
        System.out.println("Circuito: " + this.name + " - " + this.country);
    }
}
