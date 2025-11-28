package DAO;

import Data.ConnectDB;
import Models.Driver;
import Models.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeasonDAO {

    public static ArrayList<Driver> GetDriversChampionship(ConnectDB db) {
        String sql = "SELECT tm.name, tm.age, tm.wage, d.carnumber, d.handicap, d.pointsonseason, d.id " +
                "FROM \"Drivers\" d " +
                "JOIN \"TeamMembers\" tm " +
                "ON d.id_member = tm.id " +
                "ORDER BY pointsonseason DESC";

        ArrayList<Driver> drivers = new ArrayList<>();

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Driver driver = new Driver(rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("wage"),
                        rs.getInt("carnumber"),
                        rs.getInt("handicap"),
                        rs.getInt("pointsonseason"),
                        rs.getInt("id"));
                drivers.add(driver);
            }


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return drivers;
    }

    public static ArrayList<Team> GetTeamsChampionship(ConnectDB db) {
        String sql = "SELECT t.name, t.pointsonseason FROM \"Teams\" t";

        ArrayList<Team> teams = new ArrayList<>();

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Team team = new Team(rs.getString("name"));
                //team.calculatePointsOnSeason();
                teams.add(team);
            }

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return teams;

    }
}
