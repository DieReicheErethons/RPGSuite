package com.dre.rpgsuite.commands;

import java.util.concurrent.CopyOnWriteArrayList;

public class RCommandRoot {
	// Variables
	public static RCommandRoot root;

	public CopyOnWriteArrayList<RCommand> commands = new CopyOnWriteArrayList<RCommand>();

	// Commands
	public CMDHelp cmdHelp = new CMDHelp();
	
	// Methods
	public RCommandRoot() {
		root = this;
		
		// Add Commands
		this.commands.add(cmdHelp);
		this.commands.add(new CMDEditMode());
		this.commands.add(new CMDCreateBlock());
	}
}
