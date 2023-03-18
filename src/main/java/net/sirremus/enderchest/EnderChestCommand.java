package net.sirremus.enderchest;

import net.minecraft.server.command.ServerCommandSource;
import com.mojang.brigadier.context.CommandContext;

public class EnderChestCommand {
    public static int openEnderChest(CommandContext<ServerCommandSource> context) {
        EnderChestMod.openEnderChest(context);
        return 1;
    }
}
