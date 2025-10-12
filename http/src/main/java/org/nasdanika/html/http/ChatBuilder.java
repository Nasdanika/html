package org.nasdanika.html.http;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.common.Util;
import org.nasdanika.html.Button;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.TextArea;
import org.nasdanika.html.alpinejs.AlpineJsFactory;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.InputGroup;
import org.nasdanika.html.bootstrap.Modal;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

/**
 * Builds a Boostrap/AlpineJs chat application.
 */
public class ChatBuilder {
	
	protected static final String TEXT_KEY = "text";
	protected static final String MESSAGES_KEY = "messages";
	protected static final String CONFIG_KEY = "config";
	protected static final String CONTEXT_KEY = "context";
	private BootstrapFactory bootstrapFactory = BootstrapFactory.INSTANCE;
	private AlpineJsFactory alpineJsFactory = AlpineJsFactory.INSTANCE;
	protected String action;
	
	/**
	 * 
	 * @param action Chat processor URL 
	 * @param bootstrapFactory
	 * @param alpineJsFactory
	 */
	public ChatBuilder(String action,
			BootstrapFactory bootstrapFactory, 
			AlpineJsFactory alpineJsFactory) {
		
		this.action = action;
		this.setBootstrapFactory(bootstrapFactory);
		this.setAlpineJsFactory(alpineJsFactory);
	}
	
	public AlpineJsFactory getAlpineJsFactory() {
		return alpineJsFactory;
	}

	public void setAlpineJsFactory(AlpineJsFactory alpineJsFactory) {
		this.alpineJsFactory = alpineJsFactory;
	}

	public BootstrapFactory getBootstrapFactory() {
		return bootstrapFactory;
	}

	public void setBootstrapFactory(BootstrapFactory bootstrapFactory) {
		this.bootstrapFactory = bootstrapFactory;
	}		

	/**
	 * @param action Chat processor URL 
	 */
	public ChatBuilder(String action) {
		this(action, BootstrapFactory.INSTANCE, AlpineJsFactory.INSTANCE);
	}	
	
//	protected String home(HttpServerRequest request, HttpServerResponse response) {
	
	public HTMLPage buildPage() {		
		HTMLPage page = getBootstrapFactory().bootstrapCdnHTMLPage();		
		getAlpineJsFactory().cdn(page);		
		page.head().stylesheet("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css");
		Container chatApp = buildAppContainer();
		page.body(chatApp);
		return page;
	}

	public Container buildAppContainer() {
		Container chatApp = getBootstrapFactory().container();
		JSONObject appData = getAlpineJsFactory().from(chatApp.toHTMLElement()).data();
		JSONArray messagesArray = new JSONArray();
		appData
			.put(MESSAGES_KEY, messagesArray)
			.put(CONFIG_KEY, getConfig())
			.put(CONTEXT_KEY, getContext())
			.put(TEXT_KEY, "");		
		
		// Chat message cards
		Tag messageCardHtmlElement = buildMessageCard();				
		Tag messagesFor = getAlpineJsFactory()._for("message in messages", messageCardHtmlElement);		
		chatApp.row().col().content(messagesFor);
		
		// Text area
		InputGroup inputGroup = buildInputGroup(chatApp);
		chatApp.row().col().content(inputGroup);
		return chatApp;
	}

	protected InputGroup buildInputGroup(Container chatApp) {
		TextArea textArea = getBootstrapFactory().getHTMLFactory().textArea();
		textArea
			.name("userInput")
			.placeholder(getInputPlaceholder());
		InputGroup textAreaInputGroup = getBootstrapFactory()
			.inputGroup()
			.input(textArea)
			.prepend(getInputTitle());
		getAlpineJsFactory().from(textArea).model(TEXT_KEY);
		
		Button submitButton = getBootstrapFactory().getHTMLFactory().button(getSubmitButtonText());		
		String submitHandler = buildSubmitHandler();		
		getAlpineJsFactory()
			.from(submitButton)
			.on("click", submitHandler)
			.bind("disabled", "!text");
		org.nasdanika.html.bootstrap.Button<Button> bootstrapSubmitButton = getBootstrapFactory().button(submitButton, Color.PRIMARY, false);
		textAreaInputGroup.append(bootstrapSubmitButton);
		
		Object configurator = getConfigurator();
		if (configurator != null) {
			Modal configModal = createConfigModal(configurator);
			if (configModal != null) {
				Button configButton = getBootstrapFactory().getHTMLFactory().button(TagName.i.create().addClass("fas fa-cog"));
				org.nasdanika.html.bootstrap.Button<Button> bootstrapConfigButton = getBootstrapFactory().button(configButton, Color.SECONDARY, false);
				configModal.bindTrigger(configButton);
				textAreaInputGroup.append(bootstrapConfigButton);
				chatApp.toHTMLElement().content(configModal);
			}
		}
		
		getAlpineJsFactory().from(textAreaInputGroup.toHTMLElement()).ref("inputGroup"); // for scrolling to		
		return textAreaInputGroup;
	}
	
	/**
	 * Creates a config modal dialog with configurator in the body
	 * @param configurator
	 * @return
	 */
	protected Modal createConfigModal(Object configurator) {
		Modal modal = getBootstrapFactory().modal();
		TagBootstrapElement header = modal.getHeader();
		header.background(Color.LIGHT);
		Tag headerHtmlElement = header.toHTMLElement();
		headerHtmlElement.content("Configuration");
		Button closeButton = getBootstrapFactory().getHTMLFactory().button(TagName.i.create().addClass("fas fa-times"));
		headerHtmlElement.content(closeButton);
		modal.bindDismisser(closeButton);
		
		modal.getBody().toHTMLElement().content(configurator);		
		return modal;
	}

	/**
	 * If non-null is returned, then a configuration modal and button are created
	 * @return
	 */
	protected Object getConfigurator() {
		return null;
	}	

	protected String getSubmitButtonText() {
		return "Submit";
	}

	protected Object getInputTitle() {
		return "Chat";
	}

	protected String getInputPlaceholder() {
		return "";
	}

	protected String buildSubmitHandler() {
		return Util.interpolate("""
			messages.push({
				content: ${userQuestionExpression},
				style: 'primary'
			});
			
			var responseMessage = Alpine.reactive({
				content: 'Processing...',
				style: 'muted'
			});
			messages.push(responseMessage);						
			
			fetch("${action}", {
				method: 'POST',
				headers: {'Content-Type': 'application/json'},
				body: JSON.stringify({
					text: text,
					config: config,
					context: context
				})
			}).then(response => {
				if (response.ok) {
					response.json().then(responseJson => {
						responseMessage.content = responseJson.content;
						responseMessage.style = responseJson.style;
						setTimeout(() => {
								$refs.inputGroup.scrollIntoView({ behavior: 'smooth', block: 'end' });
							},
							100);
					})
					.catch(error => {
					    console.error("Error parsing JSON:", error);
					    responseMessage.content = "Oops! Something went wrong while processing the response.";
					    responseMessage.style = 'danger';
					});
				} else {
					responseMessage.content = response.status + ": " + response.statusText;
					responseMessage.style = 'danger';
				}
			})
			.catch(error => {
			    console.error("Fetch error:", error);
			    responseMessage.content = ${fetchError};
			    responseMessage.style = 'danger';
			});
			text = '';
			
			setTimeout(() => {
					$refs.inputGroup.scrollIntoView({ behavior: 'smooth', block: 'end' });
				},
				100);			
			""",
			Map.of(
					"action", action,
					"fetchError", getFetchError(),
					"userQuestionExpression", getUserQuestionExpression()					
			)::get);		
	}
	
	protected String getUserQuestionExpression() {
		return "'<div style=\"white-space:pre-wrap\">' + text + '</div>'";
	}
	
	/**
	 * Fetch error expression
	 * @return
	 */
	protected String getFetchError() {
		return "\"Network error: Unable to fetch data.\"";
	}
	
	protected JSONObject getConfig() {
		return new JSONObject();
	}	
	
	protected JSONObject getContext() {
		return new JSONObject();
	}	

	protected Tag buildMessageCard() {
		Card messageCard = getBootstrapFactory().card();
		messageCard.margin().bottom(Breakpoint.DEFAULT, Size.S1);
		Tag messageCardHtmlElement = messageCard.toHTMLElement();
		getAlpineJsFactory()
			.from(messageCardHtmlElement)
			.bind("class", "'border-' + message.style");
		messageCard.border(Color.DEFAULT);		
		Tag messageCardBody = messageCard.getBody().toHTMLElement();
		messageCardBody.content("Loading...");
		getAlpineJsFactory()
			.from(messageCardBody)
			.html("message.content");
		return messageCardHtmlElement;
	}

}
