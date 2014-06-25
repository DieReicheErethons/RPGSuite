package com.dre.rpgsuite.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dre.rpgsuite.P;
import com.dre.rpgsuite.RBlock;
import com.dre.rpgsuite.RBlockType;
import com.dre.rpgsuite.RPlayer;
import com.dre.rpgsuite.util.RUtil;

public class CMDCreateBlock extends RCommand {
	
	public CMDCreateBlock() {
		this.command = "createblock";
		this.args = 2;
		this.help = P.p.languageReader.get("Help_Cmd_CreateBlock");
		this.isPlayerCommand = true;
		this.isConsoleCommand = true;
	}

	@Override
	public void onExecute(String[] args, CommandSender sender) {
		if (sender instanceof Player) {			
			RBlockType type = RBlockType.get(args[1]);
			
			if (type == null) {
				Material material = Material.getMaterial(args[2].toUpperCase());
				
				if (material != null) {
					new RBlockType(args[1], material);
					RUtil.msg(sender, "3");
				} else {
					RUtil.msg(sender, "2");// TODO: material does not exist
				}
			} else {
				RUtil.msg(sender, "1");// TODO: id exists
			}
		}
	}
}
