package com.dre.rpgsuite.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dre.rpgsuite.P;
import com.dre.rpgsuite.RPlayer;

public class CMDEditMode extends RCommand {
	
	public CMDEditMode() {
		this.command = "editmode";
		this.args = -1;
		this.help = P.p.languageReader.get("Help_Cmd_EditMode");
		this.isPlayerCommand = true;
		this.isConsoleCommand = false;
	}

	@Override
	public void onExecute(String[] args, CommandSender sender) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			RPlayer rplayer = RPlayer.get(player);
			
			rplayer.switchEditMode();
		}
	}
}
