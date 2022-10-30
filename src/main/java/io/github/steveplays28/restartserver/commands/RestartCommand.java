package io.github.steveplays28.restartserver.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.steveplays28.restartserver.RestartServer;
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
		return CommandManager.literal(NAME).executes(ctx -> execute(ctx.getSource())).requires((ctx) -> ctx.hasPermissionLevel(PERMISSION_LEVEL));
	}

	public static int execute(ServerCommandSource source) {
		// Send restart message to console and all players
		if (RestartServer.config.sendRestartMessage) {
			source.getServer().getPlayerManager().broadcast(Text.literal("[Restart Server] " + RestartServer.config.restartMessage).formatted(Formatting.YELLOW), RestartServer.config.sendRestartMessageInActionbar);
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
				source.getServer().sendMessage(Text.literal("[Restart Server] " + String.format("Restarting server using script '%s'...", RestartServer.config.restartScriptPath)).formatted(Formatting.YELLOW));
			} catch (IOException e) {
				RestartServer.LOGGER.info("[Restart Server] " + getStackTrace(e));

				// Send restart failed message to console and all players
				source.getServer().getPlayerManager().broadcast(Text.literal("[Restart Server] " + RestartServer.config.restartFailedMessage).formatted(Formatting.RED), RestartServer.config.sendRestartMessageInActionbar);
			}
		}

		// Stop server
		source.getServer().stop(false);

		return 1;
	}
}
