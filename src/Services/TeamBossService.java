package Services;

import DAO.TeamBossDAO;
import Data.ConnectDB;
import Models.TeamBoss;

import java.util.Scanner;

public class TeamBossService {

    public static TeamBoss CreateTeamBoss(ConnectDB db){

        Scanner sc = new Scanner(System.in);

        System.out.println("Informe o nome do chefe da equipe: ");
        String nome = sc.nextLine();

        System.out.println("Informe a idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.println("Informe o salario: ");
        double salario = sc.nextDouble();
        sc.nextLine();

        TeamBoss boss = new TeamBoss(nome, idade, salario);

        TeamBossDAO.InsertTeamBoss(db, boss);

        return boss;

    }
}
