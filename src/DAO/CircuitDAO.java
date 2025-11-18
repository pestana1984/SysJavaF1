package DAO;

import Data.ConnectDB;

import Models.Circuit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CircuitDAO {

    public static int InsertCircuit(ConnectDB db, Circuit circuit) {
        String sqlRace = "INSERT INTO \"Circuits\" (NAME, COUNTRY) VALUES (?, ?)";

        int affectedRows = 0;

        try (PreparedStatement ps = db.getConnection().prepareStatement(sqlRace)) {

            ps.setString(1, circuit.getName());
            ps.setString(2, circuit.getCountry());

            affectedRows = ps.executeUpdate();


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return affectedRows;
    }

    public static ArrayList<Circuit> GetCircuits(ConnectDB db) {
        String sql = "SELECT NAME, COUNTRY, ID FROM \"Circuits\"";

        ArrayList<Circuit> circuits = new ArrayList<>();

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Circuit circuit = new Circuit(rs.getString(1), rs.getString(2), rs.getInt(3));
                circuits.add(circuit);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return circuits;
    }

    public static Circuit GetCircuitByName(ConnectDB db, String name) {
        String sql = "SELECT NAME, COUNTRY, ID FROM \"Circuits\" WHERE NAME = ?";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Circuit(rs.getString(1), rs.getString(2), rs.getInt(3));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            ;
        }
        return null;
    }

    public static void DeleteCircuitByName(ConnectDB db, String name) {
        String sql = "DELETE FROM \"Circuits\" WHERE NAME = ?";

        try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
