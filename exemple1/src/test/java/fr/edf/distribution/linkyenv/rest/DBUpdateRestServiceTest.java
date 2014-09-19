package fr.edf.distribution.linkyenv.rest;

import javax.ws.rs.core.Response;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.edf.distribution.linkyenv.service.IEnvironmentsService;

public class DBUpdateRestServiceTest {

	@Mock
	private IEnvironmentsService environmentsService;

	private DBUpdateRestService dbUpdateRestService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		dbUpdateRestService = new DBUpdateRestService(environmentsService);
	}

	@Test
	public void update_invoke_environmentsService_to_update_db() throws Exception {
		//Given
		final String envName = "envName";
		final String appName = "appName";
		final String version = "version";
		final Integer status = 0;

		//When
		Response response = dbUpdateRestService.update(envName,appName,version,status);

		//Then
		Mockito.verify(environmentsService).updateVersionFor(envName,appName,version);
		Mockito.verify(environmentsService).updateStatusFor(envName,appName,status);
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
	}
}