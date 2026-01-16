package org.example.plugin.randomItems;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;
import java.io.FileNotFoundException;

public class randomItemSetup extends JavaPlugin {

    public randomItemSetup(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        super.setup();
        try {
            this.getEntityStoreRegistry().registerSystem(new randomItemGiver());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
