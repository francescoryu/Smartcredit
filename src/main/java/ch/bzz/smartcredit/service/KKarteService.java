package ch.bzz.smartcredit.service;

import ch.bzz.smartcredit.data.DataHandler;
import ch.bzz.smartcredit.model.KKarte;

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
 * @date : 08.06.2022
 * @Version : 2.0
 */

/**
 * Auflistung der Karten
 * @return Response
 */

@Path("kkarte")
public class KKarteService {


    /**
     * sortiert KKarte anhand bestimmter Kriterien
     * @param sort
     * @return Response
     */

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKKarte(@QueryParam("sort") String sort) {
        List<KKarte> kKarteList = DataHandler.readAllKKarten();
        List<KKarte> cloned_kKarteList = kKarteList.stream().collect(Collectors.toList());
        if (sort!=null && !sort.isEmpty()) {
            if(sort.equals("kkarteUUID")){
                cloned_kKarteList.sort(Comparator.comparing(KKarte::getKKarteUUID));
            }

            if(sort.equals("kartenNummer")){
                cloned_kKarteList.sort(Comparator.comparing(KKarte::getKartenNummer));
            }

            if(sort.equals("institut")){
                cloned_kKarteList.sort(Comparator.comparing(KKarte::getInstitut));
            }
            return Response
                    .status(200)
                    .entity(cloned_kKarteList)
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity(kKarteList)
                    .build();
        }
    }


    /**
     * Liest die Karte mit der UUID
     * @param kkarteUUID
     * @return Response
     */

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readCard(@QueryParam("uuid") String kkarteUUID) {
        KKarte kkarte = DataHandler.readKKarteByUUID(kkarteUUID);
        if (kkarte == null) {
            return Response
                    .status(404)
                    .build();
        }
        else {
            return Response
                    .status(200)
                    .entity(kkarte)
                    .build();
        }

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
     * @param kkarteUUID
     * @param institut
     * @param kartenNummer
     * @param kundeUUID
     * @return Response
     */

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createKKarte(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("kkarteUUID") String kkarteUUID,

            @Size(min=2, max=40)
            @FormParam("institut") String institut,

            @NotEmpty
            @Pattern(regexp = "([0-9]{4}[[:blank:]]){3}[0-9]{4}")
            @FormParam("kartenNummer") String kartenNummer,

            @FormParam("kundeUUID") String kundeUUID
    ) {
        KKarte kKarte = new KKarte();
        kKarte.setKKarteUUID(UUID.randomUUID().toString());
        kKarte.setInstitut(institut);
        kKarte.setKartenNummer(kartenNummer);
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
            @Pattern(regexp = "([0-9]{4}[[:blank:]]){3}[0-9]{4}")
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
