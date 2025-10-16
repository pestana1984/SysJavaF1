package TemporadaF1;

import Models.*;

import java.util.ArrayList;
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

    public static void main(String[] args) {

        ArrayList<Team> teams = new ArrayList<>();
        ArrayList<Car> cars = new ArrayList<>();

        //Crei a primeira equipe com 2 carros
        //var team = CreateTeam();
        //var car1 = CreateCar();
        //var car2 = CreateCar();
        var boss1 = new TeamBoss("Fred Vasseur", 50, 500);
        var boss2 = new TeamBoss("Andrea Stella", 50, 500);

        var team1 = new Team("Ferrari", "Italia", boss1);
        var team2 = new Team("McLaren", "Inglaterra", boss2);

        var driver1 = new Driver("Carles Leclerc", 25, 500, 16);
        var driver2 = new Driver("Lewis Hamilton", 40, 500, 44);

        var car1 = new Car("SF25", 500, driver1);
        var car2 = new Car("SF25", 500, driver2);

        var driver3 = new Driver("Lando Norris", 25, 500, 4);
        var driver4 = new Driver("Oscar Piastri", 25, 500, 81);

        var car3 = new Car("MCP33", 500, driver3);
        var car4 = new Car("MCP33", 500, driver4);

        team1.addCar(car1);
        team1.addCar(car2);

        //Adicionei na lista de equipes
        teams.add(team1);

        //Criei a segunda equipe com mais 2 carros
//        team = CreateTeam();
//        car1 = CreateCar();
//        car2 = CreateCar();

        team2.addCar(car3);
        team2.addCar(car4);

        //Adicionei a nova equipe na lista de equipes
        teams.add(team2);

        for(Team team : teams){
            cars.addAll(team.cars);
        }

        Race race = new Race(cars, "BR", "Interlagos");

        race.startRace();
        race.showRaceResult(race.classification, race.drivers);

        System.out.println("Campeonato de Pilotos:");
        for(Driver driver : race.drivers){
            System.out.println("Nome: " + driver.getName());
            System.out.println("Pontos: " + driver.getPointsOnSeason());
        }

        race = new Race(cars, "AU", "Melbourne");

        race.startRace();
        race.showRaceResult(race.classification, race.drivers);

        System.out.println("Campeonato de Pilotos:");
        for(Driver driver : race.drivers){
            System.out.println("Nome: " + driver.getName());
            System.out.println("Pontos: " + driver.getPointsOnSeason());
        }

    }
}
