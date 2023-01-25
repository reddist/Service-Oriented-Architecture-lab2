package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.ZonedDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @XmlElement(name = "id")
    @NotNull
    @Positive
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @XmlElement(name = "name")
    @NotNull
    @NotEmpty
    private String name; //Поле не может быть null, Строка не может быть пустой

    @XmlElement(type = Coordinates.class, name = "coordinates")
    @NotNull
    private Coordinates coordinates; //Поле не может быть null

    @XmlElement(type = String.class, name = "creationDate")
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    @NotNull
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @XmlElement(name = "height")
    @Positive
    private Double height; //Поле может быть null, Значение поля должно быть больше 0

    @XmlElement(name = "passportID")
    @NotNull
    @NotEmpty
    @Size(min = 10, max = 34)
    private String passportID; //Строка не может быть пустой, Длина строки должна быть не меньше 10, Длина строки не должна быть больше 34, Поле не может быть null

    @XmlElement(type = Color.class, name = "eyeColor")
    private Color eyeColor; //Поле может быть null

    @XmlElement(type = Country.class, name = "nationality")
    private Country nationality; //Поле может быть null

    @XmlElement(type = Location.class, name = "location")
    @NotNull
    private Location location; //Поле не может быть null
}
