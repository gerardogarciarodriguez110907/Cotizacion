package utez.edu.mx.cotizacion.Service;


import org.springframework.stereotype.Service;
import utez.edu.mx.cotizacion.Controller.DTO.RequestAutosDTO;
import utez.edu.mx.cotizacion.Controller.DTO.RequestEnviosDTO;
import utez.edu.mx.cotizacion.Controller.DTO.RequestHospedajeDTO;
import utez.edu.mx.cotizacion.Controller.exeption.customExeptions.BadRequestExeption;

@Service
public class MyService {

    public double envios(RequestEnviosDTO payload) {
        double costo = 0;
        double costobase = 80;
        double costokg = 12;
        double valumen = payload.getLargo() * payload.getAncho() * payload.getAlto();
        double seguro = 0;


        // validar que no pesen mas de 50 kg

        if (payload.getPesoKG() > 50) {
            throw new BadRequestExeption("EL peso del envio supera los 50 kg");
        }

// Validar si la dimension de cualquiera es mayor a 150
        if (payload.getLargo() > 150 || payload.getAncho() > 150 || payload.getAlto() > 150) {
            throw new BadRequestExeption("El valor de la dimension es mayor de 150");
        }


        //Validar si existe el paquete
        String tipo = payload.getTipoenvio();
        if (tipo == null || (!tipo.equals("EXPRESS") && !tipo.equals("MISMO_DIA") && !tipo.equals("ESTANDAR"))) {
            throw new BadRequestExeption("El tipo de envio no existe");
        }


        // Validar si volumen es mayor a 1 000 000

        if (valumen > 1000000) {
            throw new BadRequestExeption("El valor del voluen es mayor al esperado ");
        }

        costo = costobase + (payload.getPesoKG() * costokg);

        if (valumen > 50000) {
            costo = costo + 100;
        }

        if (payload.getTipoenvio().equals("EXPRESS")) {
            costo = costo * 1.40;

        }

        if (payload.getTipoenvio().equals("MISMO_DIA")) {
            costo = costo * 1.70;
        }


        if (costo > 10000) {
            seguro = costo * 0.02;
            costo += seguro;
        }


        return costo;

    }


    public double autos(RequestAutosDTO payload) {


        if (payload.getEdad() < 18) {
            throw new BadRequestExeption("Eres menor de edad para rentar un auto");
        }

        if (payload.getDiasR() > 30) {
            throw new BadRequestExeption("No se puede rentar mas de 30 dias");
        }


        double costoAutoPorDia;
        switch (payload.getTipoV()) {
            case "COMPACTO":
                costoAutoPorDia = 550;
                break;
            case "SEDAN":
                costoAutoPorDia = 700;
                break;
            case "SUV":
                costoAutoPorDia = 950;
                break;
            case "CAMIONETA":
                costoAutoPorDia = 1200;
                break;
            default:
                throw new BadRequestExeption("El tipo de auto no existe");
        }


        double costoRenta = costoAutoPorDia * payload.getDiasR();


        if (payload.isSeguro()) {
            costoRenta += payload.getDiasR() * 180;
        }


        if (payload.getDiasR() > 7) {
            costoRenta *= 0.90;
        }


        double kilometrosIncluidos = payload.getDiasR() * 100;
        double costoKmExtra = 0;

        if (payload.getKilometros() > kilometrosIncluidos) {
            double kmExcedidos = payload.getKilometros() - kilometrosIncluidos;
            costoKmExtra = kmExcedidos * 4;
        }


        double recargoEdad = 0;
        if (payload.getEdad() >= 18 && payload.getEdad() <= 24) {
            recargoEdad = costoRenta * 0.15;
        }


        return costoRenta + costoKmExtra + recargoEdad;
    }


    public double hotel(RequestHospedajeDTO payload) {

        double costoNoche = 0;
        double costoHospedaje = 0;
        double costodesayuno = 0;
        double costoEstacionamiento = 0;

        //Validaciones


        if (payload.getNoches() > 30) {
            throw new BadRequestExeption("El maximo de dias son 30");

        }


        switch (payload.getTipoH().toUpperCase()) {
            case "INDIVIDUAL":
                if (payload.getTotalHuespedes() > 1) { //[cite: 4]
                    throw new BadRequestExeption("La habitación individual solo permite máximo 1 persona");
                }
                costoNoche = 700;
                break;

            case "DOBLE":
                if (payload.getTotalHuespedes() > 2) { //[cite: 4]
                    throw new BadRequestExeption("La habitación doble solo permite máximo 2 personas");
                }
                costoNoche = 1100;
                break;

            case "SUITE":
                if (payload.getTotalHuespedes() > 4) { //[cite: 4]
                    throw new BadRequestExeption("La suite solo permite máximo 4 personas");
                }
                costoNoche = 1800;
                break;

            default:
                throw new BadRequestExeption("El tipo de habitación no existe");
        }
        costoHospedaje = costoNoche * payload.getNoches();


        if (payload.getTemporada() == "BAJA") {
            costoHospedaje = costoNoche * 0.90;
        }

        if (payload.getTemporada() == "REGULAR") {
            costoHospedaje = costoHospedaje;
        }

        if (payload.getTemporada() == "ALTA") {
            costoHospedaje = costoNoche * 1.25;
        }


        if (payload.isDesayuno() == true) {
            costodesayuno = payload.getTotalHuespedes() * payload.getNoches() * 150;


        }

        if (payload.isEstacionamiento() == true) {
            costoEstacionamiento = payload.getNoches() * 100;
        }

        if (payload.getNoches() > 7) {
            costoHospedaje = costoHospedaje * 0.92;
        }

        double subtotal = costoHospedaje + costodesayuno + costoEstacionamiento;
        double impuesto = subtotal * 0.04;


        double total = subtotal + impuesto;
        return total;

    }


}
