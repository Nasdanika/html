package org.nasdanika.html;

public enum TagName {
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
	i(true),
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
	span(true, false),
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
	
	public final boolean paired;
	
	/**
	 * If true the tag content can be formatted by indenting contained tags.
	 */
	public final boolean indent;
	
	/**
	 * Creates tag with {@link HTMLFactory}.INSTANCE.
	 * @param content
	 * @return
	 */
	public Tag create(Object... content) {
		return HTMLFactory.INSTANCE.tag(this, content);
	}
	
	private TagName() {
		this(false);
	}
	
	private TagName(boolean paired) {
		this(paired, true);
	}
	
	private TagName(boolean paired, boolean indent) {
		this.paired = paired;
		this.indent = indent;
	}
	
}