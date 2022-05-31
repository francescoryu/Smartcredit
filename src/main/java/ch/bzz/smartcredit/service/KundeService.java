package ch.bzz.smartcredit.service;

import ch.bzz.smartcredit.data.DataHandler;
import ch.bzz.smartcredit.model.KKarte;
import ch.bzz.smartcredit.model.Kunde;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @autor : Francesco Ryu
 * @date : 23.05.2022
 * @Version : 3.0
 */

/**
 * Auflistung der Kunden
 * @return Response
 */

@Path("kunde")
public class KundeService {

    /**
     * Sort Methode für bestimmte Kriterien
     * @param sort
     * @return Response
     */

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKunde(@QueryParam("sort") String sort) {
        List<Kunde> kundeList = DataHandler.readAllKunde();
        List<Kunde> cloned_kundeList = kundeList.stream().collect(Collectors.toList());
        if (sort!=null && !sort.isEmpty()) {
            if(sort.equals("kundeUUID")){
                cloned_kundeList.sort(Comparator.comparing(Kunde::getKundeUUID));
            }

            if(sort.equals("vorName")){
                cloned_kundeList.sort(Comparator.comparing(Kunde::getVorName));
            }

            if(sort.equals("nachName")){
                cloned_kundeList.sort(Comparator.comparing(Kunde::getNachName));
            }

            if(sort.equals("alter")){
                cloned_kundeList.sort(Comparator.comparing(Kunde::getAlter));
            }
            return Response
                    .status(200)
                    .entity(cloned_kundeList)
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity(kundeList)
                    .build();
        }
    }

    /**
     * Liest den Kunden mit der UUID
     * @param kundeUUID
     * @return Response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readKunde(@QueryParam("uuid") String kundeUUID) {
        Kunde kunde = DataHandler.readKundeByUUID(kundeUUID);
        if (kunde == null) {
            return Response
                    .status(404)
                    .build();
        }
        else {
            return Response
                    .status(200)
                    .entity(kunde)
                    .build();
        }
    }
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteKunde(
            @QueryParam("uuid") String kundeUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteKunde(kundeUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createKunde(
            @FormParam("kundeUUID") String kundeUUID,
            @FormParam("vorName") String vorName,
            @FormParam("nachName") String nachName,
            @FormParam("alter") Integer alter
    ) {
        Kunde kunde = new Kunde();
        kunde.setKundeUUID(UUID.randomUUID().toString());
        kunde.setVorName(vorName);
        kunde.setNachName(nachName);
        kunde.setAlter(alter);

        DataHandler.insertKunde(kunde);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateKKarte(
            @FormParam("kundeUUID") String kundeUUID,
            @FormParam("vorName") String vorName,
            @FormParam("nachName") String nachName,
            @FormParam("alter") Integer alter
    ) {
        int httpStatus = 200;
        Kunde kunde = DataHandler.readKundeByUUID(kundeUUID);
        if (kunde != null) {
            kunde.setKundeUUID(UUID.randomUUID().toString());
            kunde.setVorName(vorName);
            kunde.setNachName(nachName);
            kunde.setAlter(alter);

            DataHandler.insertKunde(kunde);
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
