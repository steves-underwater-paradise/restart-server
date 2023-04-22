package io.github.steveplays28.restartserver.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.steveplays28.restartserver.RestartServer;
import net.minecraft.network.MessageType;
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

		RestartServer.LOGGER.info("[Restart Server] user.dir: " + System.getProperty("user.dir"));

		if (RestartServer.config.runRestartScript) {
			try {
				// Start restart script process
				if (RestartServer.config.openInTerminal) {
					Runtime.getRuntime().exec(String.join(" ", RestartServer.config.terminalStartCommand, RestartServer.config.restartScriptPath));
				} else {
					Runtime.getRuntime().exec(RestartServer.config.restartScriptPath);
				}

				// Send detailed restart message to console
				source.getServer().sendSystemMessage(new LiteralText("[Restart Server] " + String.format("Restarting server using script '%s'...", RestartServer.config.restartScriptPath)).formatted(Formatting.YELLOW), UUID.randomUUID());

				// Stop server
				if (RestartServer.config.stopServer) {
					source.getServer().stop(false);
				}

				return 0;
			} catch (IOException e) {
				RestartServer.LOGGER.info("[Restart Server] " + getStackTrace(e));

				// Send restart failed message to console and all players
				source.getServer().getPlayerManager().broadcast(
						new LiteralText("[Restart Server] " + RestartServer.config.restartFailedMessage).formatted(Formatting.RED),
						RestartServer.config.sendRestartMessageInActionbar
								? MessageType.SYSTEM
								: MessageType.GAME_INFO,
						UUID.randomUUID()
				);

				return 1;
			}
		} else {
			// Stop server
			if (RestartServer.config.stopServer) {
				source.getServer().stop(false);
			}

			return 0;
		}
	}
}
