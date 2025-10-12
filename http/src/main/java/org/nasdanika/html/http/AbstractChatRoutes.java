package org.nasdanika.html.http;

import java.util.Collections;
import java.util.UUID;

import org.json.JSONObject;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.alpinejs.AlpineJsFactory;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.http.ReflectiveHttpServerRouteBuilder.Route;

import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import reactor.core.publisher.Mono;
import reactor.netty.NettyOutbound;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

public abstract class AbstractChatRoutes extends ChatBuilder {
	
	protected AbstractChatRoutes(BootstrapFactory bootstrapFactory, AlpineJsFactory alpineJsFactory) {
		super("chat", bootstrapFactory, alpineJsFactory);		
	}

	protected AbstractChatRoutes() {
		super("chat");
	}

	protected String getCookie() {
		return "CHATID";
	}
	
	protected Cookie createChatCookie(String chatId) {
        DefaultCookie cookie = new DefaultCookie("SESSIONID", chatId);
        cookie.setPath(getCookiePath());
        cookie.setMaxAge(getCookieMaxAge()); 
		return cookie;
	}

	protected int getCookieMaxAge() {
		return 3600;
	}

	protected String getCookiePath() {
		return "/";
	}
	
	protected record Message(String content, Color style) {
		
	}
	
	protected Mono<Message> chat(
			HttpServerRequest request, 
			String chatId, 
			String question, 
			JSONObject config,
			JSONObject context) {
		
		return chatContent(
				request, 
				chatId, 
				question, 
				config,
				context).map(c -> new Message(c, Color.INFO));
	}
	
	protected abstract Mono<String> chatContent(
			HttpServerRequest request, 
			String chatId, 
			String question, 
			JSONObject config, 
			JSONObject context);	
	
	@Route
	public NettyOutbound getChat(
			HttpServerRequest request, 
			HttpServerResponse response) {

		return response
				.header("Content-Type", "text/html; charset=utf-8")
				.sendString(buildPage(request).map(Object::toString));
	}
	
	protected Mono<HTMLPage> buildPage(HttpServerRequest request) {
		return Mono.fromSupplier(this::buildPage);
	}
	
	@Route
	public NettyOutbound postChat(
			HttpServerRequest request, 
			HttpServerResponse response) {
		
		String chatId = request
				.cookies()
				.getOrDefault(getCookie(), Collections.emptySet())
				.stream()
				.findFirst()
				.map(Cookie::value)
				.orElseGet(() -> {
					String newChatId = UUID.randomUUID().toString();
					 Cookie cookie = createChatCookie(newChatId);
					 String encodedCookie = ServerCookieEncoder.STRICT.encode(cookie);
			         response.addHeader("Set-Cookie", encodedCookie);					 
					return newChatId;
				});
		
		Mono<JSONObject> requestJSON = request.receive().aggregate().asString().map(JSONObject::new);		
		return response
				.header("Content-Type", "application/json")
				.sendString(requestJSON
						.flatMap(json -> chat(request, chatId, json.getString(TEXT_KEY), json.getJSONObject(CONFIG_KEY), json.getJSONObject(CONTEXT_KEY)))
						.map(msg -> {
							JSONObject jResponse = new JSONObject();
							jResponse.put("content", msg.content());
							jResponse.put("style", msg.style().code);
							return jResponse.toString(2);
						}));							
	}
	
}
