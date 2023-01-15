package battleship.domain.army;

import battleship.BattleShipConfig;
import battleship.domain.cell.Ship;

public class Submarine extends Ship {
    {
        length = BattleShipConfig.SUBMARINE_LENGTH;
    }
    public Submarine() {
    }
}
