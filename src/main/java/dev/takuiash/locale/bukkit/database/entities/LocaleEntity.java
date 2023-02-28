package dev.takuiash.locale.bukkit.database.entities;

import java.util.UUID;

import dev.takuiash.locale.bukkit.Locale;

public class LocaleEntity {

	private Long id;
	private UUID playerId;
	private Locale locale;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public UUID getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
