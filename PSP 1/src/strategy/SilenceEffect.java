package strategy;

import entities.Actions;
import entities.Unit;

import java.util.HashMap;
import java.util.Map;

public class SilenceEffect implements Effect {
    @Override
    public Unit restrictActions(Unit target) {
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
    public double calculateDuration(double baseDuration, Unit target) {
        return baseDuration;
    }

    @Override
    public Map<UnitKey, Unit> applyStatChanges(Ability ability) {
        Map<UnitKey, Unit> result = new HashMap<>();
        result.put(UnitKey.CASTER, ability.getCaster());
        result.put(UnitKey.TARGET, ability.getTarget());
        return result;
    }
}
