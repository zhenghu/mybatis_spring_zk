package fr.edf.distribution.linkyenv.dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.edf.distribution.linkyenv.model.Environment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/daoTestContext.xml"})
public class EnvironmentsDaoTest {

	@Autowired
	private EnvironmentsDao environmentsDao;

	@Test
	public void getEnvironmentNames_return_unique_environment_names() throws Exception {
		//Given
		//When
		List<String> actualEnvironments = environmentsDao.getEnvironmentNames();
		//Then
		Assertions.assertThat(actualEnvironments.size()).isEqualTo(9);
	}

	@Test
	public void getApplicationsNames_return_unique_application_names() throws Exception {
		//Given
		//When
		List<String> actualApplications = environmentsDao.getApplicationsNames();
		//Then
		Assertions.assertThat(actualApplications.size()).isEqualTo(5);
		Assertions.assertThat(actualApplications.get(0)).isEqualTo("Deploy C");
		Assertions.assertThat(actualApplications.get(1)).isEqualTo("Deploy K");
		Assertions.assertThat(actualApplications.get(2)).isEqualTo("PDA");
		Assertions.assertThat(actualApplications.get(3)).isEqualTo("Pilot C");
		Assertions.assertThat(actualApplications.get(4)).isEqualTo("Pilot K");
	}

	@Test
	public void getEnvironmentByName_return_environments_for_specific_name() throws Exception {
		//Given
		//When
		List<Environment> actualEnvironments = environmentsDao.getEnvironmentByName("DEVMOE");
		//Then
		Assertions.assertThat(actualEnvironments.size()).isEqualTo(5);
		Assertions.assertThat(actualEnvironments.get(0).getApplication()).isEqualTo("Deploy C");
		Assertions.assertThat(actualEnvironments.get(1).getApplication()).isEqualTo("Deploy K");
		Assertions.assertThat(actualEnvironments.get(2).getApplication()).isEqualTo("PDA");
		Assertions.assertThat(actualEnvironments.get(3).getApplication()).isEqualTo("Pilot C");
		Assertions.assertThat(actualEnvironments.get(4).getApplication()).isEqualTo("Pilot K");
	}

	@Test
	public void updateStatusFor_update_status_for_environment_and_application() throws Exception {
		//Give
		List<Environment> oldlEnvironments = environmentsDao.getEnvironmentByName("DEVMOE");
		Assertions.assertThat(oldlEnvironments.get(2).getStatus()).isEqualTo(0);
		//When
		environmentsDao.updateStatusFor("DEVMOE", "PDA", 1);
		List<Environment> newEnvironments = environmentsDao.getEnvironmentByName("DEVMOE");
		//Then
		Assertions.assertThat(newEnvironments.get(2).getStatus()).isEqualTo(1);
	}

	@Test
	public void updateVersionFor_update_version_for_environment_and_application() throws Exception {
		//Give
		final String newVersion = "V1.1.0";
		List<Environment> oldlEnvironments = environmentsDao.getEnvironmentByName("DEVMOE");
		Assertions.assertThat(oldlEnvironments.get(2).getVersion()).isEqualTo("V1.0.0");
		//When
		environmentsDao.updateVersionFor("DEVMOE", "PDA", newVersion);
		List<Environment> newEnvironments = environmentsDao.getEnvironmentByName("DEVMOE");
		//Then
		Assertions.assertThat(newEnvironments.get(2).getVersion()).isEqualTo(newVersion);
	}
}