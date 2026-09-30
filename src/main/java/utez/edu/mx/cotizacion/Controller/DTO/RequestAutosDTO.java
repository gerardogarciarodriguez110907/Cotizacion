package utez.edu.mx.cotizacion.Controller.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter


@AllArgsConstructor
@NoArgsConstructor
public class RequestAutosDTO {


    private String nombre;
    private Integer edad;
    private String tipoV;
    private int diasR;
    private double kilometros;
    private boolean seguro;
}
