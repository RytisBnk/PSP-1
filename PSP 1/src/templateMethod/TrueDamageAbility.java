package templateMethod;

import entities.Scaling;
import entities.Unit;

import java.util.List;

public abstract class TrueDamageAbility extends Ability {
    public TrueDamageAbility(Unit caster, Unit target, double baseDamage, List<Scaling> abilityScaling) {
        super(caster, target, baseDamage, abilityScaling);
    }

    @Override
    protected double calculateScalingDamage(List<Scaling> abilityScaling, Unit caster, Unit target) {
        double bonusDamage = 0;
        if (abilityScaling != null) {
            for (Scaling scaling: abilityScaling) {
                if (scaling.getStatOwner() == Scaling.StatOwner.CASTER) {
                    bonusDamage += caster.getStat(scaling.getScalingStat()) * scaling.getScaleRatio();
                }
                else {
                    bonusDamage += target.getStat(scaling.getScalingStat()) * scaling.getScaleRatio();
                }
            }
        }
        return bonusDamage;
    }

    @Override
    protected Unit calculateResistanceReduction(Unit caster, Unit target) {
        return target;
    }

    @Override
    protected double calculatePostResistDamage(double rawDamage, Unit target) {
        return rawDamage;
    }
}
