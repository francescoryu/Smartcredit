package ch.bzz.smartcredit.service;

import ch.bzz.smartcredit.data.DataHandler;
import ch.bzz.smartcredit.model.KKarte;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
 * Auflistung der Karten
 * @return Response
 */

@Path("kkarte")
public class KKarteService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKKarte(
            @CookieParam("userRole") String userRole
    ) {
        List<KKarte> kKarteList = DataHandler.readAllKKarten();
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
        }
        return Response
                .status(httpStatus)
                .entity(kKarteList)
                .build();
    }


    /**
     * Liest die Karte mit der UUID
     * @param kkarteUUID
     * @return Response
     */

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readKKarte(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String kkarteUUID
    ) {
        int httpStatus = 200;
        KKarte kKarte = DataHandler.readKKarteByUUID(kkarteUUID);
        if (kKarte == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(kKarte)
                .build();
    }

    /**
     * Löscht die KKarte anhand der UUID
     * @param kkarteUUID
     * @return Response
     */

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteKKarte(
            @QueryParam("uuid") String kkarteUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteKKarte(kkarteUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * erstellt neue KKarte
     * @return Response
     */

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertKKarte(
            @Valid @BeanParam KKarte kKarte,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("kundeUUID") String kundeUUID
    ) {
        kKarte.setKKarteUUID(UUID.randomUUID().toString());
        kKarte.setKundeUUID(kundeUUID);

        DataHandler.insertKKarte(kKarte);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * Updatet die KKarte anhand der UUID
     * @param kkarteUUID
     * @param institut
     * @param kartenNummer
     * @param kundeUUID
     * @return Response
     */

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateKKarte(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("kkarteUUID") String kkarteUUID,

            @NotEmpty
            @Size(min=2, max=40)
            @FormParam("institut") String institut,

            @NotEmpty
            @Pattern(regexp = "([0-9]{4} ){3}[0-9]{4}")
            @FormParam("kartenNummer") String kartenNummer,

            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("kundeUUID") String kundeUUID
    ) {
        int httpStatus = 200;
        KKarte kKarte = DataHandler.readKKarteByUUID(kkarteUUID);
        if (kKarte != null) {
            kKarte.setInstitut(institut);
            kKarte.setKartenNummer(kartenNummer);
            kKarte.setKundeUUID(kundeUUID);

            DataHandler.updateKKarte();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
