package org.stagemonitor.ajaxtiming;

import java.io.IOException;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stagemonitor.core.configuration.Configuration;
import org.stagemonitor.core.util.IOUtils;
import org.stagemonitor.requestmonitor.RequestMonitor;
import org.stagemonitor.web.monitor.HttpRequestTrace;
import org.stagemonitor.web.monitor.filter.HtmlInjector;

public class AjaxTimerHtmlInjector implements HtmlInjector {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String ajaxTimingSnippet;

	@Override
	public void init(Configuration configuration, ServletContext servletContext) {
		ajaxTimingSnippet = buildAjaxTimingSnippet(servletContext.getContextPath());
	}

	private String buildAjaxTimingSnippet(String contextPath) {
		try {
			return IOUtils.toString(getClass().getClassLoader().getResourceAsStream("time-ajax-requests.html"))
					.replace("@@CONTEXT_PREFIX_PATH@@", contextPath);
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
			return "";
		}
	}

	@Override
	public String getContentToInjectBeforeClosingBody(RequestMonitor.RequestInformation<HttpRequestTrace> requestInformation) {
		return ajaxTimingSnippet;
	}

	@Override
	public boolean isActive() {
		// TODO optionally create a ConfigurationOption that controls whether ajax timing is active
		return true;
	}
}
