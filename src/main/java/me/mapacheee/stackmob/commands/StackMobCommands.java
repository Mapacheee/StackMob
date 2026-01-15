package me.mapacheee.stackmob.commands;

import com.google.inject.Inject;
import com.thewinterframework.command.CommandComponent;
import com.thewinterframework.configurate.Container;
import com.thewinterframework.service.ReloadServiceManager;
import me.mapacheee.stackmob.Config;
import me.mapacheee.stackmob.Messages;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.paper.util.sender.Source;
import org.incendo.cloud.annotations.Permission;

@CommandComponent
public class StackMobCommands {

    private final ReloadServiceManager reloadServiceManager;
    private final Container<Config> config;
    private final Container<Messages> messages;

    @Inject
    public StackMobCommands(ReloadServiceManager reloadServiceManager, Container<Config> config, Container<Messages> messages) {
        this.reloadServiceManager = reloadServiceManager;
        this.config = config;
        this.messages = messages;
    }

    @Command("stackmob reload")
    @Permission("stackmob.reload")
    public void reload(Source source) {
        reloadServiceManager.reload();

        if (source.source() instanceof Player) {
            source.source()
                .sendMessage(MiniMessage
                    .miniMessage()
                    .deserialize("<green>StackMob has been reloaded."));
        }
    }
}