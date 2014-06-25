package com.dre.rpgsuite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;

public class RBlockType {
	private static Set<RBlockType> blockTypes = new HashSet<RBlockType>();
	
	private String id;
	private RItem dropItem;
	private int amountFrom, amountTo;
	private int respawnTime;
	private Material blockMaterial;
	private Material altBlockMaterial;
	
	public RBlockType(String id, Material material) {
		blockTypes.add(this);
		
		this.id = id;
		this.blockMaterial = material;
		
		// Default Values
		this.altBlockMaterial = Material.AIR;
		this.respawnTime = 5;
	}
	
	public Material getBlockMaterial(){
		return this.blockMaterial;
	}
	
	public Material getAltBlockMaterial(){
		return this.altBlockMaterial;
	}
	
	public int getRespawnTime() {
		return respawnTime;
	}
	
	public String getId() {
		return this.id;
	}
	
	public static Set<RBlockType> getAll(){
		return blockTypes;
	}
	
	public static RBlockType get(String id) {
		for(RBlockType rblockType : blockTypes) {
			if (rblockType.id.equalsIgnoreCase(id)) {
				return rblockType;
			}
		}
		return null;
	}
}
