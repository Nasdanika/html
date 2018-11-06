package org.nasdanika.html;

/**
 * A generic UI element. It doesn't add any functionality,
		only binds UIElement
 * and Container generic parameter to self for convenience.
 * 
 * @author Pavel
 *
 */
public interface Tag extends HTMLElement<Tag>, Container<Tag> {

	enum TagName {
		a(true),
		abbr,
		acronym,
		address,
		area,
		article,
		aside,
		audio,
		b,
		base,
		bdi,
		bdo,
		blockquote,
		body(true),
		br,
		button,
		canvas,
		caption,
		cite,
		code,
		col,
		colgroup,
		datalist,
		dd,
		del,
		details,
		dfn,
		dialog,
		div(true),
		dl,
		dt,
		em,
		embed,
		fieldset,
		figcaption,
		figure,
		footer,
		form(true),
		h1(true),
		h2(true),
		h3(true),
		h4(true),
		h5(true),
		h6(true),
		head(true),
		header,
		hgroup,
		hr,
		html,
		i,
		iframe(true),
		img,
		input,
		ins,
		kbd,
		keygen,
		label,
		legend,
		li(true),
		link,
		main,
		map,
		mark,
		menu,
		menuitem,
		meta,
		meter,
		nav,
		noscript,
		object,
		ol(true),
		optgroup,
		option,
		output,
		p,
		param,
		pre,
		progress,
		q,
		rp,
		rt,
		ruby,
		s,
		samp,
		script(true),
		section,
		select(true),
		small,
		source,
		span(true),
		strong,
		style,
		sub,
		summary,
		sup,
		table(true),
		tbody(true),
		td(true),
		textarea(true),
		tfoot(true),
		th(true),
		thead(true),
		time,
		title,
		tr(true),
		track,
		u,
		ul(true),
		var,
		video,
		wbr;
		
		private boolean paired;
		
		public boolean isPaired() {
			return paired;
		}
		
		/**
		 * Creates tag with {@link HTMLFactory}.INSTANCE.
		 * @param content
		 * @return
		 */
		public Tag create(Object... content) {
			return HTMLFactory.INSTANCE.tag(this, content);
		}
		
		private TagName() {
		}
		
		private TagName(boolean paired) {
			this.paired = paired;
		}
		
	}

	String getTagName();
	
	boolean is(TagName tagName);
	
}
