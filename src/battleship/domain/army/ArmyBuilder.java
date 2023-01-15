package battleship.domain.army;

import battleship.domain.Territory;
import battleship.domain.cell.*;
import battleship.view.CommandView;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArmyBuilder {
    private final Map<Class<? extends Ship>, String> shipNames = Map.of(
            AircraftCarrier.class, "Aircraft carrier",
            BattleShip.class, "Battleship",
            Cruiser.class, "Cruiser",
            Submarine.class, "Submarine",
            Destroyer.class, "Destroyer"
    );
    private CommandView view;

    public ArmyBuilder(CommandView view) {
        this.view = view;
    }

    public Territory build(RegularArmy army, Comparator<Ship> comparator){
        Territory territory = new Territory();
        view.showTerritoryAlleyView(territory);
        List<Ship> ships = army.getShips();
        ships.stream()
                .sorted(comparator)
                .forEach(ship -> {
                    placeShip(ship, territory);
                    view.showTerritoryAlleyView(territory);
                });
        return territory;
    }

    private void placeShip(Ship ship, Territory territory){
        Pattern pattern = Pattern.compile("^([A-Z]\\d+) ([A-Z]\\d+)$");
        String input;
        boolean validInput = false;
        do{
            input = view.enterShipPos(shipNames.get(ship.getClass()),ship.getLength());
            Matcher matcher = pattern.matcher(input);
            try{
                if(matcher.matches()){
                    LinearCell line = new LinearCell(new Position(matcher.group(1)),
                            new Position(matcher.group(2)));
                    if(!territory.validPos(line.getStart()) && !territory.validPos(line.getEnd())){
                        view.showWrongLocation();
                        continue;
                    }
                    if(line.getLength()!=ship.getLength()) {
                        view.showWrongLength(shipNames.get(ship.getClass()));
                        continue;
                    }

                    if(!territory.checkCollision(line)){
                        view.showCollision();
                        continue;
                    }

                    // valid condition
                    // possibly composition? ship has positioningBehavior
                    ship.setxStart(line.getxStart());
                    ship.setyStart(line.getyStart());
                    ship.setOrientation(line.getxStart()==line.getxEnd()
                            ? Orientation.HORIZONTAL
                            : Orientation.VERTICAL);
                    territory.registerShip(ship);
                    validInput = true;
                }
            } catch (InvalidPosException e) {
                view.showWrongLocation();
            }
        }while(!validInput);
    }
}
