package ch.bzz.smartcredit.service;

import ch.bzz.smartcredit.data.DataHandler;
import ch.bzz.smartcredit.model.Kunde;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @autor : Francesco Ryu
 * @date : 14.06.2022
 * @Version : 4.0
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
    public Response listKunde(
            @QueryParam("sort") String sort,
            @CookieParam("userRole") String userRole
    ) {
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
                    .status(200)
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
        @NotEmpty
        @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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

    /**
     * Löscht ein Kunde anhand der UUID
     * @param kundeUUID
     * @return Response
     */

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteKunde(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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

    /**
     * erstellt neuer Kunde
     * @param kunde
     * @return Response
     */

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertKunde(
            @Valid @BeanParam Kunde kunde
    ) {
        kunde.setKundeUUID(UUID.randomUUID().toString());


        DataHandler.insertKunde(kunde);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * Updatet ein Kunde anhand der UUID
     * @return
     */

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateKunde(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("kundeUUID") String kundeUUID,

            @NotEmpty
            @Size(min = 2, max = 40)
            @FormParam("vorName") String vorName,

            @NotEmpty
            @Size(min = 2, max = 50)
            @FormParam("nachName") String nachName,

            @NotEmpty
            @Min(value = 7)
            @Max(value = 999)
            @FormParam("alter") Integer alter

    ) {
        int httpStatus = 200;
        Kunde kunde = DataHandler.readKundeByUUID(kundeUUID);
        if (kunde != null) {
            kunde.setVorName(kunde.getVorName());
            kunde.setNachName(kunde.getNachName());
            kunde.setAlter(kunde.getAlter());

            DataHandler.updateKunde();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
