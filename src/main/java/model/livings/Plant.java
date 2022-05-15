package model.livings;

public class Plant implements Putable {

    public double healValue;
    public double hungerValue;

    public Plant(double healValue, double hungerValue) {
        this.healValue = healValue;
        this.hungerValue = hungerValue;
    }

    public double getHealValue() {
        return healValue;
    }

    public void setHealValue(double healValue) {
        this.healValue = healValue;
    }

    public double getHungerValue() {
        return hungerValue;
    }

    public void setHungerValue(double hungerValue) {
        this.hungerValue = hungerValue;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}
