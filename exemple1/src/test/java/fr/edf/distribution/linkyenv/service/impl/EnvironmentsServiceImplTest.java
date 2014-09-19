package fr.edf.distribution.linkyenv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.edf.distribution.linkyenv.dao.EnvironmentsDao;
import fr.edf.distribution.linkyenv.model.Environment;
import fr.edf.distribution.linkyenv.ui.model.EnvironmentUI;

public class EnvironmentsServiceImplTest {

	@Mock
	private EnvironmentsDao environmentsDao;

	private EnvironmentsServiceImpl serviceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		serviceImpl = new EnvironmentsServiceImpl(environmentsDao);
	}

	@Test
	public void getEnvironmentNames_invoke_environmentDao_return_environment_names() throws Exception {
		//Given
		List<String> expectedEnvironmentsNames = new ArrayList<String>();
		expectedEnvironmentsNames.add("env1");
		expectedEnvironmentsNames.add("env2");
		expectedEnvironmentsNames.add("env3");
		Mockito.when(environmentsDao.getEnvironmentNames()).thenReturn(expectedEnvironmentsNames);

		//When
		List<String> actualEnvironmentsNames = serviceImpl.getEnvironmentNames();

		//Then
		Assertions.assertThat(actualEnvironmentsNames).isEqualTo(expectedEnvironmentsNames);
	}

	@Test
	public void getApplicationsNames_invoke_environmentDao_return_application_names() throws Exception {
		//Given
		List<String> expectedApplicationsNames = new ArrayList<String>();
		expectedApplicationsNames.add("app1");
		expectedApplicationsNames.add("app2");
		expectedApplicationsNames.add("app3");
		Mockito.when(environmentsDao.getApplicationsNames()).thenReturn(expectedApplicationsNames);

		//When
		List<String> actualApplicationsNames = serviceImpl.getApplicationsNames();

		//Then
		Assertions.assertThat(actualApplicationsNames).isEqualTo(expectedApplicationsNames);
	}

	@Test
	public void getEnvironmentsUIModelFor_return_environment_ui_model_by_name() {
		//Given
		final String envName = "env1";
		List<Environment> expectedEnvironments = new ArrayList<Environment>();
		Environment env1 = new Environment();
		env1.setEnvironment(envName);
		env1.setApplication("app1");
		env1.setVersion("v1");
		env1.setStatus(0);

		Environment env2 = new Environment();
		env2.setEnvironment(envName);
		env2.setApplication("app2");
		env2.setVersion("v2");
		env2.setStatus(1);

		expectedEnvironments.add(env1);
		expectedEnvironments.add(env2);

		Mockito.when(environmentsDao.getEnvironmentByName(envName)).thenReturn(expectedEnvironments);

		//When
		EnvironmentUI actualEnvironmentUI = serviceImpl.getEnvironmentsUIModelFor(envName);

		//Then
		Assertions.assertThat(actualEnvironmentUI.getEnvName()).isEqualTo(envName);
		Assertions.assertThat(actualEnvironmentUI.getAppNames().get(0)).isEqualTo(
				expectedEnvironments.get(0).getApplication());
		Assertions.assertThat(actualEnvironmentUI.getAppNames().get(1)).isEqualTo(
				expectedEnvironments.get(1).getApplication());
		Assertions.assertThat(actualEnvironmentUI.getVersions().get(0)).isEqualTo(
				expectedEnvironments.get(0).getVersion());
		Assertions.assertThat(actualEnvironmentUI.getVersions().get(1)).isEqualTo(
				expectedEnvironments.get(1).getVersion());
		Assertions.assertThat(actualEnvironmentUI.getStatuses().get(0)).isEqualTo(false);
		Assertions.assertThat(actualEnvironmentUI.getStatuses().get(1)).isEqualTo(true);
	}

	@Test
	public void updateStatusFor_invoke_dao_to_update_status() throws Exception {
		//Given
		final String envName = "envName";
		final String appName = "appName";
		final Integer status = 0;

		//When
		serviceImpl.updateStatusFor(envName, appName, status);

		//Then
		Mockito.verify(environmentsDao).updateStatusFor(envName, appName, status);
	}

	@Test
	public void updateVersionFor_invoke_dao_to_update_version() throws Exception {
		//Given
		final String envName = "envName";
		final String appName = "appName";
		final String version = "version";

		//When
		serviceImpl.updateVersionFor(envName, appName, version);

		//Then
		Mockito.verify(environmentsDao).updateVersionFor(envName, appName, version);
	}

}