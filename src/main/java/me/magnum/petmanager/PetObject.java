package me.magnum.petmanager;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PetObject {
	
	public String name;
	public UUID petId, ownerId;
	public EntityType entityType;
	
	public PetObject newPet(String name, Player owner, EntityType entityType){
		this.name = name;
		petId = UUID.randomUUID();
		this.ownerId = owner.getUniqueId();
		this.entityType = entityType;
		return this;
	}
}
