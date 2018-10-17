package strategy;

import entities.Scaling;
import entities.Unit;

import java.util.List;

public class TrueDamageCalculation implements DamageCalculation {
    @Override
    public double calculateScalingDamage(List<Scaling> abilityScaling, Unit caster, Unit target) {
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
    public Unit calculateResistanceReduction(Unit caster, Unit target) {
        return target;
    }

    @Override
    public double calculatePostResistDamage(double rawDamage, Unit target) {
        return rawDamage;
    }
}
