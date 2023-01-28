package org.nasdanika.html.model.app.gen.maven;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CancellationException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.DiagramGeneratorImpl;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.html.model.app.gen.ActionSiteGenerator;

/**
 * Generates action site.
 */
@Mojo(name = "generate-action-site", defaultPhase = LifecyclePhase.SITE)
public class ActionSiteGeneratorMojo extends AbstractMojo {

	@Parameter(defaultValue = "target/action-site")	
	private File outputDirectory;

	@Parameter(defaultValue = "target/action-site-work-dir")	
	private File workDirectory;
	
	@Parameter()	
	private boolean cleanWorkDir;	
	
	@Parameter(required = true)	
	private String action;
	
	@Parameter(required = true)	
	private String pageTemplate;
	
	@Parameter()	
	private String siteMapDomain;
	
	@Parameter()	
	private String drawioViewer;	
	
	@Parameter(required = false)	
	private int errors;
	
	@Parameter()	
	private File progressOutput;	
	
    @Parameter()
    private List<DiagramGenerator> diagramGenerators;
	
	
//	@Parameter(name = "json-progress")	
//	private boolean jsonProgress;	
	
	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	MavenProject project;	
	
	/**
	 * Progress monitor reporting to a {@link PrintStream}, e.g. ``System.out``.
	 * This monitor indents sub-tasks and worked messages. It can be thought of as a hierarchical logger.
	 * @author Pavel
	 *
	 */
	public class PrintStreamProgressMonitor implements ProgressMonitor {

		private PrintStream out;
		private boolean closeStream;
		private String indent;
		protected int indentIncrement = 2;
		private boolean cancelled;
		
		/**
		 * Constructs a progress monitor for a given print stream.
		 * @param out
		 * @param indent Indent in spaces for this monitor.
		 * @param indentIncrement Increment to add to the indent of this monitor when constructing a sub-monitor.
		 * @param closeStream if true the monitor closes the stream in its close() method.
		 */
		public PrintStreamProgressMonitor(PrintStream out, int indent, int indentIncrement, boolean closeStream) {
			this.out = out;
			StringBuilder indentBuilder = new StringBuilder();
			for (int i = 0; i < indent; ++i) {
				indentBuilder.append(' ');
			}
			this.indent = indentBuilder.toString();
			this.closeStream = closeStream;
		}
		
		/**
		 * Constructs a progres monitor outputting to System.out with indent 0, indentIncrement 2 and not closing the stream.
		 */
		public PrintStreamProgressMonitor() {
			this(System.out, 0, 2, false);
		}
		
		@Override
		public void close() {
			if (closeStream) {
				out.close();
			}
		}

		@Override
		public boolean isCancelled() {
			return cancelled;
		}

		@Override
		public ProgressMonitor split(String taskName, double size, Object... data) {
			if (isCancelled()) {
				throw new CancellationException();
			}
			out.println(indent+"  "+taskName+" ("+size+")");
			if (data != null) {
				for (Object d: data) {
					out.println(formatDetail(d, indent + "    "));			
				}
			}
			return new PrintStreamProgressMonitor(out, indent.length()+indentIncrement, indentIncrement, false) {
				
				@Override
				public boolean isCancelled() {
					return PrintStreamProgressMonitor.this.isCancelled();
				}
				
			};
		}
		
		/**
		 * Formats detail element for output. This implementation concatenates the indent with the detail.
		 * @param detail
		 * @param indent
		 * @return
		 */
		protected String formatDetail(Object detail, String indent) {
			return indent + detail;
		}

		@Override
		public void worked(Status status, double work, String progressMessage, Object... data) {
			out.print(indent+"  ["+status+" "+work+"] "+progressMessage);
			if (data.length > 0) { 
				out.print(": "+Arrays.deepToString(data));
			}
			out.println();
			if (status == Status.CANCEL) {
				cancelled = true;
			}
		}

		@Override
		public ProgressMonitor setWorkRemaining(double size) {
			// NOP
			return this;
		}

	}	

	public void execute() throws MojoExecutionException {
		
		ActionSiteGenerator actionSiteGenerator = new ActionSiteGenerator() {
			
			@Override
			protected Context createContext(ProgressMonitor progressMonitor) {
				DiagramGenerator diagramGenerator;
				if (Util.isBlank(drawioViewer)) {
					diagramGenerator = DiagramGenerator.INSTANCE;
				} else {
					diagramGenerator = new DiagramGeneratorImpl() {
						
						protected String getDrawioViewer() {
							return drawioViewer;
						};
						
					};
				}
				if (diagramGenerators != null) {
					for (DiagramGenerator dg: diagramGenerators) {
						diagramGenerator = dg.compose(diagramGenerator);
					}
				}
				return Context.singleton(DiagramGenerator.class, diagramGenerator).compose(super.createContext(progressMonitor));
			}
			
			@Override
			protected ProgressMonitor createProgressMonitor() {
				if (progressOutput == null) {
					return new LogProgressMonitor(getLog(), 0, 2);
				}
				
//				if (jsonProgress) {
//					// JSON output progress monitor
//					
//				}
				
				try {
					return new PrintStreamProgressMonitor(new PrintStream(progressOutput), 0, 2, true);
				} catch (FileNotFoundException e) {
					throw new NasdanikaException(e);
				}
			}
			
		};
		
		File baseDir = project.getBasedir();
		URI baseDirURI = URI.createFileURI(baseDir.getAbsolutePath()).appendSegment("");
		
		URI actionURI = URI.createURI(action).resolve(baseDirURI);
		URI pageTemplateURI = URI.createURI(pageTemplate).resolve(baseDirURI);

		try {
			Map<String, Collection<String>> errors = actionSiteGenerator.generate(
					actionURI, 
					pageTemplateURI, 
					siteMapDomain, 
					outputDirectory, 
					workDirectory, 
					cleanWorkDir);
			
			int errorCount = 0;			
			for (Entry<String, Collection<String>> ee: errors.entrySet()) {
				getLog().error(ee.getKey());
				for (String error: ee.getValue()) {
					++errorCount;
					getLog().error("\t" + error);
				}
			}
			if (errorCount != this.errors) {
				throw new MojoExecutionException("There are site" + errorCount + " site errors");
			}
		} catch (IOException | DiagnosticException ex) {
			throw new MojoExecutionException(ex);
		}
	}
}
