package DAO;

import Data.ConnectDB;
import Models.Engineer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class EngineerDAO {

    public static void InsertEngineer(ConnectDB db) {
        try {
            Scanner sc = new Scanner(System.in);

            String sql = "INSERT INTO \"TeamMembers\" (NAME, AGE, WAGE) VALUES (?, ?, ?)";

            System.out.println("Informe o nome: ");
            String nome = sc.nextLine();
            System.out.println("Informe a idade: ");
            int idade = sc.nextInt();
            sc.nextLine();
            System.out.println("Informe o salario: ");
            double salario = sc.nextDouble();
            sc.nextLine();
            System.out.println("Informe a especialidade: ");
            String especialidade = sc.nextLine();
            System.out.println("Informe os anos de experiencia: ");
            int anosExperiencia = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = db.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, nome);
            ps.setInt(2, idade);
            ps.setDouble(3, salario);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                {
                    if (generatedKeys.next()) {
                        int teamId = generatedKeys.getInt(1);

                        String sqlEngineer = "INSERT INTO \"Engineers\" (SPECIALTY, YEAROFEXPERIENCE, ID_MEMBER) VALUES (?, ?, ?)";

                        PreparedStatement ps2 = db.getConnection().prepareStatement(sqlEngineer);

                        ps2.setString(1, especialidade);
                        ps2.setInt(2, anosExperiencia);
                        ps2.setInt(3, teamId);

                        int affectedRow = ps2.executeUpdate();
                        if (affectedRow > 0) {
                            System.out.println("Engenheiro cadastrado com sucesso!");
                        }
                        ps2.close();
                    }
                }
                generatedKeys.close();
            }
            sc.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Engineer> GetEngineers(ConnectDB db) {

        String sql = "SELECT tm.name, tm.age, tm.wage, e.specialty, e.yearofexperience " +
                "FROM \"TeamMembers\" tm " +
                "JOIN \"Engineers\" e " +
                "on tm.id = e.id_member";

        ArrayList<Engineer> engineers = new ArrayList<>();

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Engineer engineer = new Engineer(rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("wage"),
                        rs.getString("specialty"),
                        rs.getInt("yearofexperience")
                );
                engineers.add(engineer);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return engineers;
    }
}
