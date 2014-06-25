package com.dre.rpgsuite.commands;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.dre.rpgsuite.P;
import com.dre.rpgsuite.util.RUtil;

public class CMDHelp extends RCommand {
	
	public CMDHelp() {
		this.command = "help";
		this.args = -1;
		this.help = P.p.languageReader.get("Help_Cmd_Help");
		this.isPlayerCommand = true;
		this.isConsoleCommand = true;
	}

	@Override
	public void onExecute(String[] args, CommandSender sender) {
		boolean isConsole = false, isPlayer = false;

		if (sender instanceof ConsoleCommandSender) {
			isConsole = true;
		} else if (sender instanceof Player) {
			isPlayer = true;
		}

		int page = 1;
		int pages = (int) Math.ceil(RCommandRoot.root.commands.size() / 6.0);

		if (args.length > 1) {
			try {
				page = NumberUtils.toInt(args[1], 0);
			} catch (NumberFormatException e) {
				page = 1;
			}
			if (page < 1)
				page = 1;
			if (page > pages)
				page = pages;
		}

		RUtil.msg(sender, ChatColor.GREEN + "============[ " + ChatColor.GOLD + "Help RPGSuite - " + page + "/" + pages + ChatColor.GREEN + " ]============", false);

		int i = -1;
		int ipage = 1;
		for (RCommand command : RCommandRoot.root.commands) {
			if ((command.isConsoleCommand && isConsole) || (command.isPlayerCommand && isPlayer)) {
				i++;
				if (i > 5) {
					i = 0;
					ipage++;
				}
				if (ipage == page) {
					RUtil.msg(sender, ChatColor.YELLOW + command.help, false);
				}
			}
		}

		RUtil.msg(sender, ChatColor.GREEN + "==============[ " + ChatColor.GOLD + "By Frank Baumann" + ChatColor.GREEN + " ]==============", false);
	}
}
