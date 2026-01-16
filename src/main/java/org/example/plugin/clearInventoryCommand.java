package org.example.plugin;

import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.console.ConsoleSender;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

/**
 * This is an example command that will simply print the name of the plugin in chat when used.
 */
public class clearInventoryCommand extends CommandBase {


    public clearInventoryCommand() {
        super("clear", "clears your inventory");
        this.setPermissionGroup(GameMode.Adventure); // Allows the command to be used by anyone, not just OP

    }

    @Override
    protected void executeSync(@Nonnull CommandContext ctx) {
        CommandSender sender = ctx.sender();
        TitleBroadcast broadcast = new TitleBroadcast();
        broadcast.ShowTitle(Universe.get().getPlayer(sender.getUuid()), "Cleared your inventory");
        if (sender instanceof Player player) {
            Inventory inventory = player.getInventory();
            inventory.dropAllItemStacks();

        } else if (sender instanceof ConsoleSender) {

        }

    }
}