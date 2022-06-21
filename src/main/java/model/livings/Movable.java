package model.livings;

import model.board.Safari;

import java.util.ArrayList;
import java.util.List;

/** Interfejs odpowiadajacy za ruch obiektow
 *
 */
public interface Movable {

     void move(int x, int y);
     void randomMove();

     /**Pobiera kierunek na osi X
      * @return
      */
     LeftRightDirection getLeftRightDirection();

     void setLeftRightDirection(LeftRightDirection leftRightDirection);

     /**Pobiera kierunek na osi Y
      * @return
      */
     UpDownDirection getUpDownDirection();

     void setUpDownDirection(UpDownDirection upDownDirection);

     /**
      * @return
      */
     boolean hasTarget();

     void findTarget(List<Putable> list);

     Putable getTarget();

     void moveToTarget();

     boolean isInRangeOfTarget();


}
