package fr.edf.distribution.linkyenv.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.edf.distribution.linkyenv.service.IEnvironmentsService;

@Service
@Path("/")
public class DBUpdateRestService {

	private IEnvironmentsService environmentsService;

	@Autowired
	public DBUpdateRestService(IEnvironmentsService environmentsService) {

		this.environmentsService = environmentsService;
	}

	@POST
	@Path("/update/{envName}/{appName}/{version}/{status}")
	public Response update(@PathParam("envName") String envName,
			                          @PathParam("appName") String appName,
			                          @PathParam("version") String version,
			                          @PathParam("status") Integer status) {
		environmentsService.updateVersionFor(envName,appName,version);
		environmentsService.updateStatusFor(envName,appName,status);

		return Response.ok().build();
	}
}
