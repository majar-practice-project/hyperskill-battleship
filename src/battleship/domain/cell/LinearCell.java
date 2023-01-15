package battleship.domain.cell;

import java.util.List;
import java.util.stream.IntStream;

public class LinearCell {
    private Position start;
    private Position end;

    public LinearCell(Position p1, Position p2) throws InvalidPosException {
        if (p1.getX() != p2.getX() && p1.getY() != p2.getY()) throw new InvalidPosException();
        if (p1.getX() < p2.getX() || p1.getY() < p2.getY()) {
            this.start = p1;
            this.end = p2;
        } else {
            this.start = p2;
            this.end = p1;
        }
    }

    public int getxStart() {
        return start.getX();
    }

    public int getyStart() {
        return start.getY();
    }

    public int getxEnd() {
        return end.getX();
    }

    public int getyEnd() {
        return end.getY();
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }

    public int getLength() {
        return end.getX() - start.getX() + end.getY() - start.getY() + 1;
    }

    public List<Position> getOccupiedPoints(){
        if(start.getX()==end.getX()){
            return IntStream.rangeClosed(start.getY(), end.getY())
                    .mapToObj(i -> new Position(start.getX(), i))
                    .toList();
        } else{
            return IntStream.rangeClosed(start.getX(), end.getX())
                    .mapToObj(i -> new Position(i, start.getY()))
                    .toList();
        }
    }
}
