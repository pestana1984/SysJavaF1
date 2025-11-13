package DAO;

import Data.ConnectDB;
import Entities.RaceResultResponse;
import Models.Circuit;

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

        for(int i = 0; i < classification.length; i++){
            try(PreparedStatement ps = db.getConnection().prepareStatement(sqlRaceResult)){
                ps.setInt(1, DriverDAO.GetDriverIdByCarNumber(db, classification[i]));
                ps.setInt(2, i+1);
                switch (i+1) {
                    case 1:
                        ps.setInt(3, 25);
                        DriverDAO.UpdatePointsOnSeason(db, classification[i], 25);
                        break;
                    case 2:
                        ps.setInt(3, 18);
                        DriverDAO.UpdatePointsOnSeason(db, classification[i], 18);
                        break;
                    case 3:
                        ps.setInt(3,15);
                        DriverDAO.UpdatePointsOnSeason(db, classification[i], 15);
                        break;
                    case 4:
                        ps.setInt(3,12);
                        DriverDAO.UpdatePointsOnSeason(db, classification[i], 12);
                        break;
                    case 5:
                        ps.setInt(3,10);
                        DriverDAO.UpdatePointsOnSeason(db, classification[i], 10);
                        break;
                    case 6:
                        ps.setInt(3,8);
                        DriverDAO.UpdatePointsOnSeason(db, classification[i], 8);
                        break;
                    case 7:
                        ps.setInt(3,6);
                        DriverDAO.UpdatePointsOnSeason(db, classification[i], 6);
                        break;
                    case 8:
                        ps.setInt(3,4);
                        DriverDAO.UpdatePointsOnSeason(db, classification[i], 4);
                        break;
                    case 9:
                        ps.setInt(3,2);
                        DriverDAO.UpdatePointsOnSeason(db, classification[i], 2);
                        break;
                    case 10:
                        ps.setInt(3,1);
                        DriverDAO.UpdatePointsOnSeason(db, classification[i], 1);
                        break;
                    default:
                        break;
                }
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
