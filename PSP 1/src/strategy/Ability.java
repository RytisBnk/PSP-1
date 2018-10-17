package strategy;

import entities.Scaling;
import entities.Unit;

import java.util.List;
import java.util.Map;

public class Ability {
    private double baseDamage;
    private double baseEffectDuration;
    private double effectValue;
    private List<Scaling> abilityScaling;
    private Unit caster;
    private Unit target;
    private DamageCalculation calculation;
    private Effect effect;

    public Ability(Unit caster, Unit target, double baseDamage, List<Scaling> abilityScaling) {
        this.caster = caster;
        this.target = target;
        this.baseDamage = baseDamage;
        this.abilityScaling = abilityScaling;
    }

    public double damage() {
        double scaledDamage = baseDamage + calculation.calculateScalingDamage(abilityScaling, caster, target);
        Unit modifiedTarget = calculation.calculateResistanceReduction(caster, target);
        return calculation.calculatePostResistDamage(scaledDamage, modifiedTarget);
    }

    public String applyEffect() {
        if (effect != null) {
            Unit restrictedUnit = effect.restrictActions(target);
            target = restrictedUnit;
            double duration = effect.calculateDuration(baseEffectDuration, target);
            Map<Effect.UnitKey, Unit>  result = effect.applyStatChanges(this);

            return "Effect duration:" + duration + "\n" + "Caster=" + result.get(Effect.UnitKey.CASTER).toString() + "\nTarget=" + result.get(Effect.UnitKey.TARGET).toString();
        }
        else {
            return "Ability has no additional effect";
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

    public List<Scaling> getAbilityScaling() {
        return abilityScaling;
    }

    public void setAbilityScaling(List<Scaling> abilityScaling) {
        this.abilityScaling = abilityScaling;
    }

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

    public void setCalculation(DamageCalculation calculation) {
        this.calculation = calculation;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public double getEffectValue() {
        return effectValue;
    }

    public void setEffectValue(double effectValue) {
        this.effectValue = effectValue;
    }
}
