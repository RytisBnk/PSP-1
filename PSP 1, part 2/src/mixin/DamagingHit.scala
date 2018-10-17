package mixin

import Entities.Scaling

import scala.collection.mutable

trait DamagingHit {
  def damage() : Double
  protected def calculateScalingDamage(scalingStats : mutable.MutableList[Scaling], caster : Entities.Unit, target : Entities.Unit) : Double
  protected def calculateResistanceReduction(caster : Entities.Unit, target : Entities.Unit) : Entities.Unit
  protected def calculatePostResistDamage(rawDamage : Double, target : Entities.Unit) : Double
}
