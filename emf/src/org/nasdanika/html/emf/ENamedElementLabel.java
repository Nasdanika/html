package org.nasdanika.html.emf;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jsoup.Jsoup;
import org.nasdanika.emf.AnnotationSource;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.Color;


/**
 * Base class for labels and actions representing {@link ENamedElement}s. 
 * This class implements getText() method by splitting element name by camel case, capitalizing the first word and lower-casing the rest.
 * It also implements getDescription() and getTooltip() (TODO).
 * @author Pavel Vlasov
 *
 */
public class ENamedElementLabel<T extends ENamedElement> extends AnnotationSource<T> implements Label {
	
	public ENamedElementLabel(T eNamedElement) {
		super(eNamedElement);
	}	

	@Override
	public String getIcon() {
		return getAnnotation("icon");
	}
	
	@Override
	public String getText() {
		String text = EObjectAdaptable.getResourceContext(modelElement).getString("label");
		if (text != null) {
			return text;
		}
				
		return nameToLabel();
	}

	/**
	 * Converts model element name to label text. This implementation breaks the name by camel case,
	 * capitalizes the first word and lowsercases the rest.
	 * @return
	 */
	protected String nameToLabel() {
		String[] cca = StringUtils.splitByCharacterTypeCamelCase(modelElement.getName());
		cca[0] = StringUtils.capitalize(cca[0]);
		for (int i=1; i<cca.length; ++i) {
			cca[i] = cca[i].toLowerCase();
		}
		return StringUtils.join(cca, " ");
	}

	@Override
	public String getTooltip() {
		String description = getDescription();
		if (description == null || description.trim().isEmpty()) {
			return null;
		}
		String textDoc = Jsoup.parse(description).text();
		return firstSentence(textDoc);					
	}

	@Override
	public Color getColor() {
		String ca = getAnnotation("color");
		return ca == null ? null : Color.valueOf(ca.trim());
	}

	@Override
	public boolean isOutline() {
		String outlineAnnotation = getAnnotation("outline");
		return outlineAnnotation != null && "true".equals(outlineAnnotation.trim());
	}

	@Override
	public String getDescription() {
		String markdown = EObjectAdaptable.getResourceContext(modelElement).getString("documentation", EcoreUtil.getDocumentation(modelElement));

		if (Util.isBlank(markdown)) {
			return null;
		}
	
		return HTMLFactory.INSTANCE.div(markdownToHtml(markdown)).addClass("markdown-body").toString();				
	}

	@Override
	public Object getId() {
		return null;
	}

	@Override
	public String getNotification() {
		return null;
	}
		
}
