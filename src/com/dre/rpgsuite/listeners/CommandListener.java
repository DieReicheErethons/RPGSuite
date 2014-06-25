package com.dre.rpgsuite.listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.dre.rpgsuite.P;
import com.dre.rpgsuite.commands.RCommand;
import com.dre.rpgsuite.commands.RCommandRoot;
import com.dre.rpgsuite.util.RUtil;

public class CommandListener implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd_notused, String arg, String[] args) {
		if (args.length > 0) {
			String cmd = args[0];

			for (RCommand command : RCommandRoot.root.commands) {
				if (cmd.equals(command.command)) {
					if (sender instanceof ConsoleCommandSender) {
						if (!command.isConsoleCommand) {
							RUtil.msg(sender, P.p.languageReader.get("Log_Error_NoConsoleCommand", command.command));
							return false;
						}
					}

					if (sender instanceof Player) {
						Player player = (Player) sender;
						if (!command.isPlayerCommand) {
							RUtil.msg(player, P.p.languageReader.get("Error_NoPlayerCommand", command.command));
							return false;
						} else {
							if (!command.playerHasPermissions(player)) {
								RUtil.msg(player, P.p.languageReader.get("Error_NoPermissions"));
								return false;
							}
						}
					}

					if (command.args == args.length - 1 || command.args == -1) {
						command.onExecute(args, sender);
						return true;
					} else {
						command.displayHelp(sender);
					}
				}
			}

			RUtil.msg(sender, P.p.languageReader.get("Error_CmdNotExist", cmd));
		} else {
			RCommandRoot.root.cmdHelp.onExecute(args, sender);
		}

		return false;
	}

}
