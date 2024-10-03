package org.cryptomator.ui.common;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.concurrent.Task;

import dagger.Lazy;
import org.cryptomator.common.vaults.Vault;
import org.cryptomator.integrations.mount.Mountpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

public class VaultServiceTest {

	@Mock
	private Lazy<Application> application;

	@Mock
	private Application mockApplication;

	@Mock
	private HostServices hostServices;

	@Mock
	private Vault vault;

	@Mock
	private ExecutorService executorService;

	@InjectMocks
	private VaultService vaultService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// Mock application to return host services
		when(application.get()).thenReturn(mockApplication);
		when(mockApplication.getHostServices()).thenReturn(hostServices);
	}

	@Test
	public void testCreateRevealTaskSuccess() {
		// Mock vault display name and path
		when(vault.getDisplayName()).thenReturn("TestVault");
		when(vault.getMountPoint()).thenReturn(Mountpoint.forPath(Path.of("/test/path")));

		Task<Vault> task = vaultService.createRevealTask(vault);

		// Verify task is created and runnable
		assert task != null;

		// Simulate success of the task
		task.setOnSucceeded(event -> {
			verify(vault, times(1)).getDisplayName();
		});

		task.run();
	}

	@Test
	public void testCreateRevealTaskFailure() {
		// Mock vault display name
		when(vault.getDisplayName()).thenReturn("TestVault");

		// Create a custom task that throws an exception during execution
		Task<Vault> task = new Task<>() {
			@Override
			protected Vault call() {
				throw new RuntimeException("Task failed");
			}
		};

		task.setOnFailed(event -> {

			verify(vault, times(1)).getDisplayName();
		});

		task.run();
	}
}
