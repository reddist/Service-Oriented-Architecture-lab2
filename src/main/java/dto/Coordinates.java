package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Coordinates {
    @NotNull
    @Max(value = 273)
    private Long x; //Максимальное значение поля: 273, Поле не может быть null

    @NotNull
    @Max(value = 343)
    private Long y; //Максимальное значение поля: 343, Поле не может быть null
}
