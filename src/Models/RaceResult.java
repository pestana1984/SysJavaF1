package Models;

import java.util.ArrayList;

public class RaceResult {

    private String circuitName;
    private String country;
    public ArrayList<Car> cars;
    public ArrayList<Driver> drivers;
    public int[] classification;
    public ArrayList<Team> teams;

    public RaceResult(ArrayList<Car> cars, String country, String circuitName, ArrayList<Team> teams) {
        this.circuitName = circuitName;
        this.country = country;
        this.cars = cars;
        this.classification = new int[cars.size()];
        this.teams = teams;

        this.drivers = new ArrayList<>();
        for (Car car : cars) {
            drivers.add(car.getAccountableDriver());
        }
    }

    public String getCircuitName() {
        return this.circuitName;
    }

    public String getCountry() {
        return this.country;
    }

    public int[] getClassification() {
        return this.classification;
    }

    public void startRace() {

        if (cars == null || cars.isEmpty()) return;

        classification = new int[cars.size()];

        cars.sort((car1, car2) -> {
            double perf1 = car1.calculateEficiency(car1.getAccountableDriver().getHandicap());
            double perf2 = car2.calculateEficiency(car2.getAccountableDriver().getHandicap());
            return Double.compare(perf2, perf1); // Maior primeiro
        });

        classification = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            classification[i] = cars.get(i).getAccountableDriver().carNumber;
        }

        updateDriversPointsOnSeason();
        updateTeamsPointsOnSeason();

        for(Driver driver : drivers) {
            driver.Practice();
        }

        for(Car car : cars){
            car.calculateEficiency(car.getAccountableDriver().getHandicap());
        }

        showRaceResult(classification, drivers);
    }

    private void showRaceResult(int[] classification, ArrayList<Driver> drivers) {

        System.out.println("Circuito:" + this.getCircuitName());
        System.out.println("País: " + this.getCountry());
        System.out.println("----------------------");
        for (int i = 0; i < classification.length; i++) {
            System.out.print(classification[i]);
            for(Driver driver : drivers) {
                if(classification[i] == driver.carNumber) {
                    System.out.print(" - " + driver.getName() + "\n");
                }
            }
        }
    }

    void updateDriversPointsOnSeason() {
        for(int i = 0; i < classification.length; i++) {
            for(Driver driver : drivers) {
                if(driver.carNumber == classification[i]) {
                    driver.saveResult(i+1);
                }
            }
        }
    }

    void updateTeamsPointsOnSeason(){
        for (Team team : teams) {
            team.calculatePointsOnSeason();
        }
    }
}
