package mixin

import java.util

import Entities.{Actions, Scaling}

trait StunAbility extends Ability {
  override protected def restrictActions(target: Entities.Unit): Entities.Unit = {
    try {
      val result : Entities.Unit = target.clone.asInstanceOf[Entities.Unit]
      result.setAllowedActions(new Actions(false, false, false, false))
      result
    }
    catch {
      case exc : CloneNotSupportedException => null
    }
  }

  override protected def calculateDuration(baseDuration: Double, target: Entities.Unit): Double = baseDuration * (1 - target.getTenacity)

  override protected def applyStatChanges(ability: Ability): util.Map[Scaling.StatOwner, Entities.Unit] = {
    val result : util.Map[Scaling.StatOwner, Entities.Unit] = new util.HashMap[Scaling.StatOwner, Entities.Unit]
    result.put(Scaling.StatOwner.CASTER, ability.caster)
    result.put(Scaling.StatOwner.TARGET, ability.target)
    result
  }
}
