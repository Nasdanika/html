package org.nasdanika.html.app.impl;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.common.ProgressEntry;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.viewparts.JsTreeNavigationPanelViewPart;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeFactory;

/**
 * Generates HTML progress report from {@link ProgressEntry}
 * @author Pavel
 *
 */
public class ProgressReportGenerator {

	private ActionImpl principalAction;
	private ActionImpl rootAction;

	public ProgressReportGenerator(String title, ProgressEntry... progressEntries) {
		this(title, Arrays.asList(progressEntries));
	}	
	
	/**
	 * Generates progress report
	 * @param title
	 * @param description
	 */
	public ProgressReportGenerator(String title, List<ProgressEntry> progressEntries) {
		rootAction = new ActionImpl();
		rootAction.setText(title);
		
		principalAction = new ActionImpl();
		principalAction.setParent(rootAction);
		rootAction.getChildren().add(principalAction);

		boolean isFirst = true;
		for (ProgressEntry pe: progressEntries) {
			ProgressEntryViewAction peva = new ProgressEntryViewAction(pe);
			principalAction.getChildren().add(peva);
			peva.setParent(principalAction);
			if (isFirst) {
				peva.setId("index");
				isFirst = false;
			}
		}
	}
	
	public void generate(org.nasdanika.common.resources.Container<Object> resourceConsumer, ProgressMonitor progressMonitor) {
		for (Action action: principalAction.getChildren()) {
			generate(action, resourceConsumer, progressMonitor);
		}
	}

	protected void generate(Action action, org.nasdanika.common.resources.Container<Object> resourceConsumer, ProgressMonitor progressMonitor) {		
		try (ProgressMonitor im = progressMonitor.split("Generating report for "+action.getText(), 100)) {
			Application app = new BootstrapContainerRouterApplication(Theme.Default, true);

			JsTreeFactory.INSTANCE.cdn(app.getHTMLPage());
			FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());
			
			ApplicationBuilder  applicationBuilder = new ActionApplicationBuilder(action) {
				
				@Override
				protected ViewPart getNavigationPanelViewPart() {
					return new JsTreeNavigationPanelViewPart(getNavigationPanelActions(), getActiveAction(), true) {
						@Override
						protected void configureJsTree(JSONObject jsTree) {
							JSONArray plugins = new JSONArray();
							plugins.put("state");
							jsTree.put("plugins", plugins);
						}
					};
				}
				
			};
			
			applicationBuilder.build(app, progressMonitor);
			
			resourceConsumer.put(action.getId()+".html", app, im);			
		}
		for (Action child: action.getChildren()) {
			generate(child, resourceConsumer, progressMonitor);
		}		
	}
			
}

