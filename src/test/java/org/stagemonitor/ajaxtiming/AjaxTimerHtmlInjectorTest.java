package org.stagemonitor.ajaxtiming;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ServiceLoader;
import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.stagemonitor.core.configuration.Configuration;
import org.stagemonitor.requestmonitor.RequestMonitor;
import org.stagemonitor.web.monitor.filter.HtmlInjector;

public class AjaxTimerHtmlInjectorTest {

	private AjaxTimerHtmlInjector ajaxTimerHtmlInjector = new AjaxTimerHtmlInjector();

	@Before
	public void setUp() throws Exception {
		ServletContext servletContext = mock(ServletContext.class);
		when(servletContext.getContextPath()).thenReturn("/petclinic");
		ajaxTimerHtmlInjector.init(mock(Configuration.class), servletContext);

	}

	@Test
	public void testGetContentToInjectBeforeClosingBody() throws Exception {
		String ajaxTimingSnippet = ajaxTimerHtmlInjector.getContentToInjectBeforeClosingBody(mock(RequestMonitor.RequestInformation.class));
		assertTrue(ajaxTimingSnippet.startsWith("<script type=\"text/javascript\">"));
		assertTrue(ajaxTimingSnippet.contains("xhr.open(\"POST\", \"/petclinic/stagemonitor/ajax\", true);"));
	}

	@Test
	public void testServiceLoader() throws Exception {
		boolean containsAjaxTimerHtmlInjector = false;
		for (HtmlInjector timerHtmlInjector : ServiceLoader.load(HtmlInjector.class)) {
			if (timerHtmlInjector instanceof AjaxTimerHtmlInjector) {
				containsAjaxTimerHtmlInjector = true;
			}
		}
		assertTrue(containsAjaxTimerHtmlInjector);
	}
}
