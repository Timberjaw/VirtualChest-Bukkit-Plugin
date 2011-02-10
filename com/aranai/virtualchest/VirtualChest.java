/*
 * VirtualChest is a proof of concept for virtual chests in Bukkit
 * VC creates a single large global chest, accessible by all players via the /chest command
 * 
 * I have not verified the following:
 * 1. Proper functionality if multiple users access the chest simultaneously
 * 2. Proper triggering of available inventory hooks
 */
package com.aranai.virtualchest;

import java.io.File;
import java.util.logging.Logger;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.InventoryLargeChest;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * VirtualChest for Bukkit
 *
 * @author Timberjaw
 * 
 */
public class VirtualChest extends JavaPlugin {

	private Logger log;
	private TileEntityVirtualChest chest;
	private TileEntityVirtualChest chest2;
	private InventoryLargeChest lc;

	public VirtualChest(PluginLoader pluginLoader, Server instance,
			PluginDescriptionFile desc, File folder, File plugin,
			ClassLoader cLoader) {
		super(pluginLoader, instance, desc, folder, plugin, cLoader);
	}

	@Override
	public void onDisable() {}

	@Override
	public void onEnable() {
		log = Logger.getLogger("Minecraft");
		
		// Large chests are made up of two individual small chests
		// TileEntityVirtualChest extends the TileEntityChest class to remove some bothersome world checks
		// This would NOT work with regular TileEntityChest instances
		chest = new TileEntityVirtualChest();
		chest2 = new TileEntityVirtualChest();
		
		// Set up the global chest
		// Note: this is NOT persisted across server restarts
		lc = new InventoryLargeChest("Large chest", chest, chest2);
		
		// Enable message
        PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[VirtualChest] version [" + pdfFile.getVersion() + "] loaded" );
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		// Show chest to the player. -_-
		
		// Verify that the sender is a player
		if(sender instanceof Player)
		{
			// Get the EntityPlayer handle from the sender
			EntityPlayer eh = ((CraftPlayer)sender).getHandle();
			
			// Chest time!
			eh.a(lc);
		}
		
		return true;
	}

}
