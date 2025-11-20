package DAO;

import Data.ConnectDB;
import Models.Driver;
import Models.TeamBoss;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamBossDAO {

    public static void InsertTeamBoss(ConnectDB db, TeamBoss boss) {

        String sqlMember = "INSERT INTO \"TeamMembers\" (NAME, AGE, WAGE) VALUES (?, ?, ?)";

        try {

            PreparedStatement ps = db.getConnection().prepareStatement(sqlMember, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, boss.getName());
            ps.setInt(2, boss.getAge());
            ps.setDouble(3, boss.getWage());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                {
                    if (generatedKeys.next()) {
                        int bossId = generatedKeys.getInt(1);

                        String sqlTeamBoss = "INSERT INTO \"TeamBosses\" (BONUS, ID_MEMBER) VALUES (?, ?)";

                        PreparedStatement ps2 = db.getConnection().prepareStatement(sqlTeamBoss);
                        ps2.setDouble(1, boss.getBonus());
                        ps2.setDouble(2, bossId);

                        int affectedRow = ps2.executeUpdate();
                        if (affectedRow > 0) {
                            System.out.println("Chefe cadastrado com sucesso!");
                        }
                        ps2.close();
                    }
                }
                generatedKeys.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<TeamBoss> GetTeamBosses(ConnectDB db) {
        String sqlSelectBosses = "select tm.name, tm.age, tm.wage from \"TeamBosses\" tb " +
                "join \"TeamMembers\" tm " +
                "ON tb.id_member = tm.id";

        ArrayList<TeamBoss> teamBosses = new ArrayList<>();

        try (PreparedStatement ps = db.getConnection().prepareStatement(sqlSelectBosses)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TeamBoss teamBoss = new TeamBoss(rs.getString(1),
                        rs.getInt(2),
                        rs.getDouble(3));

                teamBosses.add(teamBoss);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return teamBosses;
    }

    public static TeamBoss GetBossByName(ConnectDB db, String name) {
        String sqlBoss = "SELECT NAME, AGE, WAGE FROM \"TeamMembers\" WHERE NAME = ?";

        try (PreparedStatement psBoss = db.getConnection().prepareStatement(sqlBoss)) {

            psBoss.setString(1, name);
            ResultSet rsBoss = psBoss.executeQuery();

            TeamBoss teamBoss = null;

            while (rsBoss.next()) {
                teamBoss = new TeamBoss(rsBoss.getString("name"),
                        rsBoss.getInt("age"),
                        rsBoss.getDouble("wage")
                );
            }
            ;
            return teamBoss;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static int GetBossIdByName(ConnectDB db, String name) {
        String sqlBoss = "SELECT tb.id FROM \"TeamBosses\" tb " +
                            "join \"TeamMembers\" tm " +
                            "on tm.id = tb.id_member " +
                            "WHERE tm.NAME = ?";

        try (PreparedStatement psBoss = db.getConnection().prepareStatement(sqlBoss)) {
            psBoss.setString(1, name);
            ResultSet rsBoss = psBoss.executeQuery();
            rsBoss.next();
            return rsBoss.getInt("id");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public static ArrayList<TeamBoss> GetTeamBossesWithNoTeam(ConnectDB db) {
        String sqlSelectBosses = "select tm.\"name\", tm.age, tm.wage from \"TeamBosses\" tb " +
                "left join \"Teams\" t " +
                "on tb.id = t.id_teamboss " +
                "left join \"TeamMembers\" tm " +
                "on tb.id_member = tm.id " +
                "where t.id_teamboss is null";

        ArrayList<TeamBoss> teamBosses = new ArrayList<>();

        try (PreparedStatement ps = db.getConnection().prepareStatement(sqlSelectBosses)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TeamBoss teamBoss = new TeamBoss(rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("wage"));

                teamBosses.add(teamBoss);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return teamBosses;
    }
}
