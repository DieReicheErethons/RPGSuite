package com.dre.rpgsuite.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class RUtil {
	
	// Msg
	public static void msg(CommandSender sender, String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		sender.sendMessage(ChatColor.DARK_GREEN + "[RPG] " + ChatColor.WHITE + msg);
	}

	public static void msg(CommandSender sender, String msg, boolean zusatz) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		if (zusatz) {
			sender.sendMessage(ChatColor.DARK_GREEN + "[RPG]" + ChatColor.WHITE + msg);
		} else {
			sender.sendMessage(ChatColor.WHITE + msg);
		}
	}
}
