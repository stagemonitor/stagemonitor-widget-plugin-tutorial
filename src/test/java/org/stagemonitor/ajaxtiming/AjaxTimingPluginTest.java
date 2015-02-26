package org.stagemonitor.ajaxtiming;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.stagemonitor.core.MeasurementSession;
import org.stagemonitor.core.Stagemonitor;

public class AjaxTimingPluginTest {

	@Before
	public void setUp() throws Exception {
		Stagemonitor.startMonitoring(new MeasurementSession("test", "test", "test"));
	}

	@After
	public void tearDown() throws Exception {
		Stagemonitor.reset();
	}

	@Test
	public void testGetPathsOfWidgetMetricTabPlugins() throws Exception {
		assertTrue(Stagemonitor.getPathsOfWidgetMetricTabPlugins().contains("/stagemonitor/static/ajaxtiming/ajax-timing"));
	}
}
