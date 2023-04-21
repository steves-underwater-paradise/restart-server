package io.github.steveplays28.restartserver.gametest;

import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;

import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;

public class EmptyTest {
	@GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE, batchId = "test_run")
	public void testRuns(TestContext context) {
		context.complete();
	}
}
