package Models;

import java.util.Random;

public class Driver extends TeamMember {

    public int carNumber;
    private int handicap;
    private int pointsOnSeason;

    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        if(this.handicap < 100)
            this.handicap += handicap;
    }

    public int getPointsOnSeason() {
        return pointsOnSeason;
    }

    public void setPointsOnSeason(int points) {
        this.pointsOnSeason += points;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {}

    public Driver(String name, int age, double wage, int carNumber) {
        super(name, age, wage);
        this.carNumber = carNumber;
        Practice();
        this.pointsOnSeason = 0;
    }

    public void saveResult(int finishPosition) {
        switch (finishPosition) {
            case 1:
                setPointsOnSeason(25);
                break;
            case 2:
                setPointsOnSeason(18);
                break;
            case 3:
                setPointsOnSeason(15);
                break;
            case 4:
                setPointsOnSeason(12);
                break;
            case 5:
                setPointsOnSeason(10);
                break;
            case 6:
                setPointsOnSeason(8);
                break;
            case 7:
                setPointsOnSeason(6);
                break;
            case 8:
                setPointsOnSeason(4);
                break;
            case 9:
                setPointsOnSeason(2);
                break;
            case 10:
                setPointsOnSeason(1);
                break;
            default:
                break;
        }
    }

    public void Practice(){
        Random rand = new Random();
        int increase = rand.nextInt(6);
        setHandicap(increase);
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Numero do Carro: " + carNumber);
        System.out.println("Nivel de Habilidade: " + getHandicap());
        System.out.println("Pontos na Temporada: " + getPointsOnSeason());
    }
}
