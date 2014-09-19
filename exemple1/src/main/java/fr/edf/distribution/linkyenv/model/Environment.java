package fr.edf.distribution.linkyenv.model;

import java.io.Serializable;

public class Environment implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Identifiant tech
	 */
	private Integer id;
	/**
	 * nom d'environment
	 */
	private String environment;
	/**
	 * nom application
	 */
	private String application;
	/**
	 * version du déployement
	 */
	private String version;
	/**
	 * le status d'environment 0:dispo 1:indispo
	 */
	private Integer status;

	public Environment(){

	}
	public void setId(Integer id) {
		this.id = id;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public String getEnvironment() {
		return environment;
	}

	public String getApplication() {
		return application;
	}

	public String getVersion() {
		return version;
	}

	public Integer getStatus() {
		return status;
	}
}
