package com.dre.rpgsuite;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageReader {
	private Map<String, String> entries = new TreeMap<String, String>();
	private Map<String, String> defaults = new TreeMap<String, String>();

	private File file;
	private boolean changed;

	public LanguageReader(File file) {
		this.setDefaults();

		/* Load */
		this.file = file;

		FileConfiguration configFile = YamlConfiguration.loadConfiguration(file);

		Set<String> keySet = configFile.getKeys(false);
		for (String key : keySet) {
			entries.put(key, configFile.getString(key));
		}

		/* Check */
		this.check();
	}

	private void setDefaults() {

		/* Log */
		defaults.put("Log_Error_NoConsoleCommand", "&6/rpg &v1&4 can not be executed as Console!");

		/* Player */
		
		/* Cmds */
		
		/* Errors */
		defaults.put("Error_NoPermissions", "&4You have no permission to do this!");
		defaults.put("Error_CmdNotExist", "&4Command &6&v1&4 does not exist, please enter &6/rpg help&4 for help!");
		defaults.put("Error_NoPlayerCommand", "&6/rpg &v1&4 can not be executed as player!");
		
		/* Help */
		
	}

	private void check() {
		for (String defaultEntry : defaults.keySet()) {
			if (!entries.containsKey(defaultEntry)) {
				entries.put(defaultEntry, defaults.get(defaultEntry));
				changed = true;
			}
		}
	}

	public void save() {
		if (changed) {
			/* Copy old File */
			File source = new File(file.getPath());
			String filePath = file.getPath();
			File temp = new File(filePath.substring(0, filePath.length() - 4) + "_old.yml");

			if (temp.exists())
				temp.delete();

			source.renameTo(temp);

			/* Save */
			FileConfiguration configFile = new YamlConfiguration();

			for (String key : entries.keySet()) {
				configFile.set(key, entries.get(key));
			}

			try {
				configFile.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String get(String key, String... args) {
		String entry = entries.get(key);

		if (entry != null) {
			int i = 0;
			for (String arg : args) {
				i++;
				if(arg != null){
					entry = entry.replace("&v" + i, arg);
				} else {
					entry = entry.replace("&v" + i, "null");
				}
			}
		}

		return entry;
	}
}
