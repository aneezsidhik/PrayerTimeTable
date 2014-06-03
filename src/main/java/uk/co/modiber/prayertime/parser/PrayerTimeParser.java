/**
 * 
 */
package uk.co.modiber.prayertime.parser;

import java.io.IOException;

import uk.co.modiber.prayertime.parser.event.ParsePrayerTimesEvent;
import uk.co.modiber.prayertime.parser.event.PrayerTimesEvent;

public interface PrayerTimeParser {

	public PrayerTimesEvent parse(ParsePrayerTimesEvent parseEvent) throws IOException;
}
