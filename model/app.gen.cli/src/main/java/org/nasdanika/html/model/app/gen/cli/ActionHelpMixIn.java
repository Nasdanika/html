package org.nasdanika.html.model.app.gen.cli;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.nasdanika.cli.Description;
import org.nasdanika.cli.HelpCommand;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.Util;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;

import picocli.CommandLine;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;

public class ActionHelpMixIn implements HelpCommand.OutputFormatMixIn {
	
	private CommandLine rootCommand;

	public ActionHelpMixIn(CommandLine rootCommand) {
		this.rootCommand = rootCommand;
	}
		
	@Option(names = {"-a", "--action-model"}, description = "Output to action model")
	private boolean actionModel;

	@Override
	public boolean match() {
		return actionModel;
	}

	@Override
	public void write(OutputStream out) throws IOException {
		Action rootAction = createCommandLineAction(rootCommand);
		ResourceSet actionModelsResourceSet = new ResourceSetImpl();
		actionModelsResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		URI actionModelResourceURI = URI.createFileURI(File.createTempFile("command-action-model-", ".xml").getAbsolutePath());		
		Resource actionModelResource = actionModelsResourceSet.createResource(actionModelResourceURI);
		actionModelResource.getContents().add(rootAction);
		actionModelResource.save(out, null);
	}
	
	public static Action createCommandLineAction(CommandLine commandLine) throws IOException {		
		CommandSpec commandSpec = commandLine.getCommandSpec();
		Action action = AppFactory.eINSTANCE.createAction();
		action.setText(commandSpec.name());
		action.setLocation(commandSpec.name() + "/index.html");
		
		String[] version = commandSpec.version(); // TODO: Nav, float right
		StringBuilder builder = new StringBuilder();
		if (version.length > 0) {
			builder.append("<table><tr><th valign=\"top\">Version:</th><td> ").append(System.lineSeparator());			
			for (String vs: version) {
				builder.append(vs).append(System.lineSeparator());
				builder.append("<br/>").append(System.lineSeparator());
			}
			builder.append("</td></tr></table>").append(System.lineSeparator());
		}

		builder.append(HelpCommand.usageToHTML(commandLine));

		// Description 
		Object userObject = commandSpec.userObject();
		if (userObject != null) {
			Class<? extends Object> clazz = userObject.getClass();
			Description description = clazz.getAnnotation(Description.class);
			if (description != null) {
				String icon = description.icon();
				if (!Util.isBlank(icon)) {
					action.setIcon(icon);
				}
				String tooltip = description.tooltip();
				if (!Util.isBlank(tooltip)) {
					action.setTooltip(tooltip);
				}
				String markdown = description.value();
				if (Util.isBlank(markdown)) {
					String dResource = description.resource();
					if (Util.isBlank(dResource)) {
						dResource = clazz.getName().substring(clazz.getName().lastIndexOf('.') + 1) + ".md";						
					}
					InputStream dStream = clazz.getResourceAsStream(dResource);
					if (dStream != null) {
						markdown = DefaultConverter.INSTANCE.toString(dStream);
					}
				}
				
				builder
					.append("<div class=\"markdown-body\">")
					.append(System.lineSeparator())
					.append(MarkdownHelper.INSTANCE.markdownToHtml(markdown))
					.append(System.lineSeparator())
					.append("</div>")
					.append(System.lineSeparator());
				
			}
		}
		
		addContent(action, builder.toString());
		
		EList<EObject> children = action.getChildren();
		for (CommandLine subCommand: commandLine.getSubcommands().values().stream().sorted((a,b) -> a.getCommandName().compareTo(b.getCommandName())).collect(Collectors.toList())) {
			children.add(createCommandLineAction(subCommand));
		}

		return action;
	}
	
	/**
	 * Adds textual content.
	 * @param content
	 */
	protected static void addContent(Action action, String content) {
		if (!Util.isBlank(content)) {
			Text text = createText(content);
			action.getContent().add(text);
		}
	}

	/**
	 * Convenience method to create Text and set content in one shot.
	 * @param content
	 * @return
	 */
	public static Text createText(String content) {
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(content);
		return text;
	}

}
