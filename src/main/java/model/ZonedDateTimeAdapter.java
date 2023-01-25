package model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime>{
    final static DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public ZonedDateTime unmarshal(String v) {
        LocalDateTime ldt = LocalDateTime.parse(v, formatter);
        return ZonedDateTime.of(ldt, ZoneId.systemDefault());
    }

    public String marshal(ZonedDateTime v) {
        return v.format(formatter);
    }
}
