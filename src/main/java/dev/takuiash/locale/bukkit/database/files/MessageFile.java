package dev.takuiash.locale.bukkit.database.files;

import java.io.File;

import org.bukkit.plugin.Plugin;

import dev.takuiash.commons.YamlFile;

public class MessageFile extends YamlFile {

	public MessageFile(String file) {
		super(new File(file));
	}

	public MessageFile(String file, boolean createIfNotExists) {
		super(new File(file), createIfNotExists);
	}
	
	public void saveResource(Plugin plugin, String resourcePath) {
		this.saveResource(plugin, resourcePath, false);
	}
}
