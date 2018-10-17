package templateMethod;

import entities.Scaling;
import entities.Unit;

import java.util.List;

public class TrueDamageBasicAttack extends BasicAttack {
    public TrueDamageBasicAttack(Unit caster, Unit target, boolean isCritical) {
        super(caster, target, isCritical);
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
