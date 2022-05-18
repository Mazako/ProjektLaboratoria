package model.livings;

public abstract class Herbivore extends Animal {

    private Plant target;

    public Herbivore(int x, int y, double health, double hunger, int speed) {
        super(x, y, health, hunger, speed);
    }

    public Plant getTarget() {
        return target;
    }

    public void setTarget(Plant target) {
        this.target = target;
    }
}
