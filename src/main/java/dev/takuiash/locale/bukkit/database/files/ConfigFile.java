package dev.takuiash.locale.bukkit.database.files;

import org.bukkit.plugin.Plugin;

import dev.takuiash.commons.YamlFile;
import dev.takuiash.locale.bukkit.Locale;
import dev.takuiash.locale.bukkit.LocalePlugin;

public class ConfigFile extends YamlFile {

	private static LocalePlugin plugin;
	
	public ConfigFile(LocalePlugin plugin) {
		super(plugin.getDataFolder(), "config.yml");
		
		saveDefaultConfig(plugin);
		
		ConfigFile.plugin = plugin;
	}
	
	public static String getLocaleFileDirPattern(Plugin plugin, Locale locale) {
		return ConfigFile.plugin.getConfig().getString("locale_file_dir_pattern")
				.replaceAll("%pluginName%", plugin.getName())
				.replaceAll("%pluginDataFolder%", plugin.getDataFolder().getPath().replace('\\', '/'))
				.replaceAll("%baseDataFolder%", ConfigFile.plugin.getDataFolder().getPath().replace('\\', '/'))
				.replaceAll("%locale%", locale.toString());
	}

	public static Locale getFallbackLocale() {
		Locale locale = Locale.valueOf(plugin.getConfig().getString("fallback_locale"));
		
		if(locale == null)
			throw new IllegalArgumentException("fallback_locale is an invalid locale in config.yml ('"+plugin.getConfig().getString("fallback_locale")+"')");
		
		return locale;
	}
	
	public static boolean isDebug() {
		return plugin.getConfig().getBoolean("debug");
	}
}
