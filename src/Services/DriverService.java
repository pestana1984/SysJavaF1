package Services;

import Models.Driver;
import Models.RaceResult;

import java.util.Comparator;
import java.util.Scanner;

public class DriverService {
    public Driver CreateDriver(){
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

    public void ShowDriversChampionship(RaceResult race){
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
}
