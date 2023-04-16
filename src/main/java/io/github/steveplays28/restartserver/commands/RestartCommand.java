package io.github.steveplays28.restartserver.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.steveplays28.restartserver.RestartServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

public class RestartCommand {
	public static final String NAME = "restart";
	public static final int PERMISSION_LEVEL = 4;

	public static LiteralArgumentBuilder<ServerCommandSource> register() {
		return CommandManager.literal(NAME).executes(ctx -> execute(ctx.getSource())).requires((ctx) -> ctx.hasPermissionLevel(PERMISSION_LEVEL));
	}

	public static int execute(ServerCommandSource source) {
		if (RestartServer.config.sendRestartMessage) {
			// Send restart message to console
			source.getServer().sendSystemMessage(new LiteralText("[Restart Server] " + RestartServer.config.restartMessage).formatted(Formatting.YELLOW), UUID.randomUUID());
			// Disconnect all players with the restart message
			source.getServer().getPlayerManager().getPlayerList().forEach(player -> player.networkHandler.disconnect(new LiteralText(RestartServer.config.restartMessage)));
		}

		if (RestartServer.config.runRestartScript) {
			try {
				// Start restart script process
				if (RestartServer.config.openInTerminal) {
					Runtime.getRuntime().exec(String.join(" ", RestartServer.config.terminalStartCommand, RestartServer.config.restartScriptPath));
				} else {
					ProcessBuilder process = new ProcessBuilder(RestartServer.config.restartScriptPath);
					process.start();
				}

				// Send detailed restart message to console
				source.getServer().sendSystemMessage(new LiteralText("[Restart Server] " + String.format("Restarting server using script '%s'...", RestartServer.config.restartScriptPath)).formatted(Formatting.YELLOW), UUID.randomUUID());
			} catch (IOException e) {
				RestartServer.LOGGER.info("[Restart Server] " + getStackTrace(e));

				// Send restart failed message to console
				source.getServer().sendSystemMessage(new LiteralText("[Restart Server] " + RestartServer.config.restartFailedMessage).formatted(Formatting.RED), UUID.randomUUID());
			}
		}

		// Stop server
		source.getServer().stop(false);

		return 1;
	}
}
