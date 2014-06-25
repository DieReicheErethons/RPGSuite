package com.dre.rpgsuite.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.dre.rpgsuite.RBlock;

public class BlockListener implements Listener{
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		RBlock rblock = RBlock.get(event.getBlock().getLocation());
		
		if (rblock != null) {
			rblock.destroyBlock(event.getPlayer());
		}
	}
}
