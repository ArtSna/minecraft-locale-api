package dev.takuiash.locale.bukkit.controllers;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import dev.takuiash.locale.bukkit.Locale;
import dev.takuiash.locale.bukkit.database.repositories.LocaleRepository;
import dev.takuiash.locale.bukkit.events.PlayerLocaleChangeEvent;

public class LocaleController {

    private final HashMap<UUID, Locale> playerLocale = new HashMap<>();
	private LocaleRepository repo;
	
	public LocaleController(LocaleRepository repo) {
		this.repo = repo;
	}
	
	public void changeLanguage(Player player, Locale locale) {
        Locale oldLocale = playerLocale.put(player.getUniqueId(), locale);

		if(repo.exists(player.getUniqueId())) {
			repo.updateLocale(player.getUniqueId(), locale);
		} else {
			repo.create(player.getUniqueId(), locale);
		}
		
		Bukkit.getPluginManager().callEvent(new PlayerLocaleChangeEvent(player, locale, oldLocale));
	}
	
	public Locale getPlayerLocale(Player player) {
		if(!repo.exists(player.getUniqueId())) {
			Locale locale = Locale.valueOf(player.getLocale());
			repo.create(player.getUniqueId(), locale);
			return locale;
		} else {
			return repo.find(player.getUniqueId());
		}
	}
	
}
