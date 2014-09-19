package fr.edf.distribution.linkyenv.ui.model;

import java.util.List;

public class EnvironmentUI {

	private String envName;
	private List<String> appNames;
	private List<String> versions;
	private List<Boolean> statuses;

	public EnvironmentUI(String envName, List<String> appNames, List<String> versions, List<Boolean> statuses) {
		this.envName = envName;
		this.appNames = appNames;
		this.versions = versions;
		this.statuses = statuses;
	}

	public String getEnvName() {
		return envName;
	}

	public List<String> getAppNames() {
		return appNames;
	}

	public List<String> getVersions() {
		return versions;
	}

	public List<Boolean> getStatuses() {
		return statuses;
	}
}
