(function () {
	plugins.push(
		{
			// the pluginId
			// has to be the same as the filename of the JS and html file
			// also, the html and the JS file have to be in the same folder
			id: "ajax-timing",
			// The label displayed in the side menu of the metrics tab
			label: "Ajax",
			// creates searchable and pageable datatable
			table: {
				// jQuery expression of a element in the plugin html file the table should be bound to
				bindto: "#ajax-timing-table",
				// the label of the first column
				nameLabel: "Name",
				columns: [
					{
						// the metrics are grouped by the categories gauges, counters, timers, meters and histograms
						metricCategory: "timers",
						// A regex of metric paths. A table row is created for each distinct regex group. That's
						// why the wildcard [^\.]+ is put in parentesis. That way a row is created for each individual
						// cache name
						metricPathRegex: /request.([^\.]+).ajax.time.total/,
						metric: "m1_rate",
						// the column label
						title: "Requests/s"
					},
					{
						metricCategory: "timers",
						metricPathRegex: /request.([^\.]+).ajax.time.total/,
						metric: "max",
						title: "Max"
					},
					{
						metricCategory: "timers",
						metricPathRegex: /request.([^\.]+).ajax.time.total/,
						metric: "mean",
						title: "Mean"
					},
					{
						metricCategory: "timers",
						metricPathRegex: /request.([^\.]+).ajax.time.total/,
						metric: "min",
						title: "Min"
					},
					{
						metricCategory: "timers",
						metricPathRegex: /request.([^\.]+).ajax.time.total/,
						metric: "p50",
						title: "p50"
					},
					{
						metricCategory: "timers",
						metricPathRegex: /request.([^\.]+).ajax.time.total/,
						metric: "p95",
						title: "p95"
					},
					{
						metricCategory: "timers",
						metricPathRegex: /request.([^\.]+).ajax.time.total/,
						metric: "stddev",
						title: "Std. Dev."
					}
				],
				// Optionally graph templates can be defined.
				// Those templates contain the placeholder ${rowName}
				// Each time a row in the table is selected the placeholder gets replaced by the row name (e.g. the cache name)
				graphTemplates: {
					// This is the default value for the placeholder ${rowName} when no row is selected.
					defaultRowSelection: 'All',
					templates: [
						{
							template: {
								// jQuery expression of a element in the plugin html file the graph should be bound to
								bindto: '#ajax-time',
								// the minimal value of the y-axis in the graph
								min: 0,
								// the maximum value of the y-axis in the graph
								// max: ,
								// the format of the values
								// one of percent|bytes|ms
								// or some arbitrary string that is appended to the values e.g. 'requests/sec'
								format: 'ms',
								// the amount of colouring of the area between the graph and the x-axis
								fill: 0.1,
								columns: [
									{
										metricCategory: "timers",
										// A regex of metric paths. A line is created for each distinct regex group.
										// That's why the placeholder ${rowName} is put in parentesis.
										// That way a line is created and named after the selected cache name
										metricPathRegex: "request.(${rowName}).ajax.time.total",
										metric: "mean"
									}
								]
							}
						},
						{
							template: {
								bindto: '#ajax-throughput',
								min: 0,
								format: 'requests/sec',
								fill: 0.1,
								columns: [
									{ metricCategory: "timers", metricPathRegex: "request.(${rowName}).ajax.time.total", metric: "m1_rate"}
								]
							}
						}
					]
				}

			}
		}
	);
}());

