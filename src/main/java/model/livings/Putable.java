package model.livings;

public interface Putable {


    int getX();
    int getY();

    void setX(int x);
    void setY(int y);

    default double distanceBetween(Putable p) {
        return Math.sqrt(Math.pow(getX() - p.getX(),2) + Math.pow((getY() - p.getY()),2));
    }



}
