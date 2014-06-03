package uk.co.modiber.prayertime.parser.event;

import java.util.ArrayList;
import java.util.List;

public class PrayerTimesEvent {

	private List<String> headers = new ArrayList<>();

	private List<List<String>> values = new ArrayList<>();

	/**
	 * @param headers
	 * @param values
	 */
	public PrayerTimesEvent(List<String> headers, List<List<String>> values) {

		super();
		this.headers = headers;
		this.values = values;
	}

	/**
	 * @return Returns the headers.
	 */
	public List<String> getHeaders() {

		return headers;
	}

	/**
	 * @param headers The headers to set.
	 */
	public void setHeaders(List<String> headers) {

		this.headers = headers;
	}

	/**
	 * @return Returns the values.
	 */
	public List<List<String>> getValues() {

		return values;
	}

	/**
	 * @param values The values to set.
	 */
	public void setValues(List<List<String>> values) {

		this.values = values;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("PrayerTimesEvent [headers=").append(headers).append(", values=").append(values).append("]");
		return builder.toString();
	}
}
