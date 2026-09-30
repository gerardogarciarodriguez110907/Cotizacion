package utez.edu.mx.cotizacion.Controller.exeption.customExeptions;

public class BadRequestExeption extends RuntimeException {
    public BadRequestExeption(String message) {
        super(message);
    }
}
