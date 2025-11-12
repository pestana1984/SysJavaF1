package DAO;

import Data.ConnectDB;
import Entities.RaceResultResponse;
import Models.Circuit;
import Models.RaceResult;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RaceResultDAO {

    public static void InsertRaceResult(ConnectDB db, int[] classification, String circuitName) {
        String sqlRaceResult = "INSERT INTO \"RacesResults\" " +
                "(ID_DRIVER, POSITION, POINTS, ID_CIRCUIT) " +
                "VALUES (?, ?, ?, ?)";

        Circuit circuit = CircuitDAO.GetCircuitByName(db, circuitName);

        if(circuit == null)
            return;

        //TODO: Corrigir a busca do ID do piloto pelo numero do carro

        for(int i = 0; i < classification.length; i++){
            try(PreparedStatement ps = db.getConnection().prepareStatement(sqlRaceResult)){
                ps.setInt(1, DriverDAO.GetDriverIdByCarNumber(db, classification[i]));
                ps.setInt(2, i+1);
                ps.setInt(3, i+10);
                ps.setInt(4, circuit.getId());

                ps.executeUpdate();
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public static ArrayList<RaceResultResponse> GetRaceResults(ConnectDB db, String circuitName){

        String sqlRaceResult = "select c.\"name\", c.country, d.carnumber, tm.\"name\", rr.\"position\", rr.points " +
                                "from \"RacesResults\" rr " +
                                "join \"Drivers\" d " +
                                "on rr.id_driver = d.id " +
                                "join \"TeamMembers\" tm " +
                                "on d.id_member = tm.id " +
                                "join \"Circuits\" c " +
                                "on rr.id_circuit = c.id " +
                                "where c.\"name\" = ?";

        ArrayList<RaceResultResponse> rr = new ArrayList<RaceResultResponse>();

        try(PreparedStatement ps = db.getConnection().prepareStatement(sqlRaceResult)){

            ps.setString(1, circuitName);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Circuit circuit = new Circuit(rs.getString(1), rs.getString(2));

                RaceResultResponse result = new RaceResultResponse(circuit, rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6)
                );

                rr.add(result);
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }

        return rr;

    }
}
