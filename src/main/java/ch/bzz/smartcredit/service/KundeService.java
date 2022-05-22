package ch.bzz.smartcredit.service;

import ch.bzz.smartcredit.data.DataHandler;
import ch.bzz.smartcredit.model.Kunde;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("kunde")
public class KundeService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKunden() {
        List<Kunde> kundeList = DataHandler.getInstance().readAllKunde();
        return Response
                .status(200)
                .entity(kundeList)
                .build();

    }
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readKunde(
            @QueryParam("uuid") String kundeUUID
    ) {
        Kunde kunde = DataHandler.getInstance().readKundeByUUID(kundeUUID);
        return Response
                .status(200)
                .entity(kunde)
                .build();
    }

}
