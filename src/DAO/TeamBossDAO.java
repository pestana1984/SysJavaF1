package DAO;

import Data.ConnectDB;
import Models.Driver;
import Models.TeamBoss;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamBossDAO {

    public static void InsertTeamBoss(ConnectDB db) {

        String sqlMember = "INSERT INTO \"TeamMembers\" (NAME, AGE, WAGE) VALUES (?, ?, ?)";

        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Informe o nome: ");
            String nome = sc.nextLine();
            System.out.println("Informe a idade: ");
            int idade = sc.nextInt();
            sc.nextLine();
            System.out.println("Informe o salario: ");
            double salario = sc.nextDouble();
            sc.nextLine();

            TeamBoss boss = new TeamBoss(nome, idade, salario);

            PreparedStatement ps = db.getConnection().prepareStatement(sqlMember, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, nome);
            ps.setInt(2, idade);
            ps.setDouble(3, salario);

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
}
