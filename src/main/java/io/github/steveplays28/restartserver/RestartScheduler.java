package io.github.steveplays28.restartserver;

import io.github.steveplays28.restartserver.commands.RestartCommand;
import net.minecraft.server.MinecraftServer;

import java.time.Instant;

public class RestartScheduler {
	public boolean scheduled = false;
	public long nextRestart = -1;

	public void onTick(MinecraftServer server) {
		long now = Instant.now().getEpochSecond();

		if (scheduled) {
			if (nextRestart <= now) {
				RestartCommand.execute(server.getCommandSource());

				nextRestart = now + RestartServer.config.restartInterval;
			}
		} else {
			nextRestart = now + RestartServer.config.restartInterval;
			scheduled = true;
		}

		// TODO: implement no player restart scheduling
	}
}
