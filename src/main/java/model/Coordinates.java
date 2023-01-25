package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    @XmlElement(name = "x")
    @NotNull
    @Max(value = 273)
    private Long x; //Максимальное значение поля: 273, Поле не может быть null

    @XmlElement(name = "y")
    @NotNull
    @Max(value = 343)
    private Long y; //Максимальное значение поля: 343, Поле не может быть null
}
