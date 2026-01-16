package org.example.plugin.randomItems;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.system.tick.TickingSystem;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.CombinedItemContainer;
import com.hypixel.hytale.server.core.modules.time.WorldTimeResource;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class randomItemGiver extends TickingSystem<EntityStore> {

    Gson gson = new Gson();
    FileReader reader = new FileReader("C:\\Users\\orile\\IdeaProjects\\hytaleModTest\\src\\main\\java\\org\\example\\plugin\\randomItems\\items_icons.json");

    Type listType = new TypeToken<List<String>>(){}.getType();
    List<String> itemIds = gson.fromJson(reader, listType);
    Random random = new Random();


    Instant lastTick = null;

    public randomItemGiver() throws FileNotFoundException {
    }

    @Override
    public void tick(float v, int i, @NonNullDecl Store<EntityStore> store) {
        var timeResource = store.getResource(WorldTimeResource.getResourceType());
        Instant cur = timeResource.getGameTime();

        if (lastTick == null){
            lastTick = cur;
            return;
        }

        if (cur.isAfter(lastTick.plus(1, ChronoUnit.MINUTES))){
            for (PlayerRef playerRef : Universe.get().getPlayers()){
                Ref<EntityStore> ref = playerRef.getReference();
                Player player = store.getComponent(ref, Player.getComponentType());
                Inventory inventory = player.getInventory();
                CombinedItemContainer combined = inventory.getCombinedHotbarFirst();

                int randomIndex = random.nextInt(itemIds.size());
                ItemStack stack = new ItemStack(itemIds.get(randomIndex));
                combined.addItemStack(stack);

            }

        }


    }
}
