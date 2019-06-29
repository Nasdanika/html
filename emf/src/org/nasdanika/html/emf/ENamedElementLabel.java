package org.nasdanika.html.emf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jsoup.Jsoup;
import org.nasdanika.emf.AnnotationSource;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.bootstrap.Color;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.profiles.pegdown.Extensions;
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.options.DataHolder;


/**
 * Base class for labels and actions representing {@link ENamedElement}s. 
 * This class implements getText() method by splitting element name by camel case, capitalizing the first word and lower-casing the rest.
 * It also implements getDescription() and getTooltip() (TODO).
 * @author Pavel Vlasov
 *
 */
public class ENamedElementLabel<T extends ENamedElement> extends AnnotationSource<T> implements Label {
		
	public static final Pattern SENTENCE_PATTERN = Pattern.compile(".+?[\\.?!]+\\s+");		
	public static final int MIN_FIRST_SENTENCE_LENGTH = 20;
	public static final int MAX_FIRST_SENTENCE_LENGTH = 250;	
	public static final String[] ABBREVIATIONS = { "e.g.", "i.e.", "etc." };
	
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

		if (markdown == null || markdown.trim().isEmpty()) {
			return null;
		}
	
		return markdownToHtml(markdown);				
	}
	
	protected String preProcessMarkdown(String markdown) {
		return markdown;
	}
		
	protected DataHolder getFlexmarkOptions() {
	    return PegdownOptionsAdapter.flexmarkOptions(Extensions.ALL ^ Extensions.HARDWRAPS ^ Extensions.SUPPRESS_HTML_BLOCKS ^ Extensions.SUPPRESS_ALL_HTML);
	}	
	
	protected Parser createMarkdownParser() {
		return Parser.builder(getFlexmarkOptions()).build();
	}
		
	protected HtmlRenderer createMarkdownHtmlRenderer() {
		return HtmlRenderer.builder(getFlexmarkOptions()).build();
	}	
	
	protected String markdownToHtml(String markdown) {
		Document document = createMarkdownParser().parse(markdown);
		return createMarkdownHtmlRenderer().render(document);
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
