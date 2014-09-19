package fr.edf.distribution.linkyenv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.edf.distribution.linkyenv.dao.EnvironmentsDao;
import fr.edf.distribution.linkyenv.model.Environment;
import fr.edf.distribution.linkyenv.service.IEnvironmentsService;
import fr.edf.distribution.linkyenv.ui.model.EnvironmentUI;

@Service("environmentsService")
public class EnvironmentsServiceImpl implements IEnvironmentsService {

	private EnvironmentsDao environmentsDao;

	@Autowired
	public EnvironmentsServiceImpl(EnvironmentsDao environmentsDao) {
		this.environmentsDao = environmentsDao;
	}

	@Override
	public List<String> getEnvironmentNames() {
		return environmentsDao.getEnvironmentNames();
	}

	@Override
	public List<String> getApplicationsNames() {
		return environmentsDao.getApplicationsNames();
	}

	@Override
	public EnvironmentUI getEnvironmentsUIModelFor(String environmentName) {
		List<String> applications = new ArrayList<String>();
		List<String> versions = new ArrayList<String>();
		List<Boolean> status = new ArrayList<Boolean>();
		for (Environment environment : environmentsDao.getEnvironmentByName(environmentName)) {
			applications.add(environment.getApplication());
			versions.add(environment.getVersion());
			status.add(environment.getStatus() != 0);
		}
		return new EnvironmentUI(environmentName, applications, versions, status);
	}

	@Override
	public void updateStatusFor(String envName, String appName, Integer status) {
		environmentsDao.updateStatusFor(envName, appName, status);
	}

	@Override
	public void updateVersionFor(String envName, String appName, String version) {
		environmentsDao.updateVersionFor(envName, appName, version);
	}

}
