package battleship.view;

import battleship.domain.Territory;
import battleship.domain.army.*;
import battleship.domain.cell.*;

import java.util.Map;

public class TerritoryPresenter {
    private Map<Class, Character> alleyViews = Map.of(
            Sea.class, '~',
            Missed.class, 'M',
            Hited.class, 'X',
            AircraftCarrier.class, 'O',
            BattleShip.class, 'O',
            Cruiser.class, 'O',
            Submarine.class, 'O',
            Destroyer.class, 'O'
    );

    public void showTerritoryAlleyView(Territory territory) {
        Cell[][] cells = territory.getCells();
        System.out.print("  ");
        for (int j = 0; j < cells[0].length; j++) {
            System.out.print(j + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < cells.length; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < cells[0].length; j++) {
                System.out.print(alleyViews.get(cells[i][j].getClass()) + " ");
            }
            System.out.println();
        }
    }

    /*
    Can only see shots, other cells will be shown as sea
     */
    public void showTerritoryEnemyView(Territory territory) {
        Cell[][] cells = territory.getCells();
        System.out.print("  ");
        for (int j = 0; j < cells[0].length; j++) {
            System.out.print(j + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < cells.length; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < cells[0].length; j++) {
                System.out.print(addFog(alleyViews.get(cells[i][j].getClass())) + " ");
            }
            System.out.println();
        }
    }

    private char addFog(char cellSymbol){
        if(cellSymbol==alleyViews.get(Missed.class) || cellSymbol==alleyViews.get(Hited.class)) return cellSymbol;
        return alleyViews.get(Sea.class);
    }
}
