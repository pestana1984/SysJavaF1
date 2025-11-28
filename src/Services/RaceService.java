package Services;

import DAO.CarDAO;
import DAO.CircuitDAO;
import DAO.RaceResultDAO;
import DAO.TeamDAO;
import Data.ConnectDB;
import Entities.RaceResultResponse;
import Models.Circuit;
import Models.Race;
import Models.Team;

import java.util.ArrayList;
import java.util.Scanner;


public class RaceService implements IRaceService {


    @Override
    public void CreateRace(ConnectDB db) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o nome do circuito:");
        String nome = sc.nextLine();

        Circuit circuit = CircuitDAO.GetCircuitByName(db, nome);

        Race race = new Race(CarDAO.GetAllCars(db),
                circuit.getCountry(),
                circuit.getName(),
                TeamDAO.GetAllTeams(db));

        race.startRace();

        for (Team team : race.teams) {
            TeamDAO.UpdatePointsOnSeason(db, team);
        }

        RaceResultDAO.InsertRaceResult(db, race.getClassification(), circuit.getName());
    }

    @Override
    public void GetAllRaces(ConnectDB db) {

        System.out.println(RaceResultDAO.GetRaceResults(db, "Albert Park"));
    }

    @Override
    public void DeleteRace(ConnectDB db, Race race) {

    }
}
