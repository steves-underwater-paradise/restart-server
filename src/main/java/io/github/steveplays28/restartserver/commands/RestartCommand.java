package io.github.steveplays28.restartserver.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.steveplays28.restartserver.RestartServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.io.IOException;

public class RestartCommand {
	public static String name = "restart";
	public static int permissionLevel = 4;

	public static LiteralArgumentBuilder<ServerCommandSource> register() {
		return CommandManager.literal(name).executes(ctx -> execute(ctx.getSource())).requires((ctx) -> ctx.hasPermissionLevel(permissionLevel));
	}

	private static int execute(ServerCommandSource source) {
		source.getServer().getPlayerManager().broadcast(Text.of(String.format("§eRestarting server... (eta: %s)", RestartServer.config.eta)), false);

		try {
			ProcessBuilder process = new ProcessBuilder(RestartServer.config.restartFile);
			process.start();
			source.getServer().sendMessage(Text.of(String.format("[RestartServer] Restarting server using %s...", RestartServer.config.restartFile)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		source.getServer().stop(false);
		source.getServer().sendMessage(Text.of("[RestartServer] Stopping server..."));
		return 1;
	}
}
