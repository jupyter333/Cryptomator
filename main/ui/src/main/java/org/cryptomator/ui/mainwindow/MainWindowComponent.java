/*******************************************************************************
 * Copyright (c) 2017 Skymatic UG (haftungsbeschränkt).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE file.
 *******************************************************************************/
package org.cryptomator.ui.mainwindow;

import dagger.Lazy;
import dagger.Subcomponent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cryptomator.ui.common.FxmlFile;
import org.cryptomator.ui.common.FxmlScene;

@MainWindowScoped
@Subcomponent(modules = {MainWindowModule.class})
public interface MainWindowComponent {

	@MainWindow
	Stage window();

	@FxmlScene(FxmlFile.MAIN_WINDOW)
	Lazy<Scene> scene();

	default void showMainWindow() {
		Stage stage = window();
		stage.setScene(scene().get());
		stage.show();
	}

	@Subcomponent.Builder
	interface Builder {

		MainWindowComponent build();
	}

}
