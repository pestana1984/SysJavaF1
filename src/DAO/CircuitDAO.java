package DAO;

import Data.ConnectDB;

import Models.Circuit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CircuitDAO {

    public static void InsertCircuit(ConnectDB db) {
        String sqlRace = "INSERT INTO \"Circuits\" (NAME, COUNTRY) VALUES (?, ?)";

        try(PreparedStatement ps = db.getConnection().prepareStatement(sqlRace)){

            Scanner sc = new Scanner(System.in);

            System.out.println("Informe o nome do circuito: ");
            String nome = sc.nextLine();
            System.out.println("Informe o pais do circuito: ");
            String pais = sc.nextLine();

            ps.setString(1, nome);
            ps.setString(2, pais);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Circuito inserido com sucesso!");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public static ArrayList<Circuit> GetCircuits(ConnectDB db) {
        String sql = "SELECT NAME, COUNTRY, ID FROM \"Circuits\"";

        ArrayList<Circuit> circuits = new ArrayList<>();

        try(PreparedStatement ps = db.getConnection().prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
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

        try(PreparedStatement ps = db.getConnection().prepareStatement(sql)){

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Circuit(rs.getString(1), rs.getString(2), rs.getInt(3));

        } catch (SQLException e) {
            System.err.println(e.getMessage());;
        }
        return null;
    }
}
