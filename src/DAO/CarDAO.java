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

    public static void InsertCar(ConnectDB db) {

        String sqlCar = "INSERT INTO \"Cars\" (MODEL, HP, AERODYNAMIC, ID_DRIVER, ID_TEAM) VALUES (?, ?, ?, ?, ?)";

        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Informe o modelo do carro: ");
            String modelo = sc.nextLine();
            System.out.println("Informe a potencia do carro: ");
            int potencia = sc.nextInt();
            sc.nextLine();

            DriverDAO.GetDrivers(db).forEach(Driver::showInfo);

            System.out.println("Informe o nome do piloto do carro: ");
            String nome = sc.nextLine();

            String sqlDriver = "select d.id from \"Drivers\" d " +
                    "join \"TeamMembers\" tm " +
                    "on d.id_member = tm.id " +
                    "where tm.\"name\" = ?";

            PreparedStatement psDriver = db.getConnection().prepareStatement(sqlDriver);
            psDriver.setString(1, nome);

            var rsDriver = psDriver.executeQuery();
            rsDriver.next();

            int idDriver = rsDriver.getInt("id");

            TeamDAO.GetAllTeams(db).forEach(Team::showTeam);
            System.out.printf("Informe a equipe desejada:");
            String nomeEquipe = sc.nextLine();

            String sqlTeam = "SELECT id FROM \"Teams\" WHERE NAME = ?";
            PreparedStatement psTeam = db.getConnection().prepareStatement(sqlTeam);
            psTeam.setString(1, nomeEquipe);

            var rsTeam = psTeam.executeQuery();
            rsTeam.next();

            int idTeam = rsTeam.getInt("id");

            Car car = new Car(modelo, potencia);

            PreparedStatement ps = db.getConnection().prepareStatement(sqlCar);
            ps.setString(1, modelo);
            ps.setInt(2, potencia);
            ps.setDouble(3,car.getAerodinamicCoeficient());
            ps.setInt(4, idDriver);
            ps.setInt(5, idTeam);

            ps.executeUpdate();
            ps.close();
        }

        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<Car> GetCars(ConnectDB db) {

        String sql = "select c.model, c.hp, c.aerodynamic, tm.\"name\", d.carnumber from \"Cars\" c " +
                "join \"Drivers\" d " +
                "on c.id_driver = d.id " +
                "join \"Teams\" t  " +
                "on c.id_team = t.id " +
                "join \"TeamMembers\" tm " +
                "on tm.id = d.id_member";

        ArrayList<Car> cars = new ArrayList<>();

        try(PreparedStatement ps = db.getConnection().prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();
            while(rs.next()){

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

}
