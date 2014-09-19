package fr.edf.distribution.linkyenv.service;

import java.util.List;

import fr.edf.distribution.linkyenv.ui.model.EnvironmentUI;

public interface IEnvironmentsService {

	List<String> getEnvironmentNames();

	List<String> getApplicationsNames();

	EnvironmentUI getEnvironmentsUIModelFor(String environnmentName);

	void updateStatusFor(String envName, String appName, Integer status);

	void updateVersionFor(String envName, String appName, String version);
}
