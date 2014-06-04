package uk.co.modiber.prayertime.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import uk.co.modiber.prayertime.parser.event.ParsePrayerTimesEvent;
import uk.co.modiber.prayertime.parser.event.PrayerTimesEvent;
import uk.co.modiber.prayertime.utils.DateTimeUtils;

public class WembleyPrayerTimeParser implements PrayerTimeParser {

	public PrayerTimesEvent parse(ParsePrayerTimesEvent parseEvent) throws IOException {

		String url = "http://www.wembleycentralmasjid.co.uk/index.php/prayer-times";
		Document document = Jsoup.connect(url).get();
		// System.out.println(document.html());

		List<String> headers = extractHeaders(document);
		String cssQuery = "";
		Calendar from = null, to = null;
		switch (parseEvent.getPeriod()) {
		case day:
			cssQuery = "tr.today";
			break;
		case week:
			from = DateTimeUtils.getStartOfTheWeek(Calendar.getInstance());
			to = (Calendar) from.clone();
			to.add(Calendar.DAY_OF_YEAR, 6);
		case month:
			cssQuery = "tbody > tr.today, tbody > tr[class^=row]";
			break;
		default:
			break;
		}
		List<List<String>> values = extractColumnValues(document,
				cssQuery,
				getDayOfTheMonthFromCalendar(from),
				getDayOfTheMonthFromCalendar(to));
		return new PrayerTimesEvent(headers, values);
	}

	private int getDayOfTheMonthFromCalendar(Calendar calendar) {

		if ( calendar != null ) {
			return Integer.parseInt(DateTimeUtils.formatCalendarDate(calendar, "dd"));
		}
		else {
			return -1;
		}
	}

	/**
	 * @param document
	 * @param to
	 * @param from
	 * @return
	 */
	private List<List<String>> extractColumnValues(Document document, String cssQuery, int from, int to) {

		List<List<String>> values = new ArrayList<>();
		for ( Element element : document.select(cssQuery) ) {
			List<String> todaysValues = extractSingleRowColumns(element);
			int date = Integer.parseInt(todaysValues.get(0));
			if ( from < 0 ) {
				values.add(todaysValues);
			}
			else if ( date >= from && date <= to ) {
				values.add(todaysValues);
			}
		}
		return values;
	}

	/**
	 * @param document
	 * @return
	 */
	private List<String> extractHeaders(Document document) {

		Element headerElement = document.select("thead > tr").get(0);
		List<String> headers = extractSingleRowColumns(headerElement);
		return headers;
	}

	/**
	 * @param element
	 * @return
	 */
	private List<String> extractSingleRowColumns(Element element) {

		List<String> headers = new ArrayList<>();
		for ( Element el : element.select("th > span, td > span") ) {
			headers.add(el.text());
		}
		return headers;
	}

}
