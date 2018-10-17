package mixin

import Entities.Scaling

import scala.collection.mutable

trait TrueDamage extends DamagingHit{
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
    bonusDamage
  }

  override protected def calculateResistanceReduction(caster: Entities.Unit, target: Entities.Unit): Entities.Unit = target

  override protected def calculatePostResistDamage(rawDamage: Double, target: Entities.Unit): Double = rawDamage
}
