package Models;

import java.util.ArrayList;
import java.util.Comparator;

public class Race {

    private String circuitName;
    private String country;
    public ArrayList<Car> cars;
    public ArrayList<Driver> drivers;
    public int[] classification;

    public Race(ArrayList<Car> cars, String country, String circuitName) {
        this.circuitName = circuitName;
        this.country = country;
        this.cars = cars;

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
        updatePointsOnSeason();
    }

    public void showRaceResult(int[] classification, ArrayList<Driver> drivers) {

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

    void updatePointsOnSeason() {
        for(int i = 0; i < 4; i++) {
            for(Driver driver : drivers) {
                if(driver.carNumber == classification[i]) {
                    driver.saveResult(i+1);
                }
            }
        }
    }
}
