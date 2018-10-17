package templateMethod;

import entities.Scaling;
import entities.Unit;

import java.util.List;

public abstract class BasicAttack {
    private boolean isCritical;
    private Unit caster;
    private Unit target;

    public BasicAttack(Unit caster, Unit target, boolean isCritical) {
        this.isCritical = isCritical;
        this.caster = caster;
        this.target = target;
    }

    public double damage() {
        Unit modifiedTarget = calculateResistanceReduction(caster, target);
        return calculatePostResistDamage(baseDamage(), modifiedTarget);
    }

    private double baseDamage() {
        if (isCritical) {
            return caster.getAttackDamage() * 2;
        }
        else {
            return caster.getAttackDamage();
        }
    }

    protected abstract double calculateScalingDamage(List<Scaling> scalingStats, Unit caster, Unit target);
    protected abstract Unit calculateResistanceReduction(Unit caster, Unit target);
    protected abstract double calculatePostResistDamage(double rawDamage, Unit target);

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
}

