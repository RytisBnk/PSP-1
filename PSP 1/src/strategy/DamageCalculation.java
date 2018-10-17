package strategy;

import entities.Scaling;
import entities.Unit;

import java.util.List;

public interface DamageCalculation {
    double calculateScalingDamage(List<Scaling> abilityScaling, Unit caster, Unit target);
    Unit calculateResistanceReduction(Unit caster, Unit target);
    double calculatePostResistDamage(double rawDamage, Unit target);
}
