package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.ViewAction;

public class EReferenceViewAction extends EStructuralFeatureViewAction<EReference> {

	public EReferenceViewAction(EReference value) {
		super(value);
	}
	
	@Override
	protected Table propertiesTable(ViewGenerator viewGenerator) {
		Table ret = super.propertiesTable(viewGenerator);
		EReference opposite = target.getEOpposite();
		if (opposite != null) {
			ViewAction oppositeViewAction = EObjectAdaptable.adaptTo(opposite, ViewAction.class);
			EObject oppositeContainer = opposite.eContainer();
			ViewAction oppositeContainerViewAction = EObjectAdaptable.adaptTo(oppositeContainer, ViewAction.class);
			Row typeRow = ret.row();
			typeRow.header(getResourceContext().getString("ui/opposite", "Opposite"));
			Cell refCell = typeRow.cell();
			if (oppositeContainerViewAction != null) {
				refCell.toHTMLElement().content(viewGenerator.link(oppositeContainerViewAction), "/");				
			}
			refCell.toHTMLElement().content(oppositeViewAction == null ?  opposite.getName() : viewGenerator.link(oppositeViewAction));
		}
		return ret;
	}

}
