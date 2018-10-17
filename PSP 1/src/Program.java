import strategy.*;
import entities.*;

import java.util.LinkedList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        Unit unit1 = new Unit(
                310,
                0,
                2000,
                2000,
                25,
                0,
                40,
                58,
                0.35,
                0,
                1.89,
                370,
                0.3,
                0,
                new Actions(true, true, true, true)
        );
        Unit unit2 = new Unit(
                156,
                456,
                2300,
                2300,
                0,
                30,
                134,
                98,
                0,
                0,
                1.2,
                370,
                0,
                0,
                new Actions(true, true, true, true)
        );
        double baseDamage = 320;
        List<Scaling> abilityScaling = new LinkedList<>();
        abilityScaling.add(new Scaling(Unit.Stat.ABILITY_POWER, 0.6, Scaling.StatOwner.CASTER));
        abilityScaling.add(new Scaling(Unit.Stat.ATTACK_DAMAGE, 0.2, Scaling.StatOwner.CASTER));
        Ability ability = BasicEntities.getBasicAbility(unit2, unit1, baseDamage, abilityScaling);
        ability.setBaseEffectDuration(2.5);
        System.out.println(ability.damage());
        System.out.println(ability.applyEffect());
    }
}
