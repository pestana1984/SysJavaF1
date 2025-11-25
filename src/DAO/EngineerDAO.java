package DAO;

import Data.ConnectDB;
import Models.Engineer;
import Models.TeamBoss;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class EngineerDAO {

    public static void InsertEngineer(ConnectDB db, Engineer engineer) {
        try {
            String sql = "INSERT INTO \"TeamMembers\" (NAME, AGE, WAGE) VALUES (?, ?, ?)";

            PreparedStatement ps = db.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, engineer.getName());
            ps.setInt(2, engineer.getAge());
            ps.setDouble(3, engineer.getWage());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                {
                    if (generatedKeys.next()) {
                        int memberId = generatedKeys.getInt(1);

                        String sqlEngineer = "INSERT INTO \"Engineers\" (SPECIALTY, YEAROFEXPERIENCE, ID_MEMBER) VALUES (?, ?, ?)";

                        PreparedStatement ps2 = db.getConnection().prepareStatement(sqlEngineer);

                        ps2.setString(1, engineer.specialty);
                        ps2.setInt(2, engineer.yearOfExperience);
                        ps2.setInt(3, memberId);

                        int affectedRow = ps2.executeUpdate();
                        if (affectedRow > 0) {
                            System.out.println("Engenheiro cadastrado com sucesso!");
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

    public static Engineer GetEngineerByName(ConnectDB db, String name) {
        String sqlEngineer = "SELECT tm.name, tm.age, tm.wage, e.specialty, e.yearofexperience " +
                                "FROM \"TeamMembers\" tm " +
                                "JOIN \"Engineers\" e " +
                                "on tm.id = e.id_member";

        try (PreparedStatement psBoss = db.getConnection().prepareStatement(sqlEngineer)) {

            psBoss.setString(1, name);
            ResultSet rsEngineer = psBoss.executeQuery();

            Engineer engineer = null;

            while (rsEngineer.next()) {
                engineer = new Engineer(rsEngineer.getString("name"),
                        rsEngineer.getInt("age"),
                        rsEngineer.getDouble("wage"),
                        rsEngineer.getString("specialty"),
                        rsEngineer.getInt("yearofexpirience")
                );
            }

            return engineer;

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static int GetEngineerIdByName(ConnectDB db, String name) {
        String sqlEngineer = "SELECT tb.id FROM \"Engineers\" e " +
                "join \"TeamMembers\" tm " +
                "on tm.id = e.id_member " +
                "WHERE tm.NAME = ?";

        try (PreparedStatement psEngineer = db.getConnection().prepareStatement(sqlEngineer)) {
            psEngineer.setString(1, name);
            ResultSet rsBoss = psEngineer.executeQuery();
            rsBoss.next();

            return rsBoss.getInt("id");
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public static void DeleteEngineer(ConnectDB db, Engineer engineer){
        String sqlEngineer = "DELETE FROM \"Engineers\" WHERE id  = ?";

        String sqlMember = "DELETE FROM \"TeamMembers\" WHERE id = ?";

        int idMember = 0;

        String sqlMemberId = "select id_member from \"Engineers\" e " +
                "join \"TeamMembers\" tm " +
                "ON e.id_member = tm.id" +
                "WHERE e.idmember = ?";

        try(PreparedStatement ps = db.getConnection().prepareStatement(sqlMemberId)){
            ps.setInt(1, GetEngineerIdByName(db, engineer.getName()));
            idMember = ps.executeQuery().getInt("id_member");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        try(PreparedStatement ps = db.getConnection().prepareStatement(sqlEngineer)){
            ps.setInt(1, GetEngineerIdByName(db, engineer.getName()));
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        try(PreparedStatement ps = db.getConnection().prepareStatement(sqlMember)){
            ps.setInt(1, idMember);
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
