package io.github.steveplays28.restartserver.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.steveplays28.restartserver.RestartServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;

public class RestartCommand {
	public static final String NAME = "restart";
	public static final int PERMISSION_LEVEL = 4;

	public static String restartScript;

	public static LiteralArgumentBuilder<ServerCommandSource> register() {
		return CommandManager.literal(NAME).executes(ctx -> execute(ctx.getSource())).requires((ctx) -> ctx.hasPermissionLevel(PERMISSION_LEVEL));
	}

	private static int execute(ServerCommandSource source) {
		// Send restart message to console and all players
		source.getServer().getPlayerManager().broadcast(Text.literal(String.format("[RestartServer] %s", RestartServer.config.restartMessage)).formatted(Formatting.YELLOW), false);

		try {
			// Determine OS
			if (SystemUtils.IS_OS_LINUX) {
				restartScript = RestartServer.config.restartScriptLinux;
			} else if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
				restartScript = RestartServer.config.restartScriptMacOS;
			} else if (SystemUtils.IS_OS_WINDOWS) {
				restartScript = RestartServer.config.restartScriptWindows;
			}

			// Start server restart script process
			ProcessBuilder process = new ProcessBuilder(restartScript);
			process.start();
			source.getServer().sendMessage(Text.literal(String.format("[RestartServer] Restarting server using script %s...", restartScript)).formatted(Formatting.YELLOW));
		} catch (IOException e) {
			e.printStackTrace();

			// Send restart failed message to console and all players
			source.getServer().getPlayerManager().broadcast(Text.literal("[RestartServer] Server restart failed.").formatted(Formatting.RED), false);
		}

		// Stop server
		source.getServer().stop(false);

		return 1;
	}
}
