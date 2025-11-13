package TemporadaF1;

import DAO.*;
import Data.ConnectDB;
import Entities.RaceResultResponse;
import Models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class TemporadaF1 {

    public static Driver CreateDriver(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe o nome do piloto: ");
        String nome =  sc.nextLine();
        System.out.println("Informe a idade do piloto: ");
        int idade = sc.nextInt();
        sc.nextLine();
        System.out.println("Informe o salário do piloto: ");
        double salario = sc.nextDouble();
        sc.nextLine();
        System.out.println("Informe o numero que o piloto vai usar: ");
        int numero = sc.nextInt();
        sc.nextLine();

        return new Driver(nome, idade, salario, numero);
    }

    public static Team CreateTeam(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe o nome da equipe: ");
        String nomeEquipe =  sc.nextLine();
        System.out.println("Informe o pais da equipe: ");
        String paisEquipe = sc.nextLine();

        System.out.println("Informe o nome do chefe da equipe: ");
        String nome =  sc.nextLine();
        System.out.println("Informe a idade do chefe da equipe: ");
        int idade = sc.nextInt();
        sc.nextLine();
        System.out.println("Informe o salário do chefe da equipe: ");
        double salario = sc.nextDouble();
        sc.nextLine();

        return new Team(nomeEquipe, paisEquipe, new TeamBoss(nome, idade, salario));
    }

    public static Car CreateCar(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe o modelo do carro: ");
        String modelo = sc.nextLine();
        System.out.println("Informe a potencia do carro: ");
        int potencia = sc.nextInt();
        sc.nextLine();

        var driver = CreateDriver();

        return new Car(modelo, potencia, driver);
    }

    public static void ShowDriversChampionship(RaceResult race){
        System.out.println("\n\nCampeonato de Pilotos:");
        var championship = race.drivers;

        championship.sort(Comparator.comparing(Driver::getPointsOnSeason).reversed());

        for(Driver d : championship){
            System.out.println("-------------");
            System.out.println(d.getCarNumber() + " - " + d.getName());
            System.out.println("Pontos: " + d.getPointsOnSeason());
            //System.out.println("-------------");
        }
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
    }

    public static void ShowTeamsChampionship(ArrayList<Team> teams){
        System.out.println("\n\nCampeonato de Construtores:");

        teams.sort(Comparator.comparing(Team::getPointsOnSeason).reversed());

        for(Team t : teams) {
            System.out.println("-------------");
            System.out.println(t.getName() + " - " + t.getBossName());
            System.out.println("Pontos: " + t.getPointsOnSeason());
            //System.out.println("-------------");
        }
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
    }

    public static void main(String[] args) {

        ConnectDB db = new ConnectDB("dpg-d3rcb88gjchc73cpjlug-a.oregon-postgres.render.com",
                "testjava", "Q0buWnbkqDMFmYEHZXXMtSHL54NHrNZ9", "testjava_gz5z");

        if(db.getConnection() != null)
        {
            System.out.println("Conectado com sucesso!");
        }
        //Cadastra Engenheiro
        //EngineerDAO.InsertEngineer(db);
        // EngineerDAO.GetEngineers(db).forEach(Engineer::showInfo);

        //Cadastra Piloto
        //DriverDAO.InsertDriver(db);
        //DriverDAO.GetDrivers(db).forEach(Driver::showInfo);

        //CadastraChefe
        //TeamBossDAO.InsertTeamBoss(db);
        //TeamBossDAO.GetTeamBosses(db).forEach(TeamBoss::showInfo);

        //Cadastra Equipe
        //TeamDAO.InsertTeam(db);
        //TeamDAO.GetTeams(db).forEach(Team::showTeam);

        //Cadastra Carro
        //CarDAO.InsertCar(db);
        //CarDAO.GetCars(db).forEach(Car::showInfo);

        //Cadastra Circuito
        //CircuitDAO.InsertCircuit(db);
        //CircuitDAO.GetCircuits(db).forEach(Circuit::showInfo);

        Random rand = new Random();

        ArrayList<Car> cars = CarDAO.GetCars(db);
        ArrayList<Team> teams = TeamDAO.GetTeams(db);

        Circuit circuit = CircuitDAO.GetCircuits(db).get(3);

        RaceResult race = new RaceResult(cars, circuit.getCountry(), circuit.getName(), teams);

        race.startRace();

        RaceResultDAO.InsertRaceResult(db, race.getClassification(), race.getCircuitName());

        var rr = RaceResultDAO.GetRaceResults(db, race.getCircuitName());

        rr.forEach(System.out::println);

        Circuit circuit2 = CircuitDAO.GetCircuits(db).get(6);

        RaceResult race2 = new RaceResult(cars, circuit2.getCountry(), circuit2.getName(), teams);

        race2.startRace();

        RaceResultDAO.InsertRaceResult(db, race2.getClassification(), race2.getCircuitName());

        var rr2 = RaceResultDAO.GetRaceResults(db, race2.getCircuitName());

        rr2.forEach(System.out::println);

    }
}
