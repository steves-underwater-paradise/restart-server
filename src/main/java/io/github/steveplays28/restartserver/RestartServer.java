package io.github.steveplays28.restartserver;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.steveplays28.restartserver.commands.RestartCommand;
import io.github.steveplays28.restartserver.config.RestartServerConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.command.ServerCommandSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RestartServer implements DedicatedServerModInitializer {
	public static final String MOD_ID = "restart-server";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static RestartServerConfig config;
	public static final List<LiteralArgumentBuilder<ServerCommandSource>> COMMANDS = List.of(RestartCommand.register());

	@Override
	public void onInitializeServer() {
		LOGGER.info("[Restart Server] Loading!");

		// Register Auto Config
		AutoConfig.register(RestartServerConfig.class, JanksonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(RestartServerConfig.class).getConfig();

		// Listen for when the server is reloading (i.e. /reload), and reload the config
		ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((s, m) -> {
			LOGGER.info("[Restart Server] Reloading config!");
			AutoConfig.getConfigHolder(RestartServerConfig.class).load();
			config = AutoConfig.getConfigHolder(RestartServerConfig.class).getConfig();
			LOGGER.info("[Restart Server] Reloaded config!");
		});

		// Register events
		ServerTickEvents.END_SERVER_TICK.register(new RestartScheduler()::onTick);

		// Register commands
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			for (var command : COMMANDS) {
				dispatcher.register(command);
			}
		});
	}
}
