package mixin

import Entities.Scaling

import scala.collection.mutable

trait MagicDamage extends DamagingHit{
  override protected def calculateScalingDamage(scalingStats: mutable.MutableList[Scaling], caster: Entities.Unit, target: Entities.Unit): Double = {
    var bonusDamage : Double = 0
    scalingStats.foreach(scaling => {
      if (scaling.getStatOwner == Scaling.StatOwner.CASTER) {
        bonusDamage += caster.getStat(scaling.getScalingStat) * scaling.getScaleRatio
      }
      else {
        bonusDamage += target.getStat(scaling.getScalingStat) * scaling.getScaleRatio
      }
    })
    System.out.println(bonusDamage)
    bonusDamage
  }

  override protected def calculateResistanceReduction(caster: Entities.Unit, target: Entities.Unit): Entities.Unit = {
    var mr : Double = target.getMagicResistance
    try {
      val result  = target.clone.asInstanceOf[Entities.Unit]
      mr *= 1 - target.getPercentageMagicPen
      mr -= target.getFlatMagicPen
      result.setMagicResistance(mr)
      result
    }
    catch {
      case exc : CloneNotSupportedException => null
    }
  }

  override protected def calculatePostResistDamage(rawDamage: Double, target: Entities.Unit): Double = {
    var multiplier : Double = 0
    if (target.getMagicResistance > 0) {
      multiplier = 100/(115 + target.getMagicResistance)
    }
    else {
      multiplier = 2 - 100/(115 - target.getMagicResistance)
    }
    rawDamage * multiplier
  }
}
