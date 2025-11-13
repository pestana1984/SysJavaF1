package DAO;

import Data.ConnectDB;
import Models.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverDAO {
    public static void InsertDriver(ConnectDB db) {

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
            System.out.println("Informe o numero que o piloto vai usar: ");
            int numero = sc.nextInt();
            sc.nextLine();

            Driver driver = new Driver(nome, idade, salario, numero);

            PreparedStatement ps = db.getConnection().prepareStatement(sqlMember, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, driver.getName());
            ps.setInt(2, driver.getAge());
            ps.setDouble(3, driver.getWage());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                {
                    if (generatedKeys.next()) {
                        int teamId = generatedKeys.getInt(1);

                        String sqlDriver = "INSERT INTO \"Drivers\" (CARNUMBER, HANDICAP, POINTSONSEASON, ID_MEMBER) VALUES (?, ?, ?, ?)";

                        PreparedStatement ps2 = db.getConnection().prepareStatement(sqlDriver);
                        ps2.setInt(1, driver.getCarNumber());
                        ps2.setDouble(2, driver.getHandicap());
                        ps2.setInt(3, driver.getPointsOnSeason());
                        ps2.setInt(4, teamId);

                        int affectedRow = ps2.executeUpdate();
                        if (affectedRow > 0) {
                            System.out.println("Piloto cadastrado com sucesso!");
                        }
                        ps2.close();
                    }
                }
                generatedKeys.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Driver> GetDrivers(ConnectDB db) {

        String sqlSelectDriver = "select tm.name, tm.age, tm.wage, d.carnumber, d.handicap, d.pointsonseason, d.id " +
                "from \"TeamMembers\" tm " +
                "join \"Drivers\" d " +
                "on tm.id = d.id_member";

        ArrayList<Driver> drivers = new ArrayList<>();

        try (PreparedStatement ps = db.getConnection().prepareStatement(sqlSelectDriver)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Driver driver = new Driver(rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("wage"),
                        rs.getInt("carnumber"),
                        rs.getInt("handicap"),
                        rs.getInt("pointsonseason"),
                        rs.getInt("id")
                );
                drivers.add(driver);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return drivers;
    }

    public static int GetDriverIdByCarNumber(ConnectDB db, int carNumber) {
        String sqlSelectDriver = "select d.id " +
                "from \"TeamMembers\" tm " +
                "join \"Drivers\" d " +
                "on tm.id = d.id_member " +
                "where d.carnumber = ?";

        int idDriver = 0;

        try (PreparedStatement ps = db.getConnection().prepareStatement(sqlSelectDriver)) {

            ps.setInt(1, carNumber);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                idDriver = rs.getInt("id");
            }


        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return idDriver;
    }

    public static void UpdatePointsOnSeason(ConnectDB db, int carNumber, int points){
        String sqlUpdatePoints = "UPDATE \"Drivers\" SET POINTSONSEASON = POINTSONSEASON + ? WHERE CARNUMBER = ?";

        try(PreparedStatement ps = db.getConnection().prepareStatement(sqlUpdatePoints)){
            ps.setInt(1, points);
            ps.setInt(2, carNumber);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
