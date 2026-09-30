package utez.edu.mx.cotizacion.Controller.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter


@AllArgsConstructor
@NoArgsConstructor

public class RequestEnviosDTO {
    private String codigo;
    private double pesoKG;
    private double largo;
    private double ancho;
    private double alto;
    private String tipoenvio;
    private double valor;


}
