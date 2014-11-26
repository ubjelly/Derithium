package org.hyperion.rs2.content.combat;

import org.hyperion.Server;
import org.hyperion.rs2.action.Action;
import org.hyperion.rs2.model.Damage.Hit;
import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.npc.NPC;
import org.hyperion.rs2.model.player.Player;

/**
 * Represents an abstraction of combat. The different variations of combat
 * (melee, range, and mage) should extend this class.
 * @author Stephen Andrews
 */
public abstract class CombatAction extends Action {

	/**
	 * The entity performing the attack.
	 */
	protected Entity aggressor;
	
	/**
	 * The entity receiving the attack.
	 */
	protected Entity victim;
	
	/**
	 * Constructs a combat action between the aggressor and its victim.
	 * @param aggressor The entity performing the attack.
	 * @param victim The entity receiving the attack.
	 * @param delay The delay between attacks.
	 */
	public CombatAction(Entity aggressor, Entity victim) {
		super(((Player) aggressor), Server.CYCLE_TIME); //TODO: Convert action system to work with NPCs
		this.aggressor = aggressor;
		this.victim = victim;
	}
	
	/**
	 * Gets the entity performing the attack.
	 * @return The entity performing the attack.
	 */
	public Entity getAggressor() {
		return aggressor;
	}
	
	/**
	 * Gets the entity receiving the attack.
	 * @return The entity receiving the attack.
	 */
	public Entity getVictim() {
		return victim;
	}
	
	/**
	 * Initiates the attack action.
	 * @param agressor The entity performing the attack.
	 * @param victom The entity being hit.
	 */
	public abstract void executeAttack(Entity aggressor, Entity victim);
	
	/**
	 * Determines whether or not an entity can attack.
	 * @param aggressor The entity performing the attack.
	 * @param victim The entity being hit.
	 */
	public abstract boolean canAttack(Entity aggressor, Entity victim);
	
	/**
	 * Inflicts damage to an entity and appends the aggressor's damage to the
	 * entity's damage map.
	 * @param entity The entity to inflict the damage on.
	 * @param damage The damage to inflict.
	 */
	public void inflictDamage(Entity entity, Hit damage) {
		entity.inflictDamage(aggressor, damage);
		entity.getDamageMap().appendDamage(aggressor, damage.getDamage());
	}
}