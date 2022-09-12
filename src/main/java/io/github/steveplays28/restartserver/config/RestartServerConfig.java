package io.github.steveplays28.restartserver.config;

import io.github.steveplays28.restartserver.RestartServer;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = RestartServer.MOD_ID)
public class RestartServerConfig implements ConfigData {
	@ConfigEntry.Category("Restart script")
	public String restartScriptLinux = "start-server.sh";
	public String restartScriptMacOS = "start-server.sh";
	public String restartScriptWindows = "start-server.bat";

	@ConfigEntry.Category("Restart message")
	public String restartMessage = "Restarting server... (eta: ~2m)";
}
