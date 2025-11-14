package Services;

import Models.Team;
import Models.TeamBoss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class TeamService {
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

    public static void GetAllTeams(){

    }
    public static void DeleteTeam(){

    }

    public void ShowTeamsChampionship(ArrayList<Team> teams){
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
}
