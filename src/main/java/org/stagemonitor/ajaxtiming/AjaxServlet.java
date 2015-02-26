package org.stagemonitor.ajaxtiming;

import static com.codahale.metrics.MetricRegistry.name;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codahale.metrics.MetricRegistry;
import org.stagemonitor.core.Stagemonitor;
import org.stagemonitor.core.util.GraphiteSanitizer;
import org.stagemonitor.core.util.JsonUtils;

@WebServlet("/stagemonitor/ajax")
public class AjaxServlet extends HttpServlet {

	private final MetricRegistry metricRegistry;

	public AjaxServlet() {
		this(Stagemonitor.getMetricRegistry());
	}

	public AjaxServlet(MetricRegistry metricRegistry) {
		this.metricRegistry = metricRegistry;
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		for (AjaxStats ajaxStats : JsonUtils.getMapper().readValue(req.getInputStream(), AjaxStats[].class)) {
			metricRegistry.timer("request.All.ajax.time.total").update(ajaxStats.getDuration(), MILLISECONDS);
			String name = name("request", GraphiteSanitizer.sanitizeGraphiteMetricSegment(ajaxStats.getUrl()), "ajax.time.total");
			metricRegistry.timer(name).update(ajaxStats.getDuration(), MILLISECONDS);
		}
		resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

	private static class AjaxStats {
		private String url;
		private long duration;

		private String getUrl() {
			return url;
		}

		private void setUrl(String url) {
			this.url = url;
		}

		private long getDuration() {
			return duration;
		}

		private void setDuration(long duration) {
			this.duration = duration;
		}
	}
}
