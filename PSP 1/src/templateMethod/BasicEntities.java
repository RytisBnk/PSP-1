package templateMethod;

import entities.Scaling;
import entities.Unit;

import java.util.List;

public class BasicEntities {
    public static Ability getBasicAbility(Unit caster, Unit target, double baseDamage, List<Scaling> abilityScaling) {
        return new MagicDamageExhaustAbility(caster, target, baseDamage, abilityScaling);
    }

    public static BasicAttack getStandardBasicAttack(Unit caster, Unit target, boolean isCritical) {
        return new PhysicalDamageBasicAttack(caster, target, isCritical);
    }
}
