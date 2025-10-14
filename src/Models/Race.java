package Models;

import java.util.ArrayList;

public class Race {

    private String circuitName;
    private String country;
    public ArrayList<Car> cars;
    public int[] classification;

    public Race(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public void startRace(){

            //TODO Verificar essa função

            if (cars == null || cars.isEmpty()) return;

            if (classification == null || classification.length != cars.size()) {
                classification = new int[cars.size()];
            }

            // Ordena cars por desempenho (maior desempenho primeiro)
            cars.sort((a, b) -> Double.compare(b.calculateEficiency(
                    b.getAccountableDriver().getHandicap()),
                    a.calculateEficiency(a.getAccountableDriver().getHandicap()
                    )));

            // Preenche a classificação com o número dos pilotos
            for (int i = 0; i < cars.size(); i++) {
                classification[i] = cars.get(i).getAccountableDriver().carNumber;
            }


    }

    public void showRaceResult(){
        for(int i = 0; i < classification.length; i++){
            System.out.println(i+1 + " Posicao: " + "Carro " + classification[i]);
        }
    }

    void updatePointsOnSeason(){

    }

}
