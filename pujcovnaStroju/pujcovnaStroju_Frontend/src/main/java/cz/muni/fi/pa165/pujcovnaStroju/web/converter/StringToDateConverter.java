package cz.muni.fi.pa165.pujcovnaStroju.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author gitti
 */
public class StringToDateConverter implements
		Converter<String, Date> {
	public Date convert(String source) {
	    try {
		System.out.println("convert date");
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(source);
		return date;
	    } catch (ParseException ex) {
		return null;
	    }
	}
}
