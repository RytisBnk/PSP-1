package strategy;

import entities.Scaling;
import entities.Unit;

import java.util.List;

public class BasicEntities {
    public static Ability getBasicAbility(Unit caster, Unit target, double baseDamage, List<Scaling> abilityScaling) {
        Ability ability =  new Ability(caster, target, baseDamage, abilityScaling);
        ability.setCalculation(new MagicDamageCalculation());
        ability.setEffect(new ExhaustEffect());
        return ability;
    }

    public static BasicAttack getStandardBasicAttack(Unit caster, Unit target, boolean isCritical) {
        BasicAttack attack = new BasicAttack(caster, target, isCritical);
        attack.setCalculation(new PhysicalDamageCalculation());
        return attack;
    }
}
