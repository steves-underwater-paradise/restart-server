package io.github.steveplays28.restartserver;

import io.github.steveplays28.restartserver.commands.RestartCommand;
import net.minecraft.server.MinecraftServer;

import java.time.Instant;

public class RestartScheduler {
	public boolean isRestartScheduled = false;
	public long nextRestart = -1;

	public boolean isNoPlayersRestartScheduled = false;
	public long nextNoPlayersRestart = -1;

	public void onTick(MinecraftServer server) {
		if (RestartServer.config.restartInterval <= 0) {
			return;
		}

		// Get current epoch time
		long now = Instant.now().getEpochSecond();

		if (isRestartScheduled) {
			if (nextRestart <= now) {
				// Restart server
				RestartCommand.execute(server.getCommandSource());
			}
		} else {
			// Schedule restart
			nextRestart = now + RestartServer.config.restartInterval;
			isRestartScheduled = true;
		}

		if (isNoPlayersRestartScheduled) {
			if (server.getCurrentPlayerCount() > 0) {
				// Unschedule no players restart
				nextNoPlayersRestart = -1;
				isNoPlayersRestartScheduled = false;
			}

			if (nextNoPlayersRestart <= now) {
				// Restart server
				RestartCommand.execute(server.getCommandSource());
			}
		} else {
			if (server.getCurrentPlayerCount() <= 0) {
				// Schedule no players restart
				nextNoPlayersRestart = now + RestartServer.config.restartInterval;
				isNoPlayersRestartScheduled = true;
			}
		}
	}
}
