package org.nasdanika.html.flow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Transition;
import org.nasdanika.html.emf.ColumnBuilder;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.bootstrap.TableCell;

public class ArtifactActionProvider extends PackageElementActionProvider<Artifact> {
	
	public ArtifactActionProvider(Artifact value, Context context) {
		super(value, context);
	}
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.ARTIFACT__INPUT_FOR);
		properties.add(FlowPackage.Literals.ARTIFACT__OUTPUT_FOR);
		properties.add(FlowPackage.Literals.ARTIFACT__REPOSITORIES);
		properties.add(FlowPackage.Literals.ARTIFACT__USED_BY);
		return properties;
	}
		
	@Override
	protected void resolve(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		super.resolve(action, context, progressMonitor);
	
		EList<Action> sections = action.getSections();
		if (!getTarget().getPayloadFor().isEmpty()) {
			sections.add(createEReferenceAction(action, FlowPackage.Literals.ARTIFACT__PAYLOAD_FOR, context, progressMonitor));
		}
		
		if (!getTarget().getResponseFor().isEmpty()) {
			sections.add(createEReferenceAction(action, FlowPackage.Literals.ARTIFACT__RESPONSE_FOR, context, progressMonitor));
		}
	}
	
	// TODO - Sort by source 
	private Action createEReferenceAction(
			Action action, 
			EReference eReference,
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		Collection<ColumnBuilder<EObject>> columnBuilders = new ArrayList<>();
		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Source"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				cell.getContent().add(renderValue(base, typedElement, rowElement.eContainer().eContainer(), context, progressMonitor));				
			}
		});
		
		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Key"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				EObject renderedValue = renderValue(base, typedElement, ((Map.Entry<?,?>) rowElement.eContainer()).getKey(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});
		
		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Target"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				EObject renderedValue = renderValue(base, typedElement, ((Transition) rowElement).getTarget(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});

		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Name"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				EObject renderedValue = renderValue(base, typedElement, ((Transition) rowElement).getName(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});

		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Documentation"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				EObject renderedValue = renderValue(base, typedElement, ((Transition) rowElement).getDocumentation(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});
		
		return createTableAction(
				eReference,
				columnBuilders,
				action, 
				context, 
				progressMonitor);
	}
	
}
