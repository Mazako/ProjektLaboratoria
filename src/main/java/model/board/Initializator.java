package model.board;

public class Initializator {

    /** Zdrowie lwa
     *
     */
    //lew
    private int lionHealth = 200;
    /** Glod lwa
     *
     */
    private int lionHunger = 100;
    /** Predkosc lwa
     *
     */
    private int lionSpeed = 2;
    /** Wartosc ataku lwa
     *
     */
    private int lionAttackValue =150;
    /** Zdrowie zyrafy
     *
     */
    //żyrafa
    private int giraffeHealth = 300;
    /** Glod zyrafy
     *
     */
    private int giraffeHunger = 100;
    /** Predkosc zyrafy
     *
     */
    private int giraffeSpeed = 2;

    /** Zdrowie weza
     *
     */
    //wąż
    private int snakeHealth = 150;
    /** Glod weza
     *
     */
    private int snakeHunger = 100;
    /** Predkosc weza
     *
     */
    private int snakeSpeed = 3;
    /** Wartosc ataku weza
     *
     */
    private int snakeAttackValue = 50;

    /** Zdrowie zebry
     *
     */
    //zebra
    private int zebraHealth = 250;
    /** Glod zebry
     *
     */
    private int zebraHunger = 100;
    /** Predkosc zebry
     *
     */
    private int zebraSpeed = 3;

    /** Zdrowie na jeden tik
     *
     */
    private int HungerPerTick;

    //roślina

    /** Minimalna wartosc leczenia
     *
     */
    private int healValueMin = 50;
    /** Maksymalna wartosc leczenia
     *
     */
    private int healValueMax = 70;
    /** Minimalny głód
     *
     */
    private int hungerValueMin = 80;
    /** Maksymalny głód
     *
     */
    private int hungerValueMax = 100;

    /** Ilosc roslin na jeden tik
     *
     */
    private int plantsPerTick = 2;

    /** Co ile tikow ma byc spawniona roslina
     *
     */
    private int ticksPerPlantSpawn = 100;

    //ilości startowe

    /** Ilosc lwow
     *
     */
    private int lions = 10;
    /** Ilosc wezy
     *
     */
    private int snakes = 10;
    /** Ilosc zebr
     *
     */
    private int zebras = 10;
    /** Ilosc zyraf
     *
     */
    private int giraffes = 10;
    /** Ilosc roslin
     *
     */
    private int plants = 10;

    /** Pokazuje odleglosc
     *
     */
    private boolean showDistances;
    /** Czas odnowienia ataku
     *
     */
    private int AttackCooldown;


    /** getter na zdrowie lwa
     * @return
     */
    public int getLionHealth() {
        return lionHealth;
    }

    /** setter na zdrowie lwa
     * @param lionHealth zdrowie lwa
     */
    public void setLionHealth(int lionHealth) {
        this.lionHealth = lionHealth;
    }

    /** getter na glod lwa
     * @return
     */
    public int getLionHunger() {
        return lionHunger;
    }

    /** setter na glod lwa
     * @param lionHunger glod lwa
     */
    public void setLionHunger(int lionHunger) {
        this.lionHunger = lionHunger;
    }

    /** getter na predkosc lwa
     * @return
     */
    public int getLionSpeed() {
        return lionSpeed;
    }

    /** setter na predkosc lwa
     * @param lionSpeed predkosc lwa
     */
    public void setLionSpeed(int lionSpeed) {
        this.lionSpeed = lionSpeed;
    }

    /** getter na wartosc ataku lwa
     * @return
     */
    public int getLionAttackValue() {
        return lionAttackValue;
    }

    /** setter na wartosc ataku lwa
     * @param lionAttackValue wartosc ataku lwa
     */
    public void setLionAttackValue(int lionAttackValue) {
        this.lionAttackValue = lionAttackValue;
    }

    /** getter na zdrowie zyrafy
     * @return
     */
    public int getGiraffeHealth() {
        return giraffeHealth;
    }

    /** setter na zdrowie zyrafy
     * @param giraffeHealth zdrowie zyrafy
     */
    public void setGiraffeHealth(int giraffeHealth) {
        this.giraffeHealth = giraffeHealth;
    }

    /** getter na glod zyrafy
     * @return
     */
    public int getGiraffeHunger() {
        return giraffeHunger;
    }

    /** setter na zdrowie zyrafy
     * @param giraffeHunger glod zyrafy
     */
    public void setGiraffeHunger(int giraffeHunger) {
        this.giraffeHunger = giraffeHunger;
    }

    /** getter na szybkosc zyrafy
     * @return
     */
    public int getGiraffeSpeed() {
        return giraffeSpeed;
    }

    /** setter na predkosc zyrafy
     * @param giraffeSpeed predkosc zyrafy
     */
    public void setGiraffeSpeed(int giraffeSpeed) {
        this.giraffeSpeed = giraffeSpeed;
    }

    /** getter na zdrowie weza
     * @return
     */
    public int getSnakeHealth() {
        return snakeHealth;
    }

    /** setter na zdrowie weza
     * @param snakeHealth zdrowie weza
     */
    public void setSnakeHealth(int snakeHealth) {
        this.snakeHealth = snakeHealth;
    }

    /** getter na glod weza
     * @return
     */
    public int getSnakeHunger() {
        return snakeHunger;
    }

    /** setter na glod weza
     * @param snakeHunger glod weza
     */
    public void setSnakeHunger(int snakeHunger) {
        this.snakeHunger = snakeHunger;
    }

    /** getter na predkosc weza
     * @return
     */
    public int getSnakeSpeed() {
        return snakeSpeed;
    }

    /** setter na predkosc weza
     * @param snakeSpeed
     */
    public void setSnakeSpeed(int snakeSpeed) {
        this.snakeSpeed = snakeSpeed;
    }

    /** getter na wartosc ataku weza
     * @return
     */
    public int getSnakeAttackValue() {
        return snakeAttackValue;
    }

    /** setter na wartosc ataku weza
     * @param snakeAttackValue wartosc ataku weza
     */
    public void setSnakeAttackValue(int snakeAttackValue) {
        this.snakeAttackValue = snakeAttackValue;
    }

    /** getter na zdrowie zebry
     * @return
     */
    public int getZebraHealth() {
        return zebraHealth;
    }

    /** setter na zdrowie zebry
     * @param zebraHealth zdrowie zebry
     */
    public void setZebraHealth(int zebraHealth) {
        this.zebraHealth = zebraHealth;
    }


    /** setter na glod zebry
     * @param zebraHunger glod zebry
     */
    public void setZebraHunger(int zebraHunger) {
        this.zebraHunger = zebraHunger;
    }

    /** getter na predkosc zebry
     * @return
     */
    public int getZebraSpeed() {
        return zebraSpeed;
    }

    /** setter na predkosc zebry
     * @param zebraSpeed predkosc zebry
     */
    public void setZebraSpeed(int zebraSpeed) {
        this.zebraSpeed = zebraSpeed;
    }

    /** getter na leczenie minimalne
     * @return
     */
    public int getHealValueMin() {
        return healValueMin;
    }

    /** setter na leczenie minimalne
     * @param healValueMin minimalne leczenie
     */
    public void setHealValueMin(int healValueMin) {
        this.healValueMin = healValueMin;
    }

    /** getter na leczenie maksymalne
     * @return
     */
    public int getHealValueMax() {
        return healValueMax;
    }

    /** setter na leczenie maksymalne
     * @param healValueMax maksymalne leczenie
     */
    public void setHealValueMax(int healValueMax) {
        this.healValueMax = healValueMax;
    }

    /** getter na glod minimalny
     * @return
     */
    public int getHungerValueMin() {
        return hungerValueMin;
    }

    /** setter na glod minimalny
     * @param hungerValueMin minimalny glod
     */
    public void setHungerValueMin(int hungerValueMin) {
        this.hungerValueMin = hungerValueMin;
    }

    /** getter na glod maksymalny
     * @return
     */
    public int getHungerValueMax() {
        return hungerValueMax;
    }

    /** setter na glod maksymalny
     * @param hungerValueMax maksymalny glod
     */
    public void setHungerValueMax(int hungerValueMax) {
        this.hungerValueMax = hungerValueMax;
    }

    /** getter na ilosc lwow
     * @return
     */
    public int getLions() {
        return lions;
    }

    /** setter na ilosc lwow
     * @param lions lwy
     */
    public void setLions(int lions) {
        this.lions = lions;
    }

    /** getter na ilosc wezy
     * @return
     */
    public int getSnakes() {
        return snakes;
    }

    /** setter na ilosc wezy
     * @param snakes weze
     */
    public void setSnakes(int snakes) {
        this.snakes = snakes;
    }

    /** getter na ilosc zebr
     * @return
     */
    public int getZebras() {
        return zebras;
    }

    /** setter na ilosc zebr
     * @param zebras zebry
     */
    public void setZebras(int zebras) {
        this.zebras = zebras;
    }

    /** getter na ilosc zyraf
     * @return
     */
    public int getGiraffes() {
        return giraffes;
    }

    /** setter na ilosc zyraf
     * @param giraffes zyrafy
     */
    public void setGiraffes(int giraffes) {
        this.giraffes = giraffes;
    }

    /** getter na ilosc roslin
     * @return
     */
    public int getPlants() {
        return plants;
    }

    /** setter na ilosc roslin
     * @param plants rosliny
     */
    public void setPlants(int plants) {
        this.plants = plants;
    }

    /** getter na ilosc tikow co ile ma byc spawniona roslina
     * @return
     */
    public int getPlantsPerTick() {
        return plantsPerTick;
    }

    /** setter na ilosc tikow co ile ma byc spawniona roslina
     * @param plantsPerTick roslin na tik
     */
    public void setPlantsPerTick(int plantsPerTick) {
        this.plantsPerTick = plantsPerTick;
    }

    /** getter na ilosc tikow na rosline
     * @return
     */
    public int getTicksPerPlantSpawn() {
        return ticksPerPlantSpawn;
    }

    /** setter na ilosc tikow na rosline
     * @param ticksPerPlantSpawn
     */
    public void setTicksPerPlantSpawn(int ticksPerPlantSpawn) {
        this.ticksPerPlantSpawn = ticksPerPlantSpawn;
    }

    /** getter na glod
     * @return
     */
    public int getHungerPerTick() {
        return  HungerPerTick;
    }

    /** Czy jest w odpowiedniej odleglosci
     * @return
     */
    public boolean isShowDistances() {
        return showDistances;
    }

    /** getter na czas odnowienia ataku
     * @return
     */
    public int getAttackCooldown() {
        return  AttackCooldown;
    }

    /** setter na glod
     * @param hungerPerTick
     */
    public void setHungerPerTick(int hungerPerTick) {
        HungerPerTick = hungerPerTick;
    }

    /** setter na to czy jest w odpowiedniej odleglosci
     * @param showDistances
     */
    public void setShowDistances(boolean showDistances) {
        this.showDistances = showDistances;
    }

    /** setter na czas odnowienia ataku
     * @param attackCooldown
     */
    public void setAttackCooldown(int attackCooldown) {
        AttackCooldown = attackCooldown;
    }
}
