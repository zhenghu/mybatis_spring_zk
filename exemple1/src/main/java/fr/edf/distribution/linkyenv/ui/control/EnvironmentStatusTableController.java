package fr.edf.distribution.linkyenv.ui.control;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

import fr.edf.distribution.linkyenv.service.IEnvironmentsService;
import fr.edf.distribution.linkyenv.ui.model.EnvironmentUI;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
//annotation temporary must be plugin with socle
public class EnvironmentStatusTableController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Grid envGrid;

	@WireVariable("environmentsService")
	private IEnvironmentsService environmentsService;

	public ListModel<EnvironmentUI> getInitEnvironments() {
		return new ListModelList<EnvironmentUI>(loadEnvironmentUIModel(), true);
	}

	public List<String> getApplications() {
		return environmentsService.getApplicationsNames();
	}

	public String[] getStatus() {
		return new String[environmentsService.getApplicationsNames().size()];
	}

	@Listen(Events.ON_CLICK + " = button")
	public void buttonEvent(Event event) {
		Button btn = (Button) event.getTarget();
		String envName = getEnvNameForComponent(btn);
		String appName = btn.getLabel();

		updateStatusOnClickButton(btn, envName, appName);

	}


	@Listen(Events.ON_CHANGE + " = textbox")
	public void textboxEvent(Event event) {
		Textbox tBox = (Textbox) event.getTarget();
		String envName = getEnvNameForComponent(tBox);
		String appName = tBox.getAutag();

		environmentsService.updateVersionFor(envName, appName, tBox.getText());
	}

	//setter for TU
	public void setEnvironmentStatusTableControllerForTU(IEnvironmentsService environmentsService, Grid envGrid) {
		this.environmentsService = environmentsService;
		this.envGrid = envGrid;
	}

	protected String getEnvNameForComponent(Component component) {
		Integer rowIndex = envGrid.getRows().getChildren().indexOf(component.getParent().getParent());
		return environmentsService.getEnvironmentNames().get(rowIndex);
	}

	private List<EnvironmentUI> loadEnvironmentUIModel() {
		List<EnvironmentUI> environmentUIs = new ArrayList<EnvironmentUI>();
		for (String envName : environmentsService.getEnvironmentNames()) {
			environmentUIs.add(environmentsService.getEnvironmentsUIModelFor(envName));
		}
		return environmentUIs;
	}

	protected void updateStatusOnClickButton(Button btn, String envName, String appName) {
		if (btn.getStyle().contains("green;")) {
			btn.setStyle("background-color:red;");
			environmentsService.updateStatusFor(envName, appName, 0);
			return;
		}
		if (btn.getStyle().contains("red;")) {
			btn.setStyle("background-color:green;");
			environmentsService.updateStatusFor(envName, appName, 1);
		}
	}
}
