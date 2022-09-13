package io.github.steveplays28.restartserver.config;

import io.github.steveplays28.restartserver.RestartServer;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = RestartServer.MOD_ID)
public class RestartServerConfig implements ConfigData {
	// Restart script
	public String restartScriptLinux = "start-server.sh";
	public String restartScriptMacOS = "start-server.sh";
	public String restartScriptWindows = "start-server.bat";

	// Messages
	public String restartMessage = "Restarting server... (eta: ~2m)";

	// Automatic restarts
	public long restartInterval;
	public String[] restartTimes;
	public boolean restartIfNoPlayersHaveBeenOnline = false;
	public long noPlayersWaitTime = 7200;
}
