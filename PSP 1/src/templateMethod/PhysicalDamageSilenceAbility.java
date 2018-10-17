package templateMethod;

import entities.Actions;
import entities.Scaling;
import entities.Unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhysicalDamageSilenceAbility extends PhysicalDamageAbility {
    public PhysicalDamageSilenceAbility(Unit caster, Unit target, double baseDamage, List<Scaling> abilityScaling) {
        super(caster, target, baseDamage, abilityScaling);
    }

    @Override
    protected Unit restrictActions(Unit target) {
        try {
            Unit result = (Unit) target.clone();
            result.setAllowedActions(new Actions(true, true, false, false));
            return result;
        }
        catch (CloneNotSupportedException exc) {
            return null;
        }
    }

    @Override
    protected double calculateDuration(double baseDuration, Unit target) {
        return baseDuration;
    }

    @Override
    protected Map<UnitKey, Unit> applyStatChanges(Ability ability) {
        Map<UnitKey, Unit> result = new HashMap<>();
        result.put(UnitKey.CASTER, ability.getCaster());
        result.put(UnitKey.TARGET, ability.getTarget());
        return result;
    }
}
