package dev.takuiash.commons.command;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {
	
	private JavaPlugin plugin; 
	
	public CommandManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void registerCommand(Command command) {
		PluginCommand pluginCommand = plugin.getCommand(command.getName());
		
		if(pluginCommand == null)
			throw new NullPointerException("you need to fill the plugin.yml with the command names ");
				
		if(command.getPermission() != null) pluginCommand.setPermission(command.getPermission());
		if(command.getPermissionMessage() != null) pluginCommand.setPermissionMessage(command.getPermissionMessage());
		if(command.getDescription() != null) pluginCommand.setDescription(command.getDescription());
		if(command.getTabCompleter() != null) pluginCommand.setTabCompleter(command.getTabCompleter());
		if(command.getUsage() != null) pluginCommand.setUsage(command.getUsage());
		
		pluginCommand.setExecutor(command);
	}
	
}
