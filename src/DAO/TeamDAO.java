package DAO;

import Data.ConnectDB;
import Models.Team;
import Models.TeamBoss;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamDAO {

    public static void InsertTeam(ConnectDB db, Team team) {

        String sqlTeam = "INSERT INTO \"Teams\" (NAME, CITIZENSHIP, POINTSONSEASON, ID_TEAMBOSS) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps = db.getConnection().prepareStatement(sqlTeam)){

            ps.setString(1, team.getName());
            ps.setString(2, team.getCitizenship());
            ps.setInt(3, 0);
            ps.setInt(4, TeamBossDAO.GetBossIdByName(db, team.getBossName()));

            ps.executeUpdate();

            ps.close();
            System.out.println("Equipe cadastrada com sucesso!");

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Team> GetAllTeams(ConnectDB db) {
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

    public static int GetTeamByName(ConnectDB db, String name){
        String sqlTeam = "SELECT id FROM \"Teams\" "+
                        "WHERE NAME = ?";

        try (PreparedStatement psTeam = db.getConnection().prepareStatement(sqlTeam)) {
            psTeam.setString(1, name);
            ResultSet rsTeam = psTeam.executeQuery();
            rsTeam.next();
            return rsTeam.getInt("id");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public static void DeleteTeamByID(ConnectDB db, int id){
        String sqlTeam = "DELETE FROM \"Teams\" WHERE id = ?";

        try (PreparedStatement psTeam = db.getConnection().prepareStatement(sqlTeam)) {
            psTeam.setInt(1, id);
            psTeam.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}

