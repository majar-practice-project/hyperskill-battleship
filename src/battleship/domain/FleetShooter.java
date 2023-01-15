package battleship.domain;

import battleship.domain.cell.Position;
import battleship.view.CommandView;

public class FleetShooter {
    private final Territory territory;
    private final CommandView view;

    public FleetShooter(Territory territory, CommandView view) {
        this.territory = territory;
        this.view = view;
    }

    public boolean shoot() {
        String input;
        boolean validInput = false;
        do {
            input = view.getShot();
            Position pos = new Position(input);
            if (!territory.validPos(pos)) {
                view.showWrongCoordinate();
                continue;
            }
            ShotStatus hit = territory.getShot(pos);
            view.showTerritoryEnemyView(territory);

            switch (hit) {
                case HIT -> view.showHitShot();
                case SUNK -> {
                    if (territory.allShipSunk()) {
                        view.showGameWon();
                    } else {
                        view.showShipSunk();
                    }
                }
                case MISSED -> view.showMissedShot();
            }

            validInput = true;
        } while (!validInput);
        return territory.allShipSunk();
    }
}
