package Models;

public class Car {
    private String model;
    private int horsePower;
    private double aerodinamicCoeficient;
    private Driver accountableDriver;

    public Car(String model, int horsePower, double aerodinamicCoeficient, Driver accountableDriver) {
        this.model = model;
        this.horsePower = horsePower;
        this.aerodinamicCoeficient = aerodinamicCoeficient;
        this.accountableDriver = accountableDriver;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public double getAerodinamicCoeficient() {
        return aerodinamicCoeficient;
    }

    public void setAerodinamicCoeficient(double aerodinamicCoeficient) {
        this.aerodinamicCoeficient = aerodinamicCoeficient;
    }

    public Driver getAccountableDriver() {
        return accountableDriver;
    }

    public void setAccountableDriver(Driver accountableDriver) {
        this.accountableDriver = accountableDriver;
    }

    public void showInfo(){
        System.out.println("Modelo: " + getModel());
        System.out.println("Potência: " + getHorsePower());
        System.out.println("Aerodinâmica: " +getAerodinamicCoeficient());
        System.out.println("Piloto Titular: " + getAccountableDriver().getName());
    }

    public double calculateEficiency(int driverHandicap){

        return this.horsePower * this.aerodinamicCoeficient * (driverHandicap / 100.0);

    }
}
