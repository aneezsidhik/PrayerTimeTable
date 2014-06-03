package uk.co.modiber.prayertime.parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.modiber.prayertime.parser.event.ParsePrayerTimesEvent;
import uk.co.modiber.prayertime.parser.event.ParsePrayerTimesEvent.Period;
import uk.co.modiber.prayertime.parser.event.PrayerTimesEvent;

public class WembleyPrayerTimeParserTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testParse_Today() throws IOException {

		PrayerTimeParser parser = new WembleyPrayerTimeParser();
		PrayerTimesEvent prayerTimes = parser.parse(new ParsePrayerTimesEvent());
		assertEquals(12, prayerTimes.getHeaders().size());
		assertEquals(1, prayerTimes.getValues().size());
		for ( List<String> values : prayerTimes.getValues() ) {
			assertEquals(12, values.size());
		}
	}

	@Test
	public void testParse_Week() throws IOException {

		PrayerTimeParser parser = new WembleyPrayerTimeParser();
		PrayerTimesEvent prayerTimes = parser.parse(new ParsePrayerTimesEvent(Period.week));
		assertEquals(12, prayerTimes.getHeaders().size());
		assertEquals(7, prayerTimes.getValues().size());
		for ( List<String> values : prayerTimes.getValues() ) {
			assertEquals(12, values.size());
		}
	}
}
