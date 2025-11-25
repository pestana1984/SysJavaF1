package Services;

import Data.ConnectDB;
import Models.Race;

public interface IRaceService {

    void CreateRace(ConnectDB db);

    void GetAllRaces(ConnectDB db);

    void DeleteRace(ConnectDB db, Race race);
}
