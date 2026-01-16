package org.example.plugin;

import com.hypixel.hytale.protocol.FormattedMessage;
import com.hypixel.hytale.protocol.MaybeBool;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.util.EventTitleUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TitleBroadcast{

    private static final Message TITLE = createTitle();
    private static final String WHITE = "#FFFFFF";


    public void ShowTitle(PlayerRef playerRef, String text) {
        FormattedMessage message = new FormattedMessage();
        message.rawText = text;
        Message subtitle = new Message(message);
        EventTitleUtil.showEventTitleToPlayer(playerRef,subtitle, TITLE, true);

    }



    private static Message createTitle() {
        FormattedMessage celestial = new FormattedMessage();
        celestial.rawText = "Celestial";
        celestial.color = "#87CEEB";

        FormattedMessage hytale = new FormattedMessage();
        hytale.rawText = "Hytale";
        hytale.color = "#00CED1";

        FormattedMessage message = new FormattedMessage();
        message.bold = MaybeBool.True;
        message.children = new FormattedMessage[] { celestial, hytale };

        return new Message(message);
    }
}
