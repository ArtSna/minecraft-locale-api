package dev.takuiash.locale.bukkit.database.repositories;

import java.util.UUID;

import org.pretty.database.Database;

import dev.takuiash.locale.bukkit.Locale;

public class LocaleRepository {
	
	private Database database;
	
	public LocaleRepository(Database database) {
		this.database = database;
		
		database.execute("CREATE TABLE IF NOT EXISTS localeapi_data("
				+ " id BIGINT PRIMARY KEY,"
				+ " uuid VARCHAR(32) UNIQUE NOT NULL,"
				+ " locale VARCHAR(10) NOT NULL"
				+ ");");
	}
	
	public boolean create(UUID uuid, Locale locale) {		
		return database.execute("INSERT INTO localeapi_data(uuid, locale) VALUES(?, ?)", 
				uuid,
				locale);
	}
	
	public boolean updateLocale(UUID uuid, Locale locale) {
		return database.execute("UPDATE localeapi_data SET locale=? WHERE uuid=?", 
				locale.toString(),
				uuid);
	}
	
	public boolean exists(UUID uuid) {		
		return !database.select("SELECT uuid FROM localeapi_data WHERE uuid=?", uuid).isEmpty();	
	}
	
	public boolean delete(UUID uuid) {
		return database.execute("DELETE FROM localeapi_data WHERE uuid=?", uuid);
	}

	public Locale find(UUID uuid) {
		return Locale.valueOf(database.select("SELECT locale FROM localeapi_data WHERE uuid=?", uuid).first().getString("locale"));
	}
	
}
