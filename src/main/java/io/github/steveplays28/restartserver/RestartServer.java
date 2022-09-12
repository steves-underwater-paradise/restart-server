package io.github.steveplays28.restartserver;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.steveplays28.restartserver.commands.RestartCommand;
import io.github.steveplays28.restartserver.config.RestartServerConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.command.ServerCommandSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestartServer implements DedicatedServerModInitializer {
	public static final String MOD_ID = "restart-server";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static RestartServerConfig config;

	@Override
	public void onInitializeServer() {
		LOGGER.info("[RestartServer] Loading!");

		// Register Auto Config
		AutoConfig.register(RestartServerConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(RestartServerConfig.class).getConfig();

		// Register events
		ServerTickEvents.END_SERVER_TICK.register(new RestartScheduler()::tick);

		// Register commands
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(
				LiteralArgumentBuilder.<ServerCommandSource>literal("backup")
						.requires((ctx) -> ctx.hasPermissionLevel(4)
						)
						.then(RestartCommand.register())
		));
	}
}
