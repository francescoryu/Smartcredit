package ch.bzz.smartcredit.service;
import ch.bzz.smartcredit.data.DataHandler;
import ch.bzz.smartcredit.model.KKarte;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("kkarte")
public class KKarteService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCards() {
        List<KKarte> kKarteList = DataHandler.getInstance().readAllKKarten();
        return Response
                .status(200)
                .entity(kKarteList)
                .build();

    }
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