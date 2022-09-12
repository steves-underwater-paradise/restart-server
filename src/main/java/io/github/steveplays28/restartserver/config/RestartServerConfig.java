package io.github.steveplays28.restartserver.config;

import io.github.steveplays28.restartserver.RestartServer;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = RestartServer.MOD_ID)
public class RestartServerConfig implements ConfigData {
	String restartFile = "restart-server.bat";

}
