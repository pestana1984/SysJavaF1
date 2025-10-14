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
        var team = CreateTeam();
        var car1 = CreateCar();
        var car2 = CreateCar();

        team.addCar(car1);
        team.addCar(car2);

        //Adicionei na lista de equipes
        teams.add(team);

        //Criei a segunda equipe com mais 2 carros
        team = CreateTeam();
        car1 = CreateCar();
        car2 = CreateCar();

        team.addCar(car1);
        team.addCar(car2);

        //Adicionei a nova equipe na lista de equipes
        teams.add(team);

        for(int i = 0; i < teams.size(); i++){
            cars.add(teams.get(i).cars.get(i));
            cars.add(teams.get(i).cars.get(i+1));
        }

        Race race = new Race(cars);

        race.startRace();
        race.showRaceResult();

    }
}
