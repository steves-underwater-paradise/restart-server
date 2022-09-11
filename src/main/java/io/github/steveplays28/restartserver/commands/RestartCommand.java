package io.github.steveplays28.restartserver.commands;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class RestartCommand implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(literal("restart")
					.requires(source -> source.hasPermissionLevel(4))
					.executes(context -> {
						context.getSource().sendMessage(Text.literal(""));

						return 1;
					}));
		});
	}
}
