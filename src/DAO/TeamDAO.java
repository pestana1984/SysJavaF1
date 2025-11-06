package DAO;

import Data.ConnectDB;
import Models.Engineer;
import Models.Team;
import Models.TeamBoss;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamDAO {

    public static void InsertTeam(ConnectDB db) {

        String sqlTeam = "INSERT INTO \"Teams\" (NAME, CITIZENSHIP, POINTSONSEASON, ID_TEAMBOSS) VALUES (?, ?, ?, ?)";

        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Informe o nome da equipe: ");
            String nomeEquipe =  sc.nextLine();
            System.out.println("Informe o pais da equipe: ");
            String paisEquipe = sc.nextLine();

            TeamBossDAO.GetTeamBosses(db).forEach(TeamBoss::showInfo);

            System.out.println("Informe o nome do chefe da equipe: ");
            String chefe = sc.nextLine();

            String sqlBoss = "SELECT id FROM \"TeamMembers\" WHERE NAME = ?";

            PreparedStatement psBoss = db.getConnection().prepareStatement(sqlBoss);
            psBoss.setString(1, chefe);
            ResultSet rsBoss = psBoss.executeQuery();

            rsBoss.next();
            int bossId = rsBoss.getInt("id");

            sqlBoss = "SELECT id FROM \"TeamBosses\" WHERE ID_MEMBER = ?";

            psBoss = db.getConnection().prepareStatement(sqlBoss);
            psBoss.setInt(1, bossId);
            rsBoss = psBoss.executeQuery();
            rsBoss.next();
            bossId = rsBoss.getInt("id");

            PreparedStatement ps = db.getConnection().prepareStatement(sqlTeam);
            ps.setString(1, nomeEquipe);
            ps.setString(2, paisEquipe);
            ps.setInt(3, 0);
            ps.setInt(4, bossId);

            ps.executeUpdate();

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Team> GetTeams(ConnectDB db) {
        String sql = "select t.name, t.citizenship, tm.name from \"Teams\" t " +
                "join \"TeamBosses\" tb " +
                "on t.id_teamboss = tb.id " +
                "join \"TeamMembers\" tm " +
                "on tm.id = tb.id_member";

        ArrayList<Team> teams = new ArrayList<>();

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                TeamBoss teamBoss = new TeamBoss("name");

                Team team = new Team(rs.getString("name"),
                        rs.getString("citizenship"),
                        teamBoss);

                teams.add(team);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return teams;
    }

}

