package io.github.steveplays28.restartserver.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.io.IOException;

public class RestartCommand {
	public static LiteralArgumentBuilder<ServerCommandSource> register() {
		return CommandManager.literal("restart")
				.executes(ctx -> execute(ctx.getSource()));
	}

	private static int execute(ServerCommandSource source) {
		source.getServer().getPlayerManager().broadcast(Text.of("§eRestarting server... (eta: ETA_HERE)"), false);

		try {
			ProcessBuilder process = new ProcessBuilder("start-server.bat");
			process.start();
			source.getServer().sendMessage(Text.of("[RestartServer] Attempting to run start-server.bat..."));
		} catch (IOException e) {
			e.printStackTrace();
		}

		source.getServer().stop(false);
		source.getServer().sendMessage(Text.of("[RestartServer] Stopping server..."));
		return 1;
	}
}
