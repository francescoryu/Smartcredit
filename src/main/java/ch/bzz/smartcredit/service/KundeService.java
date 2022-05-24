package ch.bzz.smartcredit.service;

import ch.bzz.smartcredit.data.DataHandler;
import ch.bzz.smartcredit.model.KKarte;
import ch.bzz.smartcredit.model.Kunde;

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
 * @Version : 3.0
 */

/**
 * Auflistung der Kunden
 * @return Response
 */

@Path("kunde")
public class KundeService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKunden() {
        List<Kunde> kundeList = DataHandler.getInstance().readAllKunde();
        if (kundeList == null) {
            return Response
                    .status(404)
                    .build();
        }
        else {
            return Response
                    .status(200)
                    .entity(kundeList)
                    .build();
        }

    }

    /**
     * Sort Methode für bestimmte Kriterien
     * @param sort
     * @return Response
     */

    @GET
    @Path("listSorted")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKunde(@QueryParam("sort") String sort) {
        List<Kunde> kundeList = DataHandler.getInstance().readAllKunde();
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
    public Response readKunde(
            @QueryParam("uuid") String kundeUUID
    ) {
        Kunde kunde = DataHandler.getInstance().readKundeByUUID(kundeUUID);
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

}
