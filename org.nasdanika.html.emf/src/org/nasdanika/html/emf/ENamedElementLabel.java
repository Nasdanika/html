package org.nasdanika.html.emf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.jsoup.Jsoup;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.bootstrap.Color;

/**
 * Base class for labels and actions representing {@link ENamedElement}s. 
 * This class implements getText() method by splitting element name by camel case, capitalizing the first word and lower-casing the rest.
 * It also implements getDescription() and getTooltip() (TODO).
 * @author Pavel Vlasov
 *
 */
public class ENamedElementLabel implements Label {
		
	public static final Pattern SENTENCE_PATTERN = Pattern.compile(".+?[\\.?!]+\\s+");		
	public static final int MIN_FIRST_SENTENCE_LENGTH = 20;
	public static final int MAX_FIRST_SENTENCE_LENGTH = 250;	
	public static final String[] ABBREVIATIONS = { "e.g.", "i.e.", "etc." };

	/**
	 * Source for Ecore GenModel documentation.
	 */
	public static final String ECORE_DOC_ANNOTATION_SOURCE = "http://www.eclipse.org/emf/2002/GenModel";	
	
	private ENamedElement eNamedElement;

	public ENamedElementLabel(ENamedElement eNamedElement) {
		this.eNamedElement = eNamedElement;
	}

	@Override
	public String getIcon() {
		return null;
	}

	@Override
	public String getText() {
		String[] cca = StringUtils.splitByCharacterTypeCamelCase(eNamedElement.getName());
		cca[0] = StringUtils.capitalize(cca[0]);
		for (int i=1; i<cca.length; ++i) {
			cca[i] = cca[i].toLowerCase();
		}
		return StringUtils.join(cca, " ");
	}

	@Override
	public String getTooltip() {
		// TODO get description, textify it with Jsoup, extract first x characters.
		String description = getDescription();
		if (description == null || description.trim().isEmpty()) {
			return null;
		}
		String textDoc = Jsoup.parse(description).text();
		return firstSentence(textDoc);					
	}

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public boolean isOutline() {
		return false;
	}

	@Override
	public String getDescription() {
		EAnnotation docAnn = eNamedElement.getEAnnotation(ECORE_DOC_ANNOTATION_SOURCE);
		if (docAnn==null && eNamedElement instanceof EReference) {
			docAnn = ((EReference) eNamedElement).getEReferenceType().getEAnnotation(ECORE_DOC_ANNOTATION_SOURCE);
		}
		if (docAnn == null) {
			return null;
		}
		
		String markdown = docAnn.getDetails().get("documentation");
		if (markdown == null || markdown.trim().isEmpty()) {
			return null;
		}
	
		return null; // markdownToHtml(markdown); - TODO				
	}

	@Override
	public Object getId() {
		return null;
	}

	@Override
	public String getNotification() {
		return null;
	}
	
	protected String[] getAbbreviations() {
		return ABBREVIATIONS;
	}
	
	/**
	 * Extracts first sentence from text
	 * @param context
	 * @param text
	 * @return
	 * @throws Exception
	 */
	protected String firstSentence(String text) {
		if (text == null || text.length() < getMinFirstSentenceLength()) {
			return text;
		}
		Matcher matcher = SENTENCE_PATTERN.matcher(text);		
		Z: while (matcher.find()) {
			String group = matcher.group();
			String[] abbreviations = getAbbreviations();
			for (String abbr: abbreviations) {
				if (group.trim().endsWith(abbr)) {
					continue Z;
				}
			}
			if (matcher.end() > getMinFirstSentenceLength() && matcher.end() < getMaxFirstSentenceLength()) {
				return text.substring(0, matcher.end());
			}
		}
		
		return text.length() < getMaxFirstSentenceLength() ? text : text.substring(0, getMaxFirstSentenceLength())+"...";
	}
	
	protected int getMinFirstSentenceLength() {
		return MIN_FIRST_SENTENCE_LENGTH;
	}
	
	protected int getMaxFirstSentenceLength() {
		return MAX_FIRST_SENTENCE_LENGTH;
	}
	
}
