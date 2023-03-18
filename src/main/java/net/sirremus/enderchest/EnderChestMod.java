package net.sirremus.enderchest;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import static net.minecraft.server.command.CommandManager.literal;

public class EnderChestMod implements ModInitializer {

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            CommandDispatcher<ServerCommandSource> dispatcher = server.getCommandManager().getDispatcher();
            LiteralArgumentBuilder<ServerCommandSource> node = literal("enderchest").executes(EnderChestCommand::openEnderChest);
            LiteralArgumentBuilder<ServerCommandSource> nodeAlias = literal("ec").executes(EnderChestCommand::openEnderChest);
            dispatcher.getRoot().addChild(node.build());
            dispatcher.getRoot().addChild(nodeAlias.build());
        });
    }

    public static void openEnderChest(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player != null) {
            player.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) ->
                    new GenericContainerScreenHandler(ScreenHandlerType.GENERIC_9X3, i, player.getInventory(), player.getEnderChestInventory(), 3),
                    Text.of("Ender Chest")
            ));
        }
    }
}
