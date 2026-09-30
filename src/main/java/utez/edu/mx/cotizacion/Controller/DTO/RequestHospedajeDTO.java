package utez.edu.mx.cotizacion.Controller.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHospedajeDTO {

    @NotBlank(message = "El nombre del hospedaje es obligatorio")
    private String nombreHospedaje;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    private String tipoH;

    @NotNull(message = "El número de noches es obligatorio")
    private Integer noches;

    @NotNull(message = "El total de huéspedes es obligatorio")
    private Integer totalHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    private String temporada;

    private boolean desayuno;
    private boolean estacionamiento;
}