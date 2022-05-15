package model.livings;

public abstract class Herbivore extends Animal {

    private Plant target;

    public Herbivore(double health, double hunger, int speed) {
        super(health, hunger, speed);
    }

    public Plant getTarget() {
        return target;
    }

    public void setTarget(Plant target) {
        this.target = target;
    }
}
