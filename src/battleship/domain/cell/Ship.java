package battleship.domain.cell;

public class Ship implements Cell{
    protected int length;
    protected Orientation orientation;
    protected int xStart;
    protected int yStart;

    protected Ship() {
    }

    protected Ship(int length, Orientation orientation, int xStart, int yStart) {
        this.length = length;
        this.orientation = orientation;
        this.xStart = xStart;
        this.yStart = yStart;
    }

    public int getLength() {
        return length;
    }

    public boolean getShot() {
        length--;
        return length==0;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "length=" + length +
                ", orientation=" + orientation +
                ", xStart=" + xStart +
                ", yStart=" + yStart +
                '}';
    }
}