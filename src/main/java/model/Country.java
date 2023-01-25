package model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum Country {
    @XmlEnumValue("INDIA") INDIA,
    @XmlEnumValue("VATICAN") VATICAN,
    @XmlEnumValue("THAILAND") THAILAND,
    @XmlEnumValue("NORTH_KOREA") NORTH_KOREA,
    @XmlEnumValue("JAPAN") JAPAN;

    public String value() {
        return toString();
    }

    public static Country fromValue(String v) {
        return valueOf(v);
    }
}
