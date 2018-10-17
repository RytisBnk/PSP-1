package templateMethod;

import entities.Scaling;
import entities.Unit;

import java.util.List;
import java.util.Map;

public abstract class Ability {
    protected double baseDamage;
    protected double baseEffectDuration;
    protected double effectValue;
    protected Unit caster;
    protected Unit target;
    protected List<Scaling> abilityScaling;

    enum UnitKey {
        CASTER,
        TARGET
    }

    public Ability(Unit caster, Unit target, double baseDamage, List<Scaling> abilityScaling) {
        this.caster = caster;
        this.target = target;
        this.baseDamage = baseDamage;
        this.abilityScaling = abilityScaling;
    }

    public final double damage() {
        double scaledDamage = baseDamage + calculateScalingDamage(abilityScaling, caster, target);
        Unit modifiedTarget = calculateResistanceReduction(caster, target);
        return calculatePostResistDamage(scaledDamage, modifiedTarget);
    }

    public final String applyEffect() {
        if (baseEffectDuration > 0) {
            target = restrictActions(target);
            double duration = calculateDuration(baseEffectDuration, target);
            Map<UnitKey, Unit>  result = applyStatChanges(this);

            return "Effect duration:" + duration + "\n" + "Caster=" + result.get(UnitKey.CASTER).toString() + "\nTarget=" + result.get(UnitKey.TARGET).toString();
        }
        else {
            return "Ability has no additional effect";
        }
    }

    protected abstract double calculateScalingDamage(List<Scaling> scalingStats, Unit caster, Unit target);
    protected abstract Unit calculateResistanceReduction(Unit caster, Unit target);
    protected abstract double calculatePostResistDamage(double rawDamage, Unit target);

    protected abstract Unit restrictActions(Unit target);
    protected abstract double calculateDuration(double baseDuration, Unit target);
    protected abstract Map<UnitKey, Unit> applyStatChanges(Ability ability);

    public double getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(double baseDamage) {
        this.baseDamage = baseDamage;
    }

    public double getBaseEffectDuration() {
        return baseEffectDuration;
    }

    public void setBaseEffectDuration(double baseEffectDuration) {
        this.baseEffectDuration = baseEffectDuration;
    }

    public double getEffectValue() {
        return effectValue;
    }

    public void setEffectValue(double effectValue) {
        this.effectValue = effectValue;
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

    public List<Scaling> getAbilityScaling() {
        return abilityScaling;
    }

    public void setAbilityScaling(List<Scaling> abilityScaling) {
        this.abilityScaling = abilityScaling;
    }
}
