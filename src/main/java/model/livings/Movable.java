package model.livings;

import java.util.ArrayList;
import java.util.List;

public interface Movable {

     void move(int x, int y);
     void randomMove();

     LeftRightDirection getLeftRightDirection();
     void setLeftRightDirection(LeftRightDirection leftRightDirection);

     UpDownDirection getUpDownDirection();

     void setUpDownDirection(UpDownDirection upDownDirection);

     boolean hasTarget();

     void findTarget(List<Putable> list);

     Putable getTarget();

     void moveToTarget();


}
