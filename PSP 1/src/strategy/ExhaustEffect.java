package strategy;

import entities.Actions;
import entities.Unit;

import java.util.HashMap;
import java.util.Map;

public class ExhaustEffect implements Effect {
    @Override
    public Unit restrictActions(Unit target) {
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
    public double calculateDuration(double baseDuration, Unit target) {
        return baseDuration * (1 - target.getSlowReduction());
    }

    @Override
    public Map<UnitKey, Unit> applyStatChanges(Ability ability) {
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
