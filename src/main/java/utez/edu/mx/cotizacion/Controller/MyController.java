package utez.edu.mx.cotizacion.Controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.cotizacion.Controller.DTO.RequestAutosDTO;
import utez.edu.mx.cotizacion.Controller.DTO.RequestEnviosDTO;
import utez.edu.mx.cotizacion.Controller.DTO.RequestHospedajeDTO;
import utez.edu.mx.cotizacion.Service.MyService;

@RestController
@CrossOrigin({"*"}) // todos los origenes
@RequestMapping("/my-services")
public class MyController {
    private final MyService service;

    public MyController(MyService service) {
        this.service = service;
    }


    @PostMapping("/envios")
    public double envios(@RequestBody @Valid RequestEnviosDTO payload) {
        return service.envios(payload);
    }


    @PostMapping("/autos")
    public double autos (@RequestBody @Valid RequestAutosDTO payload){
        return service.autos(payload);
    }


    @PostMapping("/hotel")
    public double hotel (@RequestBody @Valid RequestHospedajeDTO payload){
        return service.hotel(payload);
    }


}
