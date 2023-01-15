package battleship;

import battleship.domain.FleetShooter;
import battleship.domain.Territory;
import battleship.domain.army.ArmyBuilder;
import battleship.domain.army.RegularArmy;
import battleship.domain.cell.Ship;
import battleship.view.CommandView;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private static final Controller INSTANCE = new Controller();
    private final CommandView view = new CommandView();
    private final String PLAYER1 = "Player 1";
    private final String PLAYER2 = "Player 2";

    private final Map<String, Territory> players = new HashMap<>();

    private Controller() {
    }

    public static Controller getInstance() {
        return INSTANCE;
    }

    public void start() {
        buildShips();
        startGame();
    }

    private void buildShips() {
        ArmyBuilder builder = new ArmyBuilder(view);
        view.showPlaceShipPrompt(PLAYER1);
        players.put(PLAYER1, builder.build(new RegularArmy(), Comparator.comparing(Ship::getLength).reversed()));
        view.switchPlayer();
        view.showPlaceShipPrompt(PLAYER2);
        players.put(PLAYER2, builder.build(new RegularArmy(), Comparator.comparing(Ship::getLength).reversed()));
    }

    private void startGame() {
        FleetShooter shooter1 = new FleetShooter(players.get(PLAYER2), view);
        FleetShooter shooter2 = new FleetShooter(players.get(PLAYER1), view);

        boolean gameOver;

        do {
            view.switchPlayer();
            view.showTerritoryBoth(players.get(PLAYER2), players.get(PLAYER1));
            view.indicateTurn(PLAYER1);
            gameOver = shooter1.shoot();
            if(gameOver) break;
            view.switchPlayer();
            view.showTerritoryBoth(players.get(PLAYER1), players.get(PLAYER2));
            view.indicateTurn(PLAYER2);
            gameOver = shooter2.shoot();
        } while(!gameOver);
    }
}
