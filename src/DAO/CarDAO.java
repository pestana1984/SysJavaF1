package DAO;

import Data.ConnectDB;
import Models.Car;
import Models.Driver;
import Models.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CarDAO {

    public static void InsertCar(ConnectDB db, Car car, Team team) {

        String sqlCar = "INSERT INTO \"Cars\" (MODEL, HP, AERODYNAMIC, ID_DRIVER, ID_TEAM) VALUES (?, ?, ?, ?, ?)";
        String sqlDriver = "SELECT id FROM \"Drivers\" WHERE CARNUMBER = ?";
        String sqlTeam = "SELECT id FROM \"Teams\" WHERE NAME = ?";

        try (PreparedStatement psDriver = db.getConnection().prepareStatement(sqlDriver)) {

            psDriver.setInt(1, car.getAccountableDriver().carNumber);

            var rsDriver = psDriver.executeQuery();
            rsDriver.next();

            int idDriver = rsDriver.getInt("id");

            PreparedStatement psTeam = db.getConnection().prepareStatement(sqlTeam);
            psTeam.setString(1, team.getName());

            var rsTeam = psTeam.executeQuery();
            rsTeam.next();

            int idTeam = rsTeam.getInt("id");

            PreparedStatement ps = db.getConnection().prepareStatement(sqlCar);
            ps.setString(1, car.getModel());
            ps.setInt(2, car.getHorsePower());
            ps.setDouble(3, car.getAerodinamicCoeficient());
            ps.setInt(4, idDriver);
            ps.setInt(5, idTeam);

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<Car> GetAllCars(ConnectDB db) {

        String sql = "select c.model, c.hp, c.aerodynamic, " +
                "tm.\"name\", d.carnumber, c.id_team " +
                "from \"Cars\" c " +
                "join \"Drivers\" d " +
                "on c.id_driver = d.id " +
                "join \"TeamMembers\" tm " +
                "on tm.id = d.id_member " +
                "order by c.id_team ASC";

        ArrayList<Car> cars = new ArrayList<>();

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Driver driver = new Driver(rs.getString(4), rs.getInt(5));

                Car car = new Car(rs.getString(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        driver);
                cars.add(car);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return cars;
    }

    public static int GetCarIdByCarNumber(ConnectDB db, int carNumber) {
        String sqlSelectCar = "select c.id " +
                "from \"Cars\" c " +
                "join \"Drivers\" d " +
                "on c.id_driver = d.id " +
                "where d.carnumber = ?";

        int idCar = 0;

        try (PreparedStatement ps = db.getConnection().prepareStatement(sqlSelectCar)) {

            ps.setInt(1, carNumber);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                idCar = rs.getInt("id");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return idCar;
    }

    public static void DeleteCar(ConnectDB db, int idCarDriver) {
        String sql = "DELETE FROM \"Cars\" WHERE ID_DRIVER = ?";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCarDriver);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
