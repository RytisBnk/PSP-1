package templateMethod;

import entities.Scaling;
import entities.Unit;

import java.util.List;

public class MagicDamageBasicAttack extends BasicAttack {
    public MagicDamageBasicAttack(Unit caster, Unit target, boolean isCritical) {
        super(caster, target, isCritical);
    }

    @Override
    protected double calculateScalingDamage(List<Scaling> abilityScaling, Unit caster, Unit target) {
        double bonusDamage = 0;
        if (abilityScaling != null) {
            for (Scaling scaling: abilityScaling) {
                if (scaling.getStatOwner() == Scaling.StatOwner.CASTER) {
                    bonusDamage += caster.getStat(scaling.getScalingStat()) * scaling.getScaleRatio() * 0.95;
                }
                else {
                    bonusDamage += target.getStat(scaling.getScalingStat()) * scaling.getScaleRatio() * 0.95;
                }
            }
        }
        return bonusDamage;
    }

    @Override
    protected Unit calculateResistanceReduction(Unit caster, Unit target) {
        double mr = target.getMagicResistance();
        mr = mr * (1 - caster.getPercentageMagicPen());
        mr -= caster.getFlatMagicPen();
        try {
            Unit unit = (Unit)target.clone();
            unit.setMagicResistance(mr);
            return unit;
        }
        catch (CloneNotSupportedException exc) {
            return null;
        }
    }

    @Override
    protected double calculatePostResistDamage(double rawDamage, Unit target) {
        double mr = target.getMagicResistance();
        double multiplier;
        if (mr >= 0) {
            multiplier = 100/(115 + mr);
        }
        else {
            multiplier = 2 - 100/(115 - mr);
        }
        return multiplier * rawDamage;
    }
}
