package org.nasdanika.html.app.impl;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.nasdanika.common.ProgressEntry;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.ProgressRecorder.Step;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.Table;

/**
 * This class can be used to generate HTML reports from progress entries, e.g. a code generation report. 
 * @author Pavel
 *
 */
public class ProgressEntryViewAction extends ActionImpl {
	
	private static AtomicLong counter = new AtomicLong();

	private ProgressEntry progressEntry;


	public ProgressEntryViewAction(ProgressEntry progressEntry) {
		this.progressEntry = progressEntry;
		setText(progressEntry.getName());
		
		for (ProgressEntry peChild: progressEntry.getChildren()) {
			ProgressEntryViewAction child = new ProgressEntryViewAction(peChild);
			child.setParent(this);
			getChildren().add(child);
		}
		
		setId(Long.toString(counter.incrementAndGet(), Character.MAX_RADIX));
		
		setActivator(new NavigationActionActivator() {
			
			@Override
			public String getUrl(String base) {
				String ret = ProgressEntryViewAction.this.getId()+".html";
				// TODO - take base into account.
				return ret;
			}
			
		});
		
		getRoles().add(Role.NAVIGATION);
		
	}
		
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
		Table infoTable = bootstrapFactory.table();
		infoTable.toHTMLElement().style().width("auto");
		if (progressEntry.getStart() > 0) {
			Row startRow = infoTable.row();
			startRow.header("Start");
			startRow.cell(new Date(progressEntry.getStart()));
		}
		if (progressEntry.getFinish() > progressEntry.getStart()) {
			Row durationRow = infoTable.row();
			durationRow.header("Duration");
			durationRow.cell(progressEntry.getFinish() - progressEntry.getStart());
		}
		if (progressEntry.getTotalWork() > 0) {
			Row totalWorkRow = infoTable.row();
			totalWorkRow.header("Total work");
			totalWorkRow.cell(progressEntry.getTotalWork());			
		}
		
		HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
		Fragment fragment = htmlFactory.fragment(infoTable);
		
		if (progressEntry.getData() != null && progressEntry.getData().size() > 0) {
			fragment.content(TagName.h2.create("Data"));
			Table detailsTable = bootstrapFactory.table().bordered();
			detailsTable.headerRow("Type", "Value");
			for (Object obj: progressEntry.getData()) {
				detailsTable.row(obj != null ? obj.getClass().getName() : "", String.valueOf(obj));
			}
			fragment.content(detailsTable);
		}
		
		if (!progressEntry.getSteps().isEmpty()) {
			fragment.content(TagName.h2.create("Steps"));
			Table stepsTable = bootstrapFactory.table().bordered();
			stepsTable.headerRow("Time", "Status", "Worked", "Message");
			for (Step step: progressEntry.getSteps()) {				
				Row stepRow = stepsTable.row(new Date(step.getTime()), step.getStatus().name(), step.getWorked(), step.getMessage());
				switch (step.getStatus()) {
				case CANCEL:
					stepRow.color(Color.MUTED);
					break;
				case WARNING:
					stepRow.color(Color.WARNING);
					break;
				case ERROR:
					stepRow.color(Color.DANGER);
					break;
				case SUCCESS:
					stepRow.color(Color.SUCCESS); 						
					break;
				default:
					break; // INFO.
				}
			}
			fragment.content(stepsTable);			
		}
		
		return fragment;
	}

}
