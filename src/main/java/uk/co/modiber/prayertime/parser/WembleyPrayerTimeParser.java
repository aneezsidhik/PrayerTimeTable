package uk.co.modiber.prayertime.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import uk.co.modiber.prayertime.parser.event.ParsePrayerTimesEvent;
import uk.co.modiber.prayertime.parser.event.PrayerTimesEvent;

public class WembleyPrayerTimeParser implements PrayerTimeParser {

	public PrayerTimesEvent parse(ParsePrayerTimesEvent parseEvent) throws IOException {

		String url = "http://www.wembleycentralmasjid.co.uk/index.php/prayer-times";
		Document document = Jsoup.connect(url).get();
		System.out.println(document.html());

		List<String> headers = extractHeaders(document);
		List<List<String>> values = extractColumnValues(document, "tr.today");
		System.out.println(headers.size());
		System.out.println("--------------");
		System.out.println(values.get(0).size());
		// System.out.println(todayTimeTableElement.html());
		// Calendar now = Calendar.getInstance();
		// now.set(Calendar.HOUR, 0);
		// now.set(Calendar.MINUTE, 0);
		// now.set(Calendar.SECOND, 0);
		// now.set(Calendar.MILLISECOND, 0);
		// System.out.println(todaysTimes.select("span[title=" + format.format(now.getTime()) + "]").html());
		return new PrayerTimesEvent(headers, values);
	}

	/**
	 * @param document
	 * @return
	 */
	private List<List<String>> extractColumnValues(Document document, String cssQuery) {

		List<List<String>> values = new ArrayList<>();
		for ( Element element : document.select(cssQuery) ) {
			List<String> todaysValues = extractColumnValues(element);
			values.add(todaysValues);
		}
		return values;
	}

	/**
	 * @param document
	 * @return
	 */
	private List<String> extractHeaders(Document document) {

		Element headerElement = document.select("thead > tr").get(0);
		List<String> headers = extractColumnValues(headerElement);
		return headers;
	}

	/**
	 * @param element
	 * @return
	 */
	private List<String> extractColumnValues(Element element) {

		List<String> headers = new ArrayList<>();
		for ( Element el : element.select("th > span, td > span") ) {
			headers.add(el.text());
		}
		return headers;
	}

}
