package battleship.domain.army;

import battleship.BattleShipConfig;
import battleship.domain.cell.Orientation;
import battleship.domain.cell.Ship;

public class BattleShip extends Ship {
    {
        length = BattleShipConfig.BATTLE_SHIP_LENGTH;
    }
    public BattleShip() {
    }
}
