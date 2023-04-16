package io.github.steveplays28.restartserver.config;

import io.github.steveplays28.restartserver.RestartServer;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = RestartServer.MOD_ID)
public class RestartServerConfig implements ConfigData {
	// Restart script
	@Comment("Set to true to run an executable restart script (such as .bat files).\nDEFAULT: true")
	public boolean runRestartScript = true;
	@Comment("The executable restart script that should run.\nDEFAULT: start.bat")
	public String restartScriptPath = "start.bat";
	@Comment("Set to true to run the executable restart script in a new terminal window.\nDEFAULT: true")
	public boolean openInTerminal = true;
	@Comment("The command that is used to open the new terminal window.\nFORMAT: terminalStartCommand %command%\nDEFAULT: cmd /c start")
	public String terminalStartCommand = "cmd /c start";
	@Comment("Set to true to stop the server when running the executable restart script.\nDEFAULT: true")
	public boolean stopServer = true;

	// Restart message
	@Comment("Set to true to send a restart message in chat.\nDEFAULT: true")
	public boolean sendRestartMessage = true;
	@Comment("Set to true to send a restart message in the actionbar instead of in chat.\nDEFAULT: false")
	public boolean sendRestartMessageInActionbar = false;
	@Comment("The restart message that will be sent in chat or in the actionbar.\nDEFAULT: Restarting server...")
	public String restartMessage = "Restarting server...";
	@Comment("The restart failed message that will be sent in chat or in the actionbar.\nDEFAULT: Server restart failed.")
	public String restartFailedMessage = "Server restart failed.";

	// Automatic restarts
	@Comment("The interval in which the server will restart.\nSet to 0 or less to disable.\nUNIT: seconds\nDEFAULT: 0")
	public long restartInterval = 0;
	@Comment("Set to true to only restart if no players have been online for a specified amount of time.\nDEFAULT: false")
	public boolean restartIfNoPlayersHaveBeenOnline = false;
	@Comment("The specified amount of time to wait after all players have disconnected to restart.\nUNIT: seconds\nDEFAULT: 7200")
	public long noPlayersWaitTime = 7200;
}
