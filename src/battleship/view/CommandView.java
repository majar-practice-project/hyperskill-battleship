package battleship.view;

import battleship.domain.Territory;

import java.util.Scanner;

public class CommandView {
    private final Scanner scanner = new Scanner(System.in);
    private TerritoryPresenter territoryPresenter = new TerritoryPresenter();

    public void showPlaceShipPrompt(String playerName){
        System.out.printf("%s, place your ships on the game field%n", playerName);
    }
    public void showTerritoryAlleyView(Territory territory){
        clearScreen();
        territoryPresenter.showTerritoryAlleyView(territory);
    }

    public void showTerritoryEnemyView(Territory territory){
        territoryPresenter.showTerritoryEnemyView(territory);
    }

    public String enterShipPos(String shipName, int shipLength){
        System.out.printf("Enter the coordinates of the %s (%d cells):%n", shipName, shipLength);
        return scanner.nextLine();
    }

    public void showWrongLength(String shipName){
        System.out.printf("Error! Wrong length of the %s! Try again:%n", shipName);
    }

    public void showWrongLocation(){
        System.out.println("Error! Wrong ship location! Try again:");
    }

    public void showCollision(){
        System.out.println("Error! You placed it too close to another one. Try again:");
    }


    public String getShot() {
        return scanner.nextLine();
    }

    public void showWrongCoordinate(){
        System.out.println("Error! You entered the wrong coordinates! Try again:");
    }

    public void showMissedShot(){
        System.out.println("You missed!");
    }

    public void showHitShot(){
        System.out.println("You hit a ship!");
    }

    public void showGameWon(){
        System.out.println("You sank the last ship. You won. Congratulations!");
    }
    public void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void switchPlayer(){
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        clearScreen();
    }

    public void indicateTurn(String playerName){
        System.out.printf("%s, it's your turn:%n", playerName);
    }

    public void showTerritoryBoth(Territory territory1, Territory territory2){
        showTerritoryEnemyView(territory1);
        System.out.println("-".repeat(20));
        showTerritoryAlleyView(territory2);
    }

    public void showShipSunk(){
        System.out.println("You sank a ship!");
    }
}
