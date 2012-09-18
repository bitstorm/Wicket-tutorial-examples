package org.wicketTutorial;

import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.wicket.util.convert.IConverter;

public class RegExpPatternConverter implements IConverter<Pattern> {

	public Pattern convertToObject(String value, Locale locale) {
		return Pattern.compile(value);
	}

	public String convertToString(Pattern value, Locale locale) {
		return value.toString();
	}

}
