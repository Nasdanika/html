package org.nasdanika.html.emf;


import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionImpl;
import org.nasdanika.html.app.NavigationActionImpl;

/**
 * Renders HTML using EClass metadata.
 * @author Pavel Vlasov
 */
public class EClassHTMLRenderer<RC extends RenderingContext> implements Renderer {
	
	protected EClass eClass;
	protected HTMLFactory htmlFactory;
	protected RC renderingContext;

	public EClassHTMLRenderer(EClass eClass, HTMLFactory htmlFactory, RC renderingContext) {
		this.eClass = eClass;
		this.htmlFactory = htmlFactory;
		this.renderingContext = renderingContext;
	}
	
	public EClassHTMLRenderer(EClass eClass, RC renderingContext) {
		this(eClass, HTMLFactory.INSTANCE, renderingContext);
	}

	@Override
	public Object renderView() {
		return htmlFactory.fragment(
				renderBreadcrumbs(),
				renderTitle(),
				renderNonSectionFeatures(),
				renderButtons(),
				renderSections());
	}

	/**
	 * Renders sections in a named items container.
	 * @return
	 */
	protected Object renderSections() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Renders buttons, e.g. edit and delete
	 * @return
	 */
	protected Object renderButtons() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Renders features which are displayed in a table such as single-value references and atributes.
	 * @return
	 */
	protected Object renderNonSectionFeatures() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Renders object title.
	 * @return
	 */
	protected Object renderTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Renders object path in the containment hierarchy.
	 * @return
	 */
	protected Object renderBreadcrumbs() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Object renderEdit() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Derives label (display name) from a name. This implementation splits by camel case,
	 * lowercases 1+ segments and capitalizes the 0 segment. E.g. myCoolName becomes My cool name.
	 * @param name
	 * @return
	 */
	protected String nameToLabel(String name) {
		String[] cca = StringUtils.splitByCharacterTypeCamelCase(name);
		cca[0] = StringUtils.capitalize(cca[0]);
		for (int i=1; i<cca.length; ++i) {
			cca[i] = cca[i].toLowerCase();
		}
		return StringUtils.join(cca, " ");
	}

	@Override
	public Action getViewAction() {
		// For initial testing.
		NavigationActionImpl ret = new NavigationActionImpl();
		ret.setLabel("Test");
		ret.setIcon("far fa-user");
		ret.setHref("somewhere");
		ret.setId("L1000-view");
		ret.setTooltip("Opens view page");
		ret.getChildren().add(new ActionImpl());
		
		
		return ret;
	}


}
