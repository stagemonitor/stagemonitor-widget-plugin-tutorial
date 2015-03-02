package org.stagemonitor.ajaxtiming;

import java.util.Arrays;
import java.util.List;

import org.stagemonitor.core.StagemonitorPlugin;

/**
 * This plugin is registered by adding the class name to
 * src/main/resources/META-INF/services/org.stagemonitor.core.StagemonitorPlugin
 */
public class AjaxTimingPlugin extends StagemonitorPlugin {

	@Override
	public List<String> getPathsOfWidgetMetricTabPlugins() {
		// TODO read the javadoc of the superclass and provide the path of the ajax-timing plugin
		// hint: look at the test if you don't know what to do
		return Arrays.asList();
	}
}
