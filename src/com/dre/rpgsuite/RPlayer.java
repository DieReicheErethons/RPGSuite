package com.dre.rpgsuite;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.dre.rpgsuite.util.RUtil;

public class RPlayer {
	public static Set<RPlayer> players = new HashSet<RPlayer>();
	
	private Player player;
	private boolean isInEditMode;
	private ItemStack[] oldInventory;
	
	public RPlayer(Player player){
		players.add(this);
		
		this.player = player;
		this.isInEditMode = false;
		
	}
	
	public void switchEditMode(){
		if (isInEditMode) {
			player.getInventory().clear();
			player.getInventory().setContents(oldInventory);
			
			isInEditMode = false;
		} else {
			oldInventory = player.getInventory().getContents();
			
			player.getInventory().clear();
			setEditModeInvSite(0);
			
			isInEditMode = true;
		}
	}
	
	public void setEditModeInvSite(int site) {
		int slot = 9;
		
		for(RBlockType blocktype : RBlockType.getAll()) {
			ItemStack itemStack = new ItemStack(blocktype.getBlockMaterial());
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.setDisplayName(ChatColor.GREEN + blocktype.getId());
			itemStack.setItemMeta(itemMeta);
			
			this.player.getInventory().setItem(slot, itemStack);
			
			slot++;
		}
	}
	
	public static RPlayer get(Player player) {
		for(RPlayer rPlayer : players) {
			if (rPlayer.player == player) {
				RUtil.msg(player, "TEST");
				return rPlayer;
			}
		}
		return new RPlayer(player);
	}
}
