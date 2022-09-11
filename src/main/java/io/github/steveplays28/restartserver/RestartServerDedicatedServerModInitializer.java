package io.github.steveplays28.restartserver;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class RestartServerDedicatedServerModInitializer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("foo")
				.executes(context -> {
					// For versions below 1.19, replace "Text.literal" with "new LiteralText".
					context.getSource().sendMessage(Text.literal("Called /foo with no arguments"));

					return 1;
				})));
	}
}
