package strategy;

import entities.Unit;

import java.util.Map;

public interface Effect {
    enum UnitKey {
        CASTER,
        TARGET
    }
    Unit restrictActions(Unit target);
    double calculateDuration(double baseDuration, Unit target);
    Map<UnitKey, Unit> applyStatChanges(Ability ability);
}
