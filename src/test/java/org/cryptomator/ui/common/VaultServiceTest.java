package org.cryptomator.ui.common;

import javafx.application.Application;

import javafx.concurrent.Task;

import dagger.Lazy;
import org.cryptomator.common.vaults.Vault;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.*;

public class VaultServiceTest {

	@Mock
	private Lazy<Application> application;

	@Mock
	private Application mockApplication;

	@Mock
	private Vault vault;

	@Mock
	private ExecutorService executorService;

	@InjectMocks
	private VaultService vaultService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		when(application.get()).thenReturn(mockApplication);
	}

	@Test
	public void testCreateReveal() {

		Task<Vault> task = vaultService.createRevealTask(vault);
		assertNotNull(task);

		task.run();
	}

	@Test
	public void testLockAndReveal() {
		VaultService service  = new VaultService(application, executorService);
		// Test reveal
		service.reveal(vault);
		verify(executorService, times(1)).execute(any(Task.class));

		service.lock(vault, true);
		verify(executorService, times(2)).execute(any(Task.class));

	}

}