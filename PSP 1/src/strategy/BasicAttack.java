package strategy;

import entities.Unit;

public class BasicAttack {
    private boolean isCritical;
    private Unit caster;
    private Unit target;

    private DamageCalculation calculation;

    public BasicAttack(Unit caster, Unit target, boolean isCritical) {
        this.isCritical = isCritical;
        this.caster = caster;
        this.target = target;
    }

    public double damage() {
        Unit modifiedTarget = calculation.calculateResistanceReduction(caster, target);
        return calculation.calculatePostResistDamage(baseDamage(), modifiedTarget);
    }

    private double baseDamage() {
        if (isCritical) {
            return caster.getAttackDamage() * 2;
        }
        else {
            return caster.getAttackDamage();
        }
    }

    public Unit getCaster() {
        return caster;
    }

    public void setCaster(Unit caster) {
        this.caster = caster;
    }

    public Unit getTarget() {
        return target;
    }

    public void setTarget(Unit target) {
        this.target = target;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    public void setCalculation(DamageCalculation calculation) {
        this.calculation = calculation;
    }
}
