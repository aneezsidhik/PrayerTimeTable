/**
 * 
 */
package uk.co.modiber.prayertime.parser.event;

public class ParsePrayerTimesEvent {

	public enum Period {
		day,
		week,
		month
	};

	private Period period = Period.day;

	public ParsePrayerTimesEvent() {

		this(Period.day);
	}

	public ParsePrayerTimesEvent(Period period) {

		this.period = period;
	}

	public Period getPeriod() {

		return period;
	}
}
