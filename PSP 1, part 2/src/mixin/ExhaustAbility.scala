package mixin

import java.util

import Entities.{Actions, Scaling}

trait ExhaustAbility extends Ability {
  override protected def restrictActions(target: Entities.Unit): Entities.Unit = {
    try {
      val result : Entities.Unit = target.clone.asInstanceOf[Entities.Unit]
      result.setAllowedActions(new Actions(true, true, true, true))
      result
    }
    catch {
      case exc : CloneNotSupportedException => null
    }
  }

  override protected def calculateDuration(baseDuration: Double, target: Entities.Unit): Double =
    baseDuration * (1 - target.getSlowReduction)

  override protected def applyStatChanges(ability: Ability): util.Map[Scaling.StatOwner, Entities.Unit] = {
    val result : util.Map[Scaling.StatOwner, Entities.Unit] = new util.HashMap[Scaling.StatOwner, Entities.Unit]
    try  {
      val modifiedTarget : Entities.Unit = ability.target.clone.asInstanceOf[Entities.Unit]
      modifiedTarget.setAttackSpeed(modifiedTarget.getAttackSpeed * (1 - ability.effectValue))
      modifiedTarget.setMovementSpeed(modifiedTarget.getMovementSpeed * (1 - ability.effectValue))
      result.put(Scaling.StatOwner.CASTER, ability.caster)
      result.put(Scaling.StatOwner.TARGET, modifiedTarget)
      result
    }
    catch {
      case exc : CloneNotSupportedException => {
        result.put(Scaling.StatOwner.CASTER, ability.caster)
        result.put(Scaling.StatOwner.TARGET, ability.target)
        result
      }
    }
  }
}
