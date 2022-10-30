package io.github.steveplays28.restartserver.config;

import io.github.steveplays28.restartserver.RestartServer;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = RestartServer.MOD_ID)
public class RestartServerConfig implements ConfigData {
	// Restart script
	public boolean runRestartScript = true;
	public String restartScriptPath = "start.bat";
	public boolean openInTerminal = true;
	public String terminalStartCommand = "cmd /c start start.bat";

	// Restart message
	public boolean sendRestartMessage = true;
	public boolean sendRestartMessageInActionbar = false;
	public String restartMessage = "Restarting server... (eta: ~2m)";
	public String restartFailedMessage = "Server restart failed.";

	// Automatic restarts
	public long restartInterval;
	public boolean restartIfNoPlayersHaveBeenOnline = false;
	public long noPlayersWaitTime = 7200;
}
