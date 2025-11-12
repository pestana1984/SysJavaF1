package Entities;

import Models.Circuit;

public class RaceResultResponse {

    private Circuit circuit;
    private int carNumber;
    private String driverName;
    private int position;
    private int points;

    public RaceResultResponse(Circuit circuit, int carNumber, String driverName, int position, int points) {
        this.circuit = circuit;
        this.carNumber = carNumber;
        this.driverName = driverName;
        this.position = position;
        this.points = points;
    }

    @Override
    public String toString() {
        return String.format("%s - %d - %s - %d - %d", this.circuit.getName(), this.carNumber, this.driverName, this.position, this.points);
    }
}
