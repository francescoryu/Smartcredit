package ch.bzz.smartcredit.service;

import ch.bzz.smartcredit.data.DataHandler;
import ch.bzz.smartcredit.model.KKarte;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor : Francesco Ryu
 * @date : 23.05.2022
 * @Version : 2.0
 */

/**
 * Auflistung der Karten
 * @return Response
 */

@Path("kkarte")
public class KKarteService {


    /**
     * Sort Methode für bestimmte Kriterien
     * @param sort
     * @return Response
     */

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKKarte(@QueryParam("sort") String sort) {
        List<KKarte> kKarteList = DataHandler.getInstance().readAllKKarten();
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
    public Response readCard(
            @QueryParam("uuid") String kkarteUUID
    ) {
        KKarte kkarte = DataHandler.getInstance().readKKarteByUUID(kkarteUUID);
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

}
