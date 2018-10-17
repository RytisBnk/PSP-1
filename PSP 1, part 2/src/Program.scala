import java.util

import Entities.{Actions, Scaling}
import mixin._

import scala.collection.mutable

object Program {
  def main(args : Array[String]) : Unit = {
    val unit1 : Entities.Unit = new Entities.Unit(
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
    )
    val unit2: Entities.Unit = new Entities.Unit(
      156,
      456,
      2300,
      2300,
      0,
      30,
      134,
      98,
      0,
      0.3,
      1.2,
      370,
      0,
      0,
      new Actions(true, true, true, true)
    )
    val baseDamage : Double = 300
    val abilityScaling : mutable.MutableList[Scaling] = new mutable.MutableList[Scaling]()
    abilityScaling += new Scaling(Entities.Unit.Stat.ABILITY_POWER, 0.6, Scaling.StatOwner.CASTER)
    abilityScaling += new Scaling(Entities.Unit.Stat.ATTACK_DAMAGE, 0.2, Scaling.StatOwner.CASTER)
    val stun = new PhysicalDamageStun(unit2, unit1, baseDamage, abilityScaling)
    stun.baseEffectDuration = 2.0
    System.out.println(stun.damage())
    System.out.println(stun.applyEffect)
    val ability = new Ability(unit1, unit2, 350, abilityScaling) with StunAbility with MagicDamage
    System.out.println(ability.damage())
    val attack = new BasicAttack(unit1, unit2, true) with PhysicalDamage
    System.out.println(attack.damage())
  }
}

class PhysicalDamageStun(caster : Entities.Unit,
                         target : Entities.Unit,
                         baseDamage : Double, abilityScaling :
                         mutable.MutableList[Scaling])
  extends Ability(caster, target, baseDamage, abilityScaling) with StunAbility with PhysicalDamage {

}

class MagicDamageSilence(caster : Entities.Unit,
                         target : Entities.Unit,
                         baseDamage : Double,
                         abilityScaling : mutable.MutableList[Scaling])
  extends Ability(caster, target, baseDamage, abilityScaling) with SilenceAbility with MagicDamage {

}