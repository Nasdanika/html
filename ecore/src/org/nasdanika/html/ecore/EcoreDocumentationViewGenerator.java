package org.nasdanika.html.ecore;

import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.common.Context;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.jstree.JsTreeNode;

/**
 * Customized {@link ViewGenerator} for rendering Ecore documentation.
 * 
 * @author Pavel
 *
 */
public class EcoreDocumentationViewGenerator extends ViewGeneratorImpl {

	public EcoreDocumentationViewGenerator(Context context, Consumer<?> headContentConsumer,
			Consumer<?> bodyContentConsumer) {
		super(context, headContentConsumer, bodyContentConsumer);
	}

	/**
	 * Special handling of Eclasses - abstract are italic, interfaces are in dark
	 */
	@Override
	public void label(Label label, Consumer<Object> contentConsumer) {
		if (label instanceof Action) {
			EClass eClass = ((Action) label).adaptTo(EClass.class);
			if (eClass != null && eClass.isAbstract()) {
				Tag span = get(HTMLFactory.class).span();
				get(BootstrapFactory.class).wrap(span).text().italic();
				contentConsumer.accept(span);
				contentConsumer = span;
			}
		}
		super.label(label, contentConsumer);
	}

	@Override
	public JsTreeNode jsTreeNode(Action action, boolean ajax, BiFunction<Action, JsTreeNode, JsTreeNode> filter) {
		JsTreeNode ret = super.jsTreeNode(action, ajax, filter);
		EClass eClass = action.adaptTo(EClass.class);
		if (ret != null && eClass != null && eClass.isAbstract()) {
			ret.anchorAttribute("style", "font-style:italic");
		}
		return ret;
	}

};
