package io.github.steveplays28.restartserver.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.steveplays28.restartserver.RestartServer;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.io.IOException;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

public class RestartCommand {
	public static final String NAME = "restart";
	public static final int PERMISSION_LEVEL = 4;

	public static LiteralArgumentBuilder<ServerCommandSource> register() {
		return CommandManager.literal(NAME).executes(ctx -> execute(ctx.getSource())).requires((ctx) -> Permissions.check(ctx, "restart_server.commands."+NAME, PERMISSION_LEVEL));
	}

	public static int execute(ServerCommandSource source) {
		if (RestartServer.config.sendRestartMessage) {
			// Send restart message to console
			source.getServer().sendMessage(Text.literal("[Restart Server] " + RestartServer.config.restartMessage).formatted(Formatting.YELLOW));
			// Disconnect all players with the restart message
			source.getServer().getPlayerManager().getPlayerList().forEach(player -> player.networkHandler.disconnect(Text.literal(RestartServer.config.restartMessage)));
		}

		if (RestartServer.config.runRestartScript) {
			try {
				// Start restart script process
				if (RestartServer.config.openInTerminal) {
					Runtime.getRuntime().exec(String.join(" ", RestartServer.config.terminalStartCommand, RestartServer.config.restartScriptPath));
				} else {
					Runtime.getRuntime().exec(RestartServer.config.restartScriptPath);
				}

				// Send detailed restart message to console
				source.getServer().sendMessage(Text.literal("[Restart Server] " + String.format("Restarting server using script '%s'...", RestartServer.config.restartScriptPath)).formatted(Formatting.YELLOW));

				// Stop server
				if (RestartServer.config.stopServer) {
					source.getServer().stop(false);
				}

				return 0;
			} catch (IOException e) {
				RestartServer.LOGGER.info("[Restart Server] " + getStackTrace(e));

				// Send restart failed message to console and all players
				source.getServer().getPlayerManager().broadcast(Text.literal("[Restart Server] " + RestartServer.config.restartFailedMessage).formatted(Formatting.RED), RestartServer.config.sendRestartMessageInActionbar);

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
