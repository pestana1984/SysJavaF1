package Services;

import DAO.SeasonDAO;
import Data.ConnectDB;
import Models.Driver;
import Models.Team;

import java.util.ArrayList;
import java.util.List;

public class SeasonService {

    public static void DriversChampionship(ConnectDB db){
        ArrayList<Driver> championship = SeasonDAO.GetDriversChampionship(db);

        for (Driver driver : championship) {
            System.out.println(driver.getName() + " - " + driver.getPointsOnSeason());
        }
    }

    public static void TeamsChampionship(ConnectDB db){
        List<Team> teams = SeasonDAO.GetTeamsChampionship(db);

        for(Team team : teams) {
            System.out.println(team.getName() + " - " + team.getPointsOnSeason());
        }
    }
}
