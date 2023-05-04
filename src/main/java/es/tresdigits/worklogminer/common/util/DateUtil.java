package es.tresdigits.worklogminer.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil
{
	private DateUtil(){}

	/**
	 * Convierte una Date a LocalDate
	 * @param dateToConvert Date
	 * @return LocalDate
	 */
	public static LocalDate convertDateToLocalDate( Date dateToConvert )
	{
		LocalDate localDate = null;
		try	{
			localDate = dateToConvert.toInstant()
					.atZone( ZoneId.systemDefault() )
					.toLocalDate();
		}catch( Exception e ){
			log.info( e.getMessage() );
		}
		return localDate;
	}

	/**
	 * Convierte una LocalDate a Date
	 * @param dateToConvert LocalDate
	 * @return Date
	 */
	public static Date convertLocalDateToDate( LocalDate dateToConvert )
	{
		Date date = null;
		try{
			date = java.util.Date.from( dateToConvert.atStartOfDay()
					.atZone( ZoneId.systemDefault() )
					.toInstant() );
		}catch( Exception e ){
			log.info( e.getMessage() );
		}
		return date;
	}
	/**
	 * Convierte una LocalDate a Date
	 * @param dateToConvert LocalDate
	 * @return Date
	 */
	public static Date convertLocalDateTimeToDate( LocalDateTime dateToConvert )
	{
		Date date = null;
		try{
			date = java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
		}catch( Exception e ){
			log.info( e.getMessage() );
		}
		return date;
	}

	/**
	 * String to LocalDate
	 * @param date en formato YYYY-MM-DD
	 * @return LocalDate
	 */
	public static LocalDate convertStringToLocalDate( String date,String format )
	{
		LocalDate localDate = null;
		try{
			DateTimeFormatter df = DateTimeFormatter.ofPattern(format  );
			localDate = LocalDate.parse( date,df );
		}catch( Exception e ){
			log.info( e.getMessage() );
		}
		return localDate;
	}

	/**
	 * String to LocalDate
	 * @param date String
	 * @return LocalDate
	 */
	public static Date convertStringToDate( String date, String dft ) throws ParseException
	{
		Date startDate = null;
		try{
			DateFormat df = new SimpleDateFormat( dft );
			startDate = df.parse( date );
		}catch( Exception e ){
			log.info( e.getMessage() );
		}
		return startDate;
	}

	/**
	 * convierte un string a un localdatetime en funciona de un formato que le pasemos
	 * @param date string de la fecha
	 * @param dft string con el formato
	 * @return un LocalDateTime con la fecha que le hemos pasado.
	 */
	public static LocalDateTime converStringToLocalDateTime(String date, String dft){
		DateTimeFormatter format = DateTimeFormatter.ofPattern(dft);
		LocalDateTime dateTime = null;
		try {
			dateTime = LocalDateTime.parse(date, format);
		}catch( Exception e ){
			log.info( e.getMessage() );
		}
		return dateTime;
	}

	/**
	 * Convierte una date en un localDateTime
	 * @param dateToConvert la fecha en Date
	 * @return la fehca en LocalDateTime
	 */
	public static LocalDateTime convertDateToLocalDateTime(Date dateToConvert){
		LocalDateTime convertedDate  = null;
		try{
			convertedDate= dateToConvert.toInstant()
					.atZone( ZoneId.systemDefault() )
					.toLocalDateTime();

		}catch( Exception e ){
			log.info( e.getMessage() );
		}
		 return convertedDate;
	}
}
