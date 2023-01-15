package battleship.domain.army;

import battleship.BattleShipConfig;
import battleship.domain.cell.Ship;

public class Cruiser extends Ship {
    {
        length = BattleShipConfig.CRUISER_LENGTH;
    }

    public Cruiser() {
    }
}
