package battleship.domain;

import battleship.BattleShipConfig;
import battleship.domain.cell.*;

import java.util.Arrays;

public class Territory {
    private Cell[][] area;
    private final Sea SEA = Sea.SEA;
    private final Hited HITED = Hited.HITED;
    private final Missed MISSED = Missed.MISSED;
    private int remainingShipCell;

    {
        initTerritory();
    }

    private void initTerritory() {
        area = new Cell[BattleShipConfig.SEA_HEIGHT][BattleShipConfig.SEA_WIDTH];

        for (int i = 0; i < area.length; i++) {
            Arrays.fill(area[i], SEA);
        }
    }

    public Cell[][] getCells(){
        return area;
    }

    public boolean validPos(Position pos){
        if(pos.getX()<0 || pos.getX()>=area.length
                || pos.getY()<0 || pos.getY()>=area[0].length){
            return false;
        }
        return true;
    }

    public boolean checkCollision(LinearCell pos){
        if(pos.getxStart()==pos.getxEnd()){
            for(int i=pos.getyStart();i<=pos.getyEnd();i++){
                if(!checkNeighbors(pos.getxStart(), i)) return false;
            }
        } else{
            for(int i=pos.getxStart();i<=pos.getxEnd();i++){
                if(!checkNeighbors(i, pos.getyStart())) return false;
            }
        }
        return true;
    }

    private boolean checkNeighbors(int x, int y){
        if(area[x][y]!=SEA) return false;
        if(x-1>=0 && area[x-1][y]!=SEA) return false;
        if(x+1<area.length && area[x+1][y]!=SEA) return false;
        if(y-1>=0 && area[x][y-1]!=SEA) return false;
        if(y+1<area[0].length && area[x][y+1]!=SEA) return false;

        return true;
    }

    public void registerShip(Ship ship){
        remainingShipCell+=ship.getLength();
        if(ship.getOrientation() == Orientation.HORIZONTAL){
            for(int i=0; i<ship.getLength(); i++){
                area[ship.getxStart()][ship.getyStart()+i] = ship;
            }
        } else{
            for(int i=0; i<ship.getLength(); i++){
                area[ship.getxStart()+i][ship.getyStart()] = ship;
            }
        }
    }

    public ShotStatus getShot(Position pos){
        ShotStatus status = ShotStatus.HIT;
        boolean hit = area[pos.getX()][pos.getY()] instanceof Ship;
        if(hit){
            if(((Ship) area[pos.getX()][pos.getY()]).getShot()) status = ShotStatus.SUNK;
            area[pos.getX()][pos.getY()] = HITED;
            remainingShipCell--;
        } else if(area[pos.getX()][pos.getY()] == SEA){
            area[pos.getX()][pos.getY()] = MISSED;
            status = ShotStatus.MISSED;
        }
        return status;
    }

    public boolean allShipSunk(){
        return remainingShipCell == 0;
    }
}

