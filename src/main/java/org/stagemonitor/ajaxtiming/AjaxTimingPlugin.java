package org.stagemonitor.ajaxtiming;

import java.util.Arrays;
import java.util.List;

import org.stagemonitor.core.StagemonitorPlugin;

/**
 * This plugin is registered by adding the classname to
 * src/main/resources/META-INF/services/org.stagemonitor.core.StagemonitorPlugin
 */
public class AjaxTimingPlugin extends StagemonitorPlugin {

	/**
	 * Tells stagemonitor that the metrics tab of the in browser widget should be extended
	 *
	 * @return the path of the ajax-timing metric tab plugin of the in browser widget
	 */
	@Override
	public List<String> getPathsOfWidgetMetricTabPlugins() {
		return Arrays.asList("/stagemonitor/static/ajaxtiming/ajax-timing");
	}
}
