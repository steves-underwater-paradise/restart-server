package io.github.steveplays28.restartserver.gametest;

import io.github.steveplays28.restartserver.RestartServer;
import io.github.steveplays28.restartserver.commands.RestartCommand;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;

import java.util.Arrays;

public class GameTests {
	@GameTest
	public void testRestartCommand(GameTestHelper helper) {
		var server = helper.getLevel().getServer();

		RestartServer.config.stopServer = false;
		RestartServer.config.runRestartScript = false;

		helper.assertTrue(RestartCommand.execute(server.createCommandSourceStack()) == 0, "Restart command should succeed");
		helper.succeed();
	}

	@GameTest
	public void testRestartScriptCommand(GameTestHelper helper) {
		RestartServer.config.restartScriptPath = "start.sh";

		RestartServer.config.openInTerminal = false;
		helper.assertTrue(Arrays.equals(RestartCommand.getRestartScriptCommand(), new String[]{"start.sh"}), "script should run when not opening a terminal");

		RestartServer.config.openInTerminal = true;
		RestartServer.config.terminalStartCommand = "cmd /c start";
		helper.assertTrue(Arrays.equals(RestartCommand.getRestartScriptCommand(), new String[]{"cmd", "/c", "start", "start.sh"}), "Terminal start command should be split into arguments");

		helper.succeed();
	}
}
