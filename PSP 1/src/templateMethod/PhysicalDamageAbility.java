package templateMethod;

import entities.Scaling;
import entities.Unit;

import java.util.List;

public abstract class PhysicalDamageAbility extends Ability{
    public PhysicalDamageAbility(Unit caster, Unit target, double baseDamage, List<Scaling> abilityScaling) {
        super(caster, target, baseDamage, abilityScaling);
    }

    @Override
    protected double calculateScalingDamage(List<Scaling> abilityScaling, Unit caster, Unit target) {
        double bonusDamage = 0;
        if (abilityScaling != null) {
            for (Scaling scaling: abilityScaling) {
                if (scaling.getStatOwner() == Scaling.StatOwner.CASTER) {
                    bonusDamage += caster.getStat(scaling.getScalingStat()) * scaling.getScaleRatio() * 1.1;
                }
                else {
                    bonusDamage += target.getStat(scaling.getScalingStat()) * scaling.getScaleRatio() * 1.1;
                }
            }
        }
        return bonusDamage;
    }

    @Override
    protected Unit calculateResistanceReduction(Unit caster, Unit target) {
        double armor = target.getArmor();
        armor *= 1 - caster.getPercentageArmorPen();
        armor -= caster.getFlatArmorPen();

        try {
            Unit unit = (Unit) target.clone();
            unit.setArmor(armor);
            return unit;
        }
        catch (CloneNotSupportedException exc) {
            return null;
        }
    }

    @Override
    protected double calculatePostResistDamage(double rawDamage, Unit target) {
        double armor = target.getArmor();
        double multiplier;
        if (armor >= 0) {
            multiplier = 100/(100 + armor);
        }
        else {
            multiplier = 2 - 100/(100 - armor);
        }
        return rawDamage * multiplier;
    }
}
