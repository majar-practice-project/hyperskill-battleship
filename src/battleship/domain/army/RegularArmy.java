package battleship.domain.army;

import battleship.domain.cell.Ship;

import java.util.ArrayList;
import java.util.List;

public class RegularArmy {
    private List<Ship> ships = new ArrayList<>();

    {
        //factory maybe?
        ships.add(new AircraftCarrier());
        ships.add(new BattleShip());
        ships.add(new Submarine());
        ships.add(new Cruiser());
        ships.add(new Destroyer());
    }
    public List<Ship> getShips() {
        return ships;
    }
}
