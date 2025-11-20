package Services;

import DAO.TeamBossDAO;
import Data.ConnectDB;
import Models.TeamBoss;

import java.util.Scanner;

public class TeamBossService {

    public static void CreateTeamBoss(ConnectDB db, TeamBoss boss){
        TeamBossDAO.InsertTeamBoss(db, boss);
    }
}
