package Services;

import DAO.TeamBossDAO;
import DAO.TeamDAO;
import Data.ConnectDB;
import Models.Team;
import Models.TeamBoss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class TeamService {
    public static void CreateTeam(ConnectDB db){
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe o nome da equipe: ");
        String nomeEquipe =  sc.nextLine();

        System.out.println("Informe o pais da equipe: ");
        String paisEquipe = sc.nextLine();

        ArrayList<TeamBoss> bosses = TeamBossDAO.GetTeamBossesWithNoTeam(db);

        if(bosses.isEmpty()){
            System.err.println("Não existem chefes de equipes disponiveis.");

            TeamBoss boss = TeamBossService.CreateTeamBoss(db);

            Team team = new Team(nomeEquipe, paisEquipe, boss);

            TeamDAO.InsertTeam(db, team);
        }
        else{
            bosses.forEach(TeamBoss::showInfo);

            System.out.println("Informe o nome do chefe da equipe: ");
            String nome = sc.nextLine();

            TeamBoss boss = TeamBossDAO.GetBossByName(db, nome);

            Team team = new Team(nomeEquipe, paisEquipe, boss);

            TeamDAO.InsertTeam(db, team);
        }
    }

    public static void GetAllTeams(ConnectDB db){
        TeamDAO.GetAllTeams(db).forEach(Team::showTeam);
    }

    public static void DeleteTeam(ConnectDB db){

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
