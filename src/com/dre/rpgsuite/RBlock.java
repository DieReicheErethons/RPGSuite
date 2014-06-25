package com.dre.rpgsuite;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RBlock {
	private static Set<RBlock> blocks = new HashSet<RBlock>();
	
	private RBlockType type;
	private Location location;
	private boolean destroyed;
	
	public RBlock (RBlockType type, Location location) {
		blocks.add(this);
		
		this.type = type;
		this.location = location;
	}
	
	public static RBlock get(Location location) {
		for(RBlock rblock : blocks) {
			if (rblock.location == location) {
				return rblock;
			}
		}
		return null;
	}
	
	public void destroyBlock(Player player) {
		if (!this.destroyed) {
			this.destroyed = true;
			
			this.location.getBlock().setType(this.getType().getAltBlockMaterial());
			
			Bukkit.getScheduler().runTaskLater(P.p, new RespawnerRunnable(this), this.getType().getRespawnTime());
		}
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public RBlockType getType() {
		return this.type;
	}
	
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	
	private class RespawnerRunnable implements Runnable {
		private RBlock rblock;
		
		public RespawnerRunnable(RBlock rblock) {
			this.rblock = rblock;
		}
		
		@Override
		public void run() {
			rblock.getLocation().getBlock().setType(rblock.getType().getBlockMaterial());
			rblock.setDestroyed(false);
		}
	}
	
	
}
