package com.dre.rpgsuite;

import java.io.File;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.dre.rpgsuite.commands.RCommandRoot;
import com.dre.rpgsuite.listeners.CommandListener;

public class P extends JavaPlugin{
	public static P p;
	
	public LanguageReader languageReader;
	
	@Override
	public void onEnable() {
		p = this;
		
		languageReader = new LanguageReader(new File(this.getDataFolder(), "language/en.yml"));
		
		setupPermissions();
		
		// Commands
		getCommand("rpgsuite").setExecutor(new CommandListener());
		new RCommandRoot();
	}
	
	// Permissions
	public Permission permission = null;

	private Boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			permission = permissionProvider.getProvider();
		}
		return (permission != null);
	}
}
