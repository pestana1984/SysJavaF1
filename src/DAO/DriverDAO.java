package DAO;

import Data.ConnectDB;
import Models.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverDAO {
    public static void InsertDriver(ConnectDB db, Driver driver) {

        String sqlMember = "INSERT INTO \"TeamMembers\" (NAME, AGE, WAGE) VALUES (?, ?, ?)";

        try {

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


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return idDriver;
    }

    public static Driver GetDriverByName(ConnectDB db, String name) {
        String sqlSelectDriver = "select tm.name, tm.age, tm.wage, d.carnumber, d.handicap, d.pointsonseason, d.id " +
                "from \"TeamMembers\" tm " +
                "join \"Drivers\" d " +
                "on tm.id = d.id_member " +
                "WHERE tm.name = ?";

        Driver driver = null;

        try (PreparedStatement ps = db.getConnection().prepareStatement(sqlSelectDriver)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                driver = new Driver(rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("wage"),
                        rs.getInt("carnumber"),
                        rs.getInt("handicap"),
                        rs.getInt("pointsonseason"),
                        rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return driver;
    }

    public static void UpdatePointsOnSeason(ConnectDB db, int carNumber, int points) {
        String sqlUpdatePoints = "UPDATE \"Drivers\" SET POINTSONSEASON = POINTSONSEASON + ? WHERE CARNUMBER = ?";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sqlUpdatePoints)) {
            ps.setInt(1, points);
            ps.setInt(2, carNumber);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void DeleteDriver(ConnectDB db, Driver driver) {
        String sqlDriver = "DELETE FROM \"Drivers\" WHERE carnumber  = ?";

        String sqlMember = "DELETE FROM \"TeamMembers\" WHERE id = ?";

        String sqlMemberId = "select id_member from \"Drivers\" d " +
                "join \"TeamMembers\" tm " +
                "ON d.id_member = tm.id" +
                "WHERE d.carnumber = ?";

        int idMember = 0;

        try (PreparedStatement ps = db.getConnection().prepareStatement(sqlMemberId)) {
            ps.setInt(1, driver.getCarNumber());
            idMember = ps.executeQuery().getInt("id_member");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try (PreparedStatement ps = db.getConnection().prepareStatement(sqlDriver)) {
            ps.setInt(1, driver.getCarNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try (PreparedStatement ps = db.getConnection().prepareStatement(sqlMember)) {
            ps.setInt(1, idMember);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
