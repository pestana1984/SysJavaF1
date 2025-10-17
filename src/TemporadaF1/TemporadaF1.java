package TemporadaF1;

import Models.*;

import java.util.ArrayList;
import java.util.Comparator;
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

    public static void ShowDriversChampionship(Race race){
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

        ArrayList<Team> teams = new ArrayList<>();
        ArrayList<Car> cars = new ArrayList<>();

        var boss1 = new TeamBoss("Fred Vasseur", 50, 500);
        var boss2 = new TeamBoss("Andrea Stella", 50, 500);

        var boss3 = new TeamBoss("Matia Binotto", 50, 500);
        var boss4 = new TeamBoss("Lorran Max", 40, 500);

        var team1 = new Team("Ferrari", "Italia", boss1);
        var team2 = new Team("McLaren", "Inglaterra", boss2);
        var team3 = new Team("Stake Sauber", "Suiça", boss3);
        var team4 = new Team("Red Bull", "Austria", boss4);

        var driver1 = new Driver("Carles Leclerc", 25, 500, 16);
        var driver2 = new Driver("Lewis Hamilton", 40, 500, 44);

        var car1 = new Car("SF25", 500, driver1);
        var car2 = new Car("SF25", 500, driver2);

        var driver3 = new Driver("Lando Norris", 25, 500, 4);
        var driver4 = new Driver("Oscar Piastri", 25, 500, 81);

        var car3 = new Car("MCP33", 500, driver3);
        var car4 = new Car("MCP33", 500, driver4);

        var driver5 = new Driver("Gabriel Bortoletto", 20, 500, 6);
        var driver6 = new Driver("Nick Hulkenberg", 38, 500, 17);

        var car5 = new Car("SS25", 500, driver5);
        var car6 = new Car("SS25", 500, driver6);

        var driver7 = new Driver("Max Verstappen", 26, 500, 1);
        var driver8 = new Driver("Yuki Tsunoda", 26, 500, 22);

        var car7 = new Car("RB25", 500, driver7);
        var car8 = new Car("RB25", 500, driver8);

        team1.addCar(car1);
        team1.addCar(car2);

        team2.addCar(car3);
        team2.addCar(car4);

        team3.addCar(car5);
        team3.addCar(car6);

        team4.addCar(car7);
        team4.addCar(car8);

        //Adicionei na lista de equipes
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);

        for(Team team : teams){
            cars.addAll(team.cars);
        }

        Race race = new Race(cars, "BR", "Interlagos", teams);

        race.startRace();
        race.showRaceResult(race.classification, race.drivers);

        ShowDriversChampionship(race);
        ShowTeamsChampionship(teams);

        race = new Race(cars, "AU", "Melbourne", teams);

        race.startRace();
        race.showRaceResult(race.classification, race.drivers);

        ShowDriversChampionship(race);
        ShowTeamsChampionship(teams);

        race = new Race(cars, "US", "Circuit of America", teams);
        race.startRace();
        race.showRaceResult(race.classification, race.drivers);

        ShowDriversChampionship(race);
        ShowTeamsChampionship(teams);
    }
}
