package templateMethod;

import entities.Actions;
import entities.Scaling;
import entities.Unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhysicalDamageStunAbility extends PhysicalDamageAbility {
    public PhysicalDamageStunAbility(Unit caster, Unit target, double baseDamage, List<Scaling> abilityScaling) {
        super(caster, target, baseDamage, abilityScaling);
    }

    @Override
    protected Unit restrictActions(Unit target) {
        try {
            Unit result = (Unit) target.clone();
            result.setAllowedActions(new Actions(false, false, false, false));
            return result;
        }
        catch (CloneNotSupportedException exc) {
            return null;
        }
    }

    @Override
    protected double calculateDuration(double baseDuration, Unit target) {
        return baseDuration * (1 - target.getTenacity());
    }

    @Override
    protected Map<UnitKey, Unit> applyStatChanges(Ability ability) {
        Map<Ability.UnitKey, Unit> result = new HashMap<>();
        result.put(Ability.UnitKey.CASTER, ability.getCaster());
        result.put(Ability.UnitKey.TARGET, ability.getTarget());
        return result;
    }
}
