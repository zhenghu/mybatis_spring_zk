package fr.edf.distribution.linkyenv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fr.edf.distribution.linkyenv.model.Environment;

public interface EnvironmentsDao {

	/**
	 * Retourne les noms d'environments
	 */
	List<String> getEnvironmentNames();

	/**
	 * Retourne les noms d'applications
	 */
	List<String> getApplicationsNames();

	/**
	 * Retourne list environment par le nom d'environnement
	 *
	 * @param environmentName
	 * 		nom d'environnement
	 */
	List<Environment> getEnvironmentByName(String environmentName);

	/**
	 * Mettre a jour status pour environment et application
	 *
	 * @param environmentName
	 * 		nom d'environnement
	 * @param applicationName
	 * 		nom d'application
	 * @param newStatus
	 * 		nouvelle statut
	 */
	void updateStatusFor(@Param("envName") String environmentName, @Param("appName") String applicationName, @Param(
			"newStatus") Integer newStatus);

	/**
	 * Mettre a jour version pour environment et application
	 * @param environmentName nom d'environnement
	 * @param applicationName nom d'application
	 * @param newVersion nouvelle version
	 */
	void updateVersionFor(@Param("envName") String environmentName, @Param("appName") String applicationName, @Param(
				"newVersion") String newVersion);
}
