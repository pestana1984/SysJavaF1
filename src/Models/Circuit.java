package Models;

public class Circuit {
    private String name;
    private String country;

    public Circuit(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return this.name;
    }
    public String getCountry() {
        return this.country;
    }

    public void showInfo(){
        System.out.println("Circuito: " + this.name + " - " + this.country);
    }
}
