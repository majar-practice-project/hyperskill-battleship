package battleship.domain.cell;

public class Position {
    private int x;
    private int y;

    public Position(String label){
        //label example: D6, B12
        this.x = label.charAt(0) - 'A';
        this.y = Integer.parseInt(label.substring(1))-1;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}