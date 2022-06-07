package model.livings;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public abstract class Carnivore extends Animal {
    private int attackValue;
    private Herbivore target;

    public Carnivore(int x, int y, int health, int hunger, int speed, int attackValue) {
        super(x, y, health, hunger, speed);
        this.attackValue = attackValue;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public Herbivore getTarget() {
        return target;
    }

    public void setTarget(Herbivore target) {
        this.target = target;
    }

    public abstract void attack(Animal animal);

    @Override
    public boolean hasTarget() {
        return target == null;
    }

    @Override
    public void findTarget(List<Putable> list) {
         list.stream()
                 .filter(x -> x instanceof Herbivore)
                 .min(Comparator.comparing(this::distanceBetween))
                 .ifPresent(x -> this.setTarget((Herbivore) x));
    }

    @Override
    public void moveToTarget() {
        if (!hasTarget())
            return;


    }
}
