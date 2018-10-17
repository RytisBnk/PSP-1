package mixin

import Entities.Scaling
import java.util

import scala.collection.mutable

abstract class Ability(var caster : Entities.Unit,
                       var target : Entities.Unit,
                       var baseDamage : Double,
                       var abilityScaling : mutable.MutableList[Scaling]) extends DamagingHit {
  private var _baseEffectDuration : Double = 0.0
  private var _effectValue : Double = 0.0

  def baseEffectDuration : Double = _baseEffectDuration
  def baseEffectDuration_= (value : Double) : Unit = _baseEffectDuration = value

  def effectValue : Double = _effectValue
  def effectValue_= (value : Double) : Unit = _effectValue = value

  override def damage(): Double = {
    val totalDamage : Double = baseDamage + calculateScalingDamage(abilityScaling, caster, target)
    val modifiedTarget : Entities.Unit = calculateResistanceReduction(caster, target)
    calculatePostResistDamage(totalDamage, modifiedTarget)
  }

  def applyEffect : String = {
    target = restrictActions(target)
    val duration : Double = calculateDuration(baseEffectDuration, target)
    val units : util.Map[Scaling.StatOwner, Entities.Unit] = applyStatChanges(this)

    "Effect duration: " + duration + "\n" + units.get(Scaling.StatOwner.CASTER).toString +
      "\n" + units.get(Scaling.StatOwner.TARGET).toString
  }

  protected def restrictActions(target : Entities.Unit) : Entities.Unit
  protected def calculateDuration(baseDuration : Double, target : Entities.Unit) : Double
  protected def applyStatChanges(ability: Ability) : util.Map[Scaling.StatOwner, Entities.Unit]
}
