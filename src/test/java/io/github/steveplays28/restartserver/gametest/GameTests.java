package io.github.steveplays28.restartserver.gametest;

import io.github.steveplays28.restartserver.RestartServer;
import io.github.steveplays28.restartserver.commands.RestartCommand;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

public class GameTests {
	@GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE, batchId = "test_restart_command")
	public void testRestartCommand(TestContext context) {
		var world = context.getWorld();
		var server = context.getWorld().getServer();

		RestartServer.config.stopServer = false;
		RestartServer.config.runRestartScript = false;

		var source = new ServerCommandSource(CommandOutput.DUMMY, new Vec3d(0, 0, 0), new Vec2f(0, 0), world, 0, "yes", Text.of("yes"), server, new ZombieEntity(world));
		RestartCommand.execute(source);

		context.complete();
	}
}
