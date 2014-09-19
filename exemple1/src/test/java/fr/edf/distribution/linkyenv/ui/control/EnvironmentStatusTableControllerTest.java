package fr.edf.distribution.linkyenv.ui.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;

import fr.edf.distribution.linkyenv.service.IEnvironmentsService;
import fr.edf.distribution.linkyenv.ui.model.EnvironmentUI;

public class EnvironmentStatusTableControllerTest {

	@Mock
	private Grid envGrid;
	@Mock
	private IEnvironmentsService environmentsService;

	private EnvironmentStatusTableController environmentStatusTableController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		environmentStatusTableController = Mockito.spy(new EnvironmentStatusTableController());
		environmentStatusTableController.setEnvironmentStatusTableControllerForTU(environmentsService, envGrid);
	}

	@Test
	public void getApplications_return_application_name_by_invoke_environmentsService() {
		//Given
		List<String> expectedApplications = new ArrayList<String>();
		expectedApplications.add("app");
		Mockito.when(environmentsService.getApplicationsNames()).thenReturn(expectedApplications);

		//When
		List<String> actualApplications = environmentStatusTableController.getApplications();

		//Then
		Assertions.assertThat(actualApplications).isEqualTo(expectedApplications);
	}

	@Test
	public void getStatus_return_applications_size_by_invoke_environmentsService() {
		//Given
		List<String> expectedApplications = new ArrayList<String>();
		expectedApplications.add("app");
		Mockito.when(environmentsService.getApplicationsNames()).thenReturn(expectedApplications);

		//When
		String[] status = environmentStatusTableController.getStatus();

		//Then
		Assertions.assertThat(status.length).isEqualTo(1);
	}

	@Test
	public void getInitEnvironments_return_environmentUIs_by_invoke_environmentsService_from_bdd() throws Exception {
		//Given
		EnvironmentUI envUI1 = Mockito.mock(EnvironmentUI.class);
		Mockito.when(environmentsService.getEnvironmentNames()).thenReturn(Arrays.asList("name"));
		Mockito.when(environmentsService.getEnvironmentsUIModelFor(Mockito.anyString())).thenReturn(envUI1);

		//When
		ListModel<EnvironmentUI> actualLModel = environmentStatusTableController.getInitEnvironments();

		//Then
		Assertions.assertThat(actualLModel.getSize()).isEqualTo(1);
	}

	@Test
	public void updateStatusOnClickButton_update_status_according_to_button_color() throws Exception {
		//Given
		final Button button = new Button();
		final String envName = "envName";
		final String appName = "appName";

		//When
		button.setStyle("green;");
		environmentStatusTableController.updateStatusOnClickButton(button, envName, appName);
		//Then
		Mockito.verify(environmentsService).updateStatusFor(envName, appName, 0);

		//When
		button.setStyle("red;");
		environmentStatusTableController.updateStatusOnClickButton(button, envName, appName);
		//Then
		Mockito.verify(environmentsService).updateStatusFor(envName, appName, 1);
	}
}