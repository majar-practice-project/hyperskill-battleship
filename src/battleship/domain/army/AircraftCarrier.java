package battleship.domain.army;

import battleship.BattleShipConfig;
import battleship.domain.cell.Ship;

public class AircraftCarrier extends Ship {
    {
        length = BattleShipConfig.AIRCRAFT_CARRIER_LENGTH;
    }
    public AircraftCarrier() {
    }
}
