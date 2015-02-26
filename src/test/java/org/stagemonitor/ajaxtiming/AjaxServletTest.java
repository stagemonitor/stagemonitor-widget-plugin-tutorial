package org.stagemonitor.ajaxtiming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class AjaxServletTest {

	private AjaxServlet ajaxServlet;
	private MetricRegistry metricRegistry = new MetricRegistry();

	@Before
	public void setUp() throws Exception {
		ajaxServlet = new AjaxServlet(metricRegistry);
	}

	@Test
	public void testAjaxTiming() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/stagemonitor/ajax");
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setContent("[{\"url\": \"/test\", \"duration\": 123},{\"url\": \"/test2\", \"duration\": 234}]".getBytes());
		ajaxServlet.service(request, response);

		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		assertEquals(metricRegistry.getTimers().keySet().toString(), 3, metricRegistry.getTimers().size());

		Timer testTimer = metricRegistry.getTimers().get("request.|test.ajax.time.total");
		assertNotNull(testTimer);
		assertEquals(1, testTimer.getCount());
		assertEquals(TimeUnit.MILLISECONDS.toNanos(123), testTimer.getSnapshot().getMax());

		Timer testTimer2 = metricRegistry.getTimers().get("request.|test2.ajax.time.total");
		assertNotNull(testTimer2);
		assertEquals(1, testTimer2.getCount());
		assertEquals(TimeUnit.MILLISECONDS.toNanos(234), testTimer2.getSnapshot().getMax());

		Timer allTimer = metricRegistry.getTimers().get("request.All.ajax.time.total");
		assertNotNull(allTimer);
		assertEquals(2, allTimer.getCount());
		assertEquals(TimeUnit.MILLISECONDS.toNanos(234), allTimer.getSnapshot().getMax());
		assertEquals(TimeUnit.MILLISECONDS.toNanos(123), allTimer.getSnapshot().getMin());
	}
}
