package entities;

public class Unit implements Cloneable{
    private double attackDamage;
    private double abilityPower;
    private double currentHealth;
    private double maxHealth;
    private double flatArmorPen;
    private double flatMagicPen;
    private double armor;
    private double magicResistance;
    private double percentageArmorPen;
    private double percentageMagicPen;
    private double attackSpeed;
    private double movementSpeed;
    private double tenacity;
    private double slowReduction;
    private Actions allowedActions;

    public enum Stat {
        ATTACK_DAMAGE,
        ABILITY_POWER,
        CURRENT_HEALTH,
        MAX_HEALTH,
        FLAT_ARMOR_PEN,
        FLAT_MAGIC_PEN,
        ARMOR,
        MAGIC_RESISTANCE,
        PERCENTAGE_ARMOR_PEN,
        PERCENTAGE_MAGIC_PEN,
        ATTACK_SPEED,
        MOVEMENT_SPEED,
        TENACITY,
        SLOW_REDUCTION,
    }

    public Object clone() throws CloneNotSupportedException{
        super.clone();
        Actions actions = new Actions(
                allowedActions.canMove(),
                allowedActions.canAttack(),
                allowedActions.canUseAbilities(),
                allowedActions.canUseItems()
        );
        Unit newUnit = new Unit(
                this.attackDamage,
                this.abilityPower,
                this.currentHealth,
                this.maxHealth,
                this.flatArmorPen,
                this.flatMagicPen,
                this.armor,
                this.magicResistance,
                this.percentageArmorPen,
                this.percentageMagicPen,
                this.attackSpeed,
                this.movementSpeed,
                this.tenacity,
                this.slowReduction,
                actions
        );
        return newUnit;
    }

    public Unit(double attackDamage,
                double abilityPower,
                double currentHealth,
                double maxHealth,
                double flatArmorPen,
                double flatMagicPen,
                double armor,
                double magicResistance,
                double percentageArmorPen,
                double percentageMagicPen,
                double attackSpeed,
                double movementSpeed,
                double tenacity,
                double slowReduction,
                Actions allowedActions) {
        this.attackDamage = attackDamage;
        this.abilityPower = abilityPower;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.flatArmorPen = flatArmorPen;
        this.flatMagicPen = flatMagicPen;
        this.armor = armor;
        this.magicResistance = magicResistance;
        this.percentageArmorPen = percentageArmorPen;
        this.percentageMagicPen = percentageMagicPen;
        this.attackSpeed = attackSpeed;
        this.movementSpeed = movementSpeed;
        this.tenacity = tenacity;
        this.slowReduction = slowReduction;
        this.allowedActions = allowedActions;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "attackDamage=" + attackDamage +
                ", abilityPower=" + abilityPower +
                ", currentHealth=" + currentHealth +
                ", maxHealth=" + maxHealth +
                ", flatArmorPen=" + flatArmorPen +
                ", flatMagicPen=" + flatMagicPen +
                ", armor=" + armor +
                ", magicResistance=" + magicResistance +
                ", percentageArmorPen=" + percentageArmorPen +
                ", percentageMagicPen=" + percentageMagicPen +
                ", attackSpeed=" + attackSpeed +
                ", movementSpeed=" + movementSpeed +
                ", tenacity=" + tenacity +
                ", slowReduction=" + slowReduction +
                ", allowedActions=" + allowedActions.toString() +
                '}';
    }

    public double getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(double attackDamage) {
        this.attackDamage = attackDamage;
    }

    public double getAbilityPower() {
        return abilityPower;
    }

    public void setAbilityPower(double abilityPower) {
        this.abilityPower = abilityPower;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getFlatArmorPen() {
        return flatArmorPen;
    }

    public void setFlatArmorPen(double flatArmorPen) {
        this.flatArmorPen = flatArmorPen;
    }

    public double getFlatMagicPen() {
        return flatMagicPen;
    }

    public void setFlatMagicPen(double flatMagicPen) {
        this.flatMagicPen = flatMagicPen;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getMagicResistance() {
        return magicResistance;
    }

    public void setMagicResistance(double magicResistance) {
        this.magicResistance = magicResistance;
    }

    public double getPercentageArmorPen() {
        return percentageArmorPen;
    }

    public void setPercentageArmorPen(double percentageArmorPen) {
        this.percentageArmorPen = percentageArmorPen;
    }

    public double getPercentageMagicPen() {
        return percentageMagicPen;
    }

    public void setPercentageMagicPen(double percentageMagicPen) {
        this.percentageMagicPen = percentageMagicPen;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public double getTenacity() {
        return tenacity;
    }

    public void setTenacity(double tenacity) {
        this.tenacity = tenacity;
    }

    public double getSlowReduction() {
        return slowReduction;
    }

    public void setSlowReduction(double slowReduction) {
        this.slowReduction = slowReduction;
    }

    public Actions getAllowedActions() {
        return allowedActions;
    }

    public void setAllowedActions(Actions allowedActions) {
        this.allowedActions = allowedActions;
    }

    public double getStat(Stat stat) {
        switch (stat) {
            case ABILITY_POWER:
                return getAbilityPower();

            case ATTACK_DAMAGE:
                return getAttackDamage();

            case CURRENT_HEALTH:
                return getCurrentHealth();

            case MAX_HEALTH:
                return getMaxHealth();

            case FLAT_ARMOR_PEN:
                return getFlatArmorPen();

            case FLAT_MAGIC_PEN:
                return getFlatMagicPen();

            case ARMOR:
                return getArmor();

            case MAGIC_RESISTANCE:
                return getMagicResistance();

            case PERCENTAGE_ARMOR_PEN:
                return getPercentageArmorPen();

            case PERCENTAGE_MAGIC_PEN:
                return getPercentageMagicPen();

            case ATTACK_SPEED:
                return getAttackSpeed();

            case MOVEMENT_SPEED:
                return getMovementSpeed();

            case TENACITY:
                return getTenacity();

            case SLOW_REDUCTION:
                return getSlowReduction();

            default:
                throw new IllegalArgumentException();
        }
    }

}
