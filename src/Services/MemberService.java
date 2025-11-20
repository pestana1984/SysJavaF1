package Services;

import DAO.DriverDAO;
import DAO.EngineerDAO;
import DAO.TeamBossDAO;
import Data.ConnectDB;
import Models.Driver;
import Models.Engineer;
import Models.TeamBoss;

import java.util.Scanner;

public class MemberService {

    public static void CreateMember(ConnectDB db) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe o nome: ");
        String nome = sc.nextLine();
        System.out.println("Informe a idade: ");
        int idade = sc.nextInt();
        sc.nextLine();
        System.out.println("Informe o salario: ");
        double salario = sc.nextDouble();
        sc.nextLine();

        System.out.println("1 - Chefe");
        System.out.println("2 - Engenheiro");
        System.out.println("3 - Piloto");
        System.out.println("Qual é o tipo de Membro que está sendo cadastrado? ");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1:
                TeamBossService.CreateTeamBoss(db, new TeamBoss(nome, idade, salario));
                break;
            case 2:
                System.out.println("Informe a especialidade do engenheiro: ");
                String especialidade = sc.nextLine();
                System.out.println("Informe os anos de experiencia: ");
                int anosExperiencia = sc.nextInt();
                sc.nextLine();
                EngineerDAO.InsertEngineer(db, new Engineer(nome, idade, salario, especialidade, anosExperiencia));
                break;
            case 3:
                System.out.println("Informe o numero que o piloto vai usar: ");
                int numeroPiloto = sc.nextInt();
                sc.nextLine();
                DriverDAO.InsertDriver(db, new Driver(nome, idade, salario, numeroPiloto));
                break;
            default:
                System.err.println("Opção Inválida");
        }
    }

    public static void GetAllMembers(ConnectDB db) {
        Scanner sc = new Scanner(System.in);

        EngineerDAO.GetEngineers(db).forEach(Engineer::showInfo);
        sc.nextLine();

        DriverDAO.GetDrivers(db).forEach(Driver::showInfo);
        sc.nextLine();

        TeamBossDAO.GetTeamBosses(db).forEach(TeamBoss::showInfo);
        sc.nextLine();

        sc.close();
    }

    public static void DeleteMember(ConnectDB db) {

    }
}
