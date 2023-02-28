package dev.takuiash.locale.bukkit;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import dev.takuiash.locale.bukkit.database.files.ConfigFile;
import dev.takuiash.locale.bukkit.database.files.MessageFile;
import net.md_5.bungee.api.ChatColor;

public class MessageManager {

	private final LocalePlugin plugin = JavaPlugin.getPlugin(LocalePlugin.class);
	private final Plugin caller;
	
	private final HashMap<String, String> replaces = new HashMap<>();
	
	public MessageManager(Plugin caller) {
		this.caller = caller;
	}
	
	public MessageFile getMessageFile(Locale locale) {		
		String filePattern = ConfigFile.getLocaleFileDirPattern(caller, locale);
		return new MessageFile(filePattern, ConfigFile.getFallbackLocale() == locale ? true : false);
	}
	
	public void saveMessageResource(Locale locale, String resourcePath) {
		String filePattern = ConfigFile.getLocaleFileDirPattern(caller, locale);
		new MessageFile(filePattern, false).saveResource(caller, resourcePath);;	
	}
	
	public String getMessage(Locale locale, String path) {		
		MessageFile file = getMessageFile(locale);
		String message = file.getString(path);
		
		if(message == null && locale != ConfigFile.getFallbackLocale()) {
			if(ConfigFile.isDebug()) System.out.println("path '"+ path +"' not founded on file '" + file.getFilePath() + "'. trying fallback locale file...");
			
			file = getMessageFile(ConfigFile.getFallbackLocale());
			message = file.getString(path);
		}
		
		if(message == null)
			throw new NullPointerException("path '" + path + "' not founded on file '" + file.getFilePath() + "'");
		
		if(!replaces.isEmpty()) {
			for(Entry<String, String> replace : replaces.entrySet()) {
				message = message.replaceAll(replace.getKey(), replace.getValue());
			}
		}
		
		replaces.clear();

		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public String getMessage(Player player, String path) {
		return getMessage(plugin.getLocaleController().getPlayerLocale(player), path);

	}
	
	public void sendMessage(Player player, String path) {		
		player.sendMessage(getMessage(plugin.getLocaleController().getPlayerLocale(player), path));
	}
	
	public MessageManager replaceAll(String regex, String replacement) {
		replaces.put(regex, replacement);
		return this;
	}
}
