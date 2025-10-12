package org.nasdanika.html.http.tests;

import java.awt.Desktop;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Input;
import org.nasdanika.html.InputType;
import org.nasdanika.html.alpinejs.AlpineJs;
import org.nasdanika.html.http.AbstractChatRoutes;
import org.nasdanika.html.http.ChatBuilder;
import org.nasdanika.http.ReflectiveHttpServerRouteBuilder;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;

public class TestHttp {
	
	@Test
	public void testChatBuilder() throws Exception {		
		ChatBuilder chatBuilder = new ChatBuilder("chat");
		HTMLPage page = chatBuilder.buildPage();
		Files.writeString(Path.of("target", "chat.html"), page.toString());				
	}
	
	@Test
	@Disabled
	public void testChatServer() throws Exception {
		ReflectiveHttpServerRouteBuilder builder = new ReflectiveHttpServerRouteBuilder();
		builder.addTargets("/test-chat/", new AbstractChatRoutes() {
			
			@Override
			protected Object getConfigurator() {
				Input text = getBootstrapFactory().getHTMLFactory().input(InputType.text);
				AlpineJs<Input> aText = getAlpineJsFactory().from(text);
				aText.model("config.test");
				return text;
			}
			
			@Override
			protected JSONObject getConfig() {
				JSONObject jsonConfig = super.getConfig();
				jsonConfig.put("test", "123");
				return jsonConfig;
			}

			@Override
			protected Mono<String> chatContent(
					HttpServerRequest request, 
					String chatId, 
					String question,
					JSONObject config, 
					JSONObject context) {
				return Mono.just("Here we go [" + chatId +"]: " + question + " | " + config + " | " + context);
			}
			
		});

		DisposableServer server = HttpServer
		  .create()
		  .route(builder::buildRoutes)
		  .bindNow();		
		
	    URI resolvedUri = new URI("http://localhost:" + server.port() + "/").resolve("/test-chat/chat");			
		Desktop.getDesktop().browse(resolvedUri);
		
		try (Terminal terminal = TerminalBuilder.builder().system(true).build()) {
		    LineReader lineReader = LineReaderBuilder
		            .builder()
		            .terminal(terminal)
		            .build();
		    
		    String prompt = "http-server>";
		    while (true) {
		        String line = null;
		        line = lineReader.readLine(prompt);
		        System.out.println("Got: " + line);
		        if ("exit".equals(line)) {
		            break;
		        }
		    }
		}
		server.dispose();
		server.onDispose().block();		
	}
	
}
