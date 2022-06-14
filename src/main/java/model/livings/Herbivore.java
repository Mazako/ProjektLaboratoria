package model.livings;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Herbivore extends Animal {

    private int hpValue;


    public Herbivore(int x, int y, int health, int speed) {
        super(x, y, health, speed);
    }


    @Override
    public boolean hasTarget() {
        return getTarget() != null;
    }

    @Override
    public void findTarget(List<Putable> list) {
        list.stream()
                .filter(x -> x instanceof Plant)
                .min(Comparator.comparing(this::distanceBetween))
                .ifPresent(x -> this.setTarget((Plant) x));
    }

    @Override
    public Plant getTarget() {
        return (Plant) super.getTarget();
    }

    public void eat(Plant target) {
        setHealth(Math.min(target.getHealValue() + getHealth(), getMaxHp()));
        target.die();

    }

}
