package templateMethod;

import entities.Actions;
import entities.Scaling;
import entities.Unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhysicalDamageExhaustAbility extends PhysicalDamageAbility {
    public PhysicalDamageExhaustAbility(Unit caster, Unit target, double baseDamage, List<Scaling> abilityScaling) {
        super(caster, target, baseDamage, abilityScaling);
    }

    @Override
    protected Unit restrictActions(Unit target) {
        try {
            Unit result = (Unit) target.clone();
            result.setAllowedActions(new Actions(true, true, true, true));
            return result;
        }
        catch (CloneNotSupportedException exc) {
            return null;
        }
    }

    @Override
    protected double calculateDuration(double baseDuration, Unit target) {
        return baseDuration * (1 - target.getSlowReduction());
    }

    @Override
    protected Map<UnitKey, Unit> applyStatChanges(Ability ability) {
        Map<UnitKey, Unit> result = new HashMap<>();
        try {
            Unit modifiedTarget = (Unit) ability.getTarget().clone();
            modifiedTarget.setAttackSpeed(modifiedTarget.getAttackSpeed() *  (1 - ability.getEffectValue()));
            modifiedTarget.setMovementSpeed(modifiedTarget.getMovementSpeed() * (1 - ability.getEffectValue()));
            result.put(UnitKey.CASTER, ability.getCaster());
            result.put(UnitKey.TARGET, modifiedTarget);
            return result;
        }
        catch (CloneNotSupportedException exc) {
            result.put(UnitKey.CASTER, ability.getCaster());
            result.put(UnitKey.TARGET, ability.getTarget());
            return result;
        }
    }
}
