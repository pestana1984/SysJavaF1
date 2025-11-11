package DAO;

import Data.ConnectDB;
import Models.Circuit;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                ps.setInt(1, classification[0]);
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
}
