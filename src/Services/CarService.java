package Services;

import DAO.CarDAO;
import DAO.DriverDAO;
import DAO.TeamDAO;
import Data.ConnectDB;
import Models.Car;
import Models.Driver;
import Models.Team;

import java.util.Scanner;

public class CarService {
    public static void CreateCar(ConnectDB db) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe o modelo do carro: ");
        String modelo = sc.nextLine();
        System.out.println("Informe a potencia do carro: ");
        int potencia = sc.nextInt();
        sc.nextLine();

        DriverDAO.GetDrivers(db).forEach(Driver::showInfo);

        System.out.println("Informe o nome do piloto do carro: ");
        String nome = sc.nextLine();
        Driver driver = DriverDAO.GetDriverByName(db, nome);

        Car car = new Car(modelo, potencia, driver);

        TeamDAO.GetAllTeams(db).forEach(Team::showTeam);
        System.out.printf("Informe a equipe desejada:");
        String nomeEquipe = sc.nextLine();

        Team team = TeamDAO.GetTeamByName(db, nomeEquipe);

        CarDAO.InsertCar(db, car, team);
    }

    public static void GetAllCars(ConnectDB db) {

        CarDAO.GetAllCars(db).forEach(Car::showInfo);

    }
}

