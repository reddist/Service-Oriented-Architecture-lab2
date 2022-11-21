package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class Person {
    @NotNull
    @Positive
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotNull
    @NotEmpty
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    private Coordinates coordinates; //Поле не может быть null

    @NotNull
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Positive
    private Double height; //Поле может быть null, Значение поля должно быть больше 0

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 34)
    private String passportID; //Строка не может быть пустой, Длина строки должна быть не меньше 10, Длина строки не должна быть больше 34, Поле не может быть null

    private Color eyeColor; //Поле может быть null

    private Country nationality; //Поле может быть null

    @NotNull
    private Location location; //Поле не может быть null
}
