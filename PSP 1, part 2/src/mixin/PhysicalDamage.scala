package mixin

import Entities.Scaling

import scala.collection.mutable

trait PhysicalDamage extends DamagingHit {
  override protected def calculateScalingDamage(scalingStats: mutable.MutableList[Scaling], caster: Entities.Unit, target: Entities.Unit): Double = {
    var bonusDamage : Double = 0
    scalingStats.foreach(scaling => {
      if (scaling.getStatOwner == Scaling.StatOwner.CASTER) {
        bonusDamage += caster.getStat(scaling.getScalingStat) * scaling.getScaleRatio * 1.1
      }
      else {
        bonusDamage += target.getStat(scaling.getScalingStat) * scaling.getScaleRatio * 1.1
      }
    })
    bonusDamage
  }

  override protected def calculateResistanceReduction(caster: Entities.Unit, target: Entities.Unit): Entities.Unit = {
    var armor : Double = target.getArmor
    try {
      val result  = target.clone.asInstanceOf[Entities.Unit]
      armor *= 1 - target.getPercentageArmorPen
      armor -= target.getFlatArmorPen
      result.setArmor(armor)
      result
    }
    catch {
      case exc : CloneNotSupportedException => null
    }
  }

  override protected def calculatePostResistDamage(rawDamage: Double, target: Entities.Unit): Double = {
    var multiplier : Double = 0
    if (target.getArmor > 0) {
      multiplier = 100/(100 + target.getArmor)
    }
    else {
      multiplier = 2 - 100/(100 - target.getArmor)
    }
    rawDamage * multiplier
  }
}
