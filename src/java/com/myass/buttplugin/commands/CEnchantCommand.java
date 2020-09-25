package com.myass.buttplugin.commands;

import com.myass.buttplugin.helpers.EnchantManager;
import com.myass.buttplugin.models.CustomEnchant;
import com.myass.buttplugin.models.CustomEnchantInstance;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CEnchantCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack item = player.getItemInHand();

            if (item.getType() == Material.AIR) {
                sender.sendMessage(ChatColor.RED + "You need to be holding an item to enchant.");
                return false;
            }

            if (args.length == 0) {
                return false;
            }

            String customEnchantId = args[0];
            CustomEnchant customEnchant = EnchantManager.getCustomEnchant(customEnchantId);
            if (customEnchant == null) {
                sender.sendMessage(ChatColor.RED + "Invalid custom enchant id, " + ChatColor.GRAY + customEnchantId);
                return false;
            }

            int level = 1;
            if (args.length == 2) {
                String levelString = args[1];
                try {
                    level = Integer.parseInt(levelString);
                } catch (Exception ex) {
                    sender.sendMessage(ChatColor.RED + "Invalid enchant level, " + ChatColor.GRAY + levelString);
                    return false;
                }
            }

            if (level > customEnchant.getMaxLevel()) {
                sender.sendMessage(customEnchant.getDisplayName() + ChatColor.RED + " has valid level range "
                        + ChatColor.GRAY + "1-" + customEnchant.getMaxLevel());
                return false;
            }

            CustomEnchantInstance cei = new CustomEnchantInstance(level, customEnchant);
            EnchantManager.setCustomEnchantOnItem(cei, item);
            player.setItemInHand(item);
            player.sendMessage(ChatColor.GREEN + "Enchanted your " + ChatColor.GRAY + item.getType() + ChatColor.GREEN
                    + " with " + cei.getLoreLine());
        }

        return true;
    }

}
