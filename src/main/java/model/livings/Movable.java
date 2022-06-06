package model.livings;

public interface Movable {

     void move(int x, int y);
     void randomMove();

     LeftRightDirection getLeftRightDirection();
     void setLeftRightDirection(LeftRightDirection leftRightDirection);

     UpDownDirection getUpDownDirection();

     void setUpDownDirection(UpDownDirection upDownDirection);

     boolean hasTarget();


}
