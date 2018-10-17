package mixin

abstract class BasicAttack(caster : Entities.Unit, target : Entities.Unit, isCritical : Boolean) extends DamagingHit {
  private def baseDamage : Double = {
    if (isCritical) {
      target.getAttackDamage * 2
    }
    else {
      target.getAttackDamage
    }
  }

  override def damage(): Double = {
    val modifiedTarget : Entities.Unit = calculateResistanceReduction(caster, target)
    calculatePostResistDamage(baseDamage, modifiedTarget)
  }
}
