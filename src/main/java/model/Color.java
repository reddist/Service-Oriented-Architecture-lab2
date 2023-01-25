package model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum Color {
    @XmlEnumValue("BLACK") BLACK,
    @XmlEnumValue("BLUE") BLUE,
    @XmlEnumValue("YELLOW") YELLOW,
    @XmlEnumValue("BROWN") BROWN;

    public String value() {
        return toString();
    }

    public static Color fromValue(String v) {
        return valueOf(v);
    }
}
