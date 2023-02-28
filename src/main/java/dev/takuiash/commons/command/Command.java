package dev.takuiash.commons.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginDescriptionFile;

public abstract class Command implements CommandExecutor {
	
	private final String name;
	private String permission;
	private String permissionMessage = ChatColor.RED + "You don't have permission.";
	private List<String> aliases;
	private String description;
	private TabCompleter tabCompleter;
	private String usage;
	
	public Command(String name) {
		this.name = name;
	}

    /**
     * Gets the {@link TabCompleter} associated with this command.
     *
     * @return TabCompleter object linked to this command
     */
	public TabCompleter getTabCompleter() {
		return tabCompleter;
	}
	
    /**
     * Sets the {@link TabCompleter} to run when tab-completing this command.
     * <p>
     * If no TabCompleter is specified, and the command's executor implements
     * TabCompleter, then the executor will be used for tab completion.
     *
     * @param completer New tab completer
     */
	public void setTabCompleter(TabCompleter completer) {
		this.tabCompleter = completer;
	}
	
    /**
     * Returns the name of this command
     *
     * @return Name of this command
     */
	public String getName() {
		return name;
	}
	
    /**
     * Gets the permission required by users to be able to perform this
     * command
     *
     * @return Permission name, or null if none
     */
	public String getPermission() {
		return permission;
	}
	
    /**
     * Sets the permission required by users to be able to perform this
     * command
     *
     * @param permission Permission name or null
     */
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
    /**
     * Returns a list of active aliases of this command
     *
     * @return List of aliases
     */
	public List<String> getAliases() {
		return aliases;
	}
	
    /**
     * Sets the list of aliases to request on registration for this command.
     *
     * @param aliases Aliases to register to this command
     */
	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
	
    /**
     * Add aliases to request on registration for this command.
     *
     * @param aliases Aliases to register to this command
     */
	public void addAliases(String... aliases) {
		if(this.aliases == null)
			this.aliases = new ArrayList<>();
		
		this.aliases.addAll(Arrays.asList(aliases));
	}
	
    /**
     * Returns a message to be displayed on a failed permission check for this
     * command
     *
     * @return Permission check failed message
     */
    public String getPermissionMessage() {
        return permissionMessage;
    }

    /**
     * Sets the message sent when a permission check fails
     *
     * @param permissionMessage new permission message, null to indicate
     *     default message, or an empty string to indicate no message
     */
    public void setPermissionMessage(String permissionMessage) {
        this.permissionMessage = permissionMessage;
    }

    /**
     * Gets a brief description of this command
     *
     * @return Description of this command
     */
	public String getDescription() {
		return description;
	}
	
    /**
     * Sets a brief description of this command. Defining a description in the
     * {@link PluginDescriptionFile#getCommands()} (under the
     * `<code>description</code>' node) is equivalent to this method.
     *
     * @param description new command description
     */
	public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Gets an example usage of this command
     *
     * @return One or more example usages
     */
	public String getUsage() {
		return usage;
	}

    /**
     * Sets the example usage of this command
     *
     * @param usage new example usage
     */
	public void setUsage(String usage) {
		this.usage = usage;
	}
	
    /**
     * Executes the given command, returning its success
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return true if a valid command, otherwise false
     */
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase(name)) {
			
			if(getPermission() != null && sender.hasPermission(getPermission())) {
				sender.sendMessage(permissionMessage);
				return true;
			}
			
			perform(sender, new Arguments(args));
			return true;
		}
		return false;
	}

    /**
     * Executes the command
     *
     * @param sender Source object which is executing this command
     * @param args All arguments passed to the command
     */
	public abstract void perform(CommandSender sender, Arguments args);

	public enum CommandSenderType {
		PLAYER, CONSOLE, ALL;
	}



}
