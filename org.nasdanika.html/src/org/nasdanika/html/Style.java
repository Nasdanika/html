package org.nasdanika.html;

/**
 * Java bindings for frequently used CSS styles. 
 * @author Pavel Vlasov
 *
 */
public interface Style<T extends HTMLElement<T>> {
	
	/**
	 * Base for interfaces with normal, initial, and inherit methods. 
	 * @author Pavel Vlasov
	 *
	 * @param <T>
	 */
	interface InitialInherit<T> {

		T initial();
		T inherit();
		
	}
	
	/**
	 * Base for interfaces with none, initial, and inherit methods. 
	 * @author Pavel Vlasov
	 *
	 * @param <T>
	 */
	interface NoneInitialInherit<T> extends InitialInherit<T> {

		T none();
		
	}
	
	
	/**
	 * Base for interfaces with normal, initial, and inherit methods. 
	 * @author Pavel Vlasov
	 *
	 * @param <T>
	 */
	interface NormalInitialInherit<T> extends InitialInherit<T> {

		T normal();
		
	}
	
	T width(Object width);
	T height(Object height);
	
	interface Color<T> extends InitialInherit<T> {
		
		T value(Object color);
		
		T color(org.nasdanika.html.Color color);
		
	}
	
	interface Background<T> extends InitialInherit<T> {
	
		interface BackgroundColor<T> extends Color<T> {
			
			T transparent();
			
		}
		
		BackgroundColor<T> color();
				

//		Value	Description	CSS
//		* background-color	Specifies the background color to be used	1
//		background-image	Specifies ONE or MORE background images to be used	1
//		background-position	Specifies the position of the background images	1
//		background-size	Specifies the size of the background images	3
//		background-repeat	Specifies how to repeat the background images	1
//		background-origin	Specifies the positioning area of the background images	3
//		background-clip	Specifies the painting area of the background images	3
//		background-attachment		
	}
	
	
	T background(Object spec);
	
	Background<T> background();	
		
	Color<T> color();
	
	interface Font<T> {
		
		T size(Object size);
		
		T family(Object family);
		
		interface FontStyle<T> extends NormalInitialInherit<T> {
			
			T italic();
			T oblique();
			
		}
		
		FontStyle<T> style();
		
		interface FontVariant<T> extends NormalInitialInherit<T> {

			T smallCaps();

		}
		
		FontVariant<T> variant();
		
		interface FontWeight<T> extends NormalInitialInherit<T> {

			T bold();
			T bolder();
			T lighter();
			T number(Object weight);

		}

		FontWeight<T> weight();

	};
	
	interface Display<T> extends NoneInitialInherit<T> {
		T inline(); 
		T block();
		T flex();	
		T inlineBlock();	
		T inlineFlex();	
		T inlineTable();	 
		T listItem();
		T runIn();	 
		T table();	
		T tableCaption();	
		T tableColumnGroup();	
		T tableHeaderGroup();	
		T tableFooterGroup();	
		T tableRowGroup();	
		T tableCell();	
		T tableColumn();	
		T tableRow();	
	}
	
	Display<T> display();
	
	interface Float<T> extends NoneInitialInherit<T> {
		
		T left();
		T right();
		
	}
	
	Float<T> float_();
	
	Font<T> font();
	
	interface Box<T> {
		
		T left(Object spec);
		T right(Object spec);
		T top(Object spec);
		T bottom(Object spec);
		
	}
	
	T margin(Object spec);
	
	Box<T> margin();
	
	T padding(Object spec);
	
	Box<T> padding();
	
	T border(Object spec);
	
	Box<T> border();
	
	interface Text<T> {
		
		interface TextAlign<T> extends InitialInherit<T> {
			
			T left();
			T right();
			T center();
			T justify();			
		}
		
		TextAlign<T> align();
		
		interface TextDecoration<T> extends NoneInitialInherit<T> {
		
			T underline(); 
			T overline(); 
			T lineThrough();
			
		}
		
		TextDecoration<T> decoration();
		
		interface TextJustify<T> extends InitialInherit<T> {
			
			T auto(); 
			T interWord(); 
			T interIdeograph(); 
			T interCluster(); 
			T distribute(); 
			T kashida(); 
			T trim();
			
		}
		
		TextJustify<T> justify();
		
		interface TextOverflow<T> extends InitialInherit<T> {
			
			T clip(); 
			T ellipsis(); 
			T string(Object value);
			
		}
		
		TextOverflow<T> overflow();
						
	}
	
	Text<T> text();
	
	interface WhiteSpace<T> extends NoneInitialInherit<T> {
		T nowrap();
		T pre();
		T preLine();
		T preWrap();		
	}
	
	WhiteSpace<T> whiteSpace();
	
	/*
	CSS Properties from W3Schools, prefixed with * are implemented, with ~ are partially implemented
	align-content
	align-items
	align-self
	all
	animation
	animation-delay
	animation-direction
	animation-duration
	animation-fill-mode
	animation-iteration-count
	animation-name
	animation-play-state
	animation-timing-function
	backface-visibility
	~ background
	background-attachment
	background-blend-mode
	background-clip
	* background-color
	background-image
	background-origin
	background-position
	background-repeat
	background-size
	* border
	* border-bottom
	border-bottom-color
	border-bottom-left-radius
	border-bottom-right-radius
	border-bottom-style
	border-bottom-width
	border-collapse
	border-color
	border-image
	border-image-outset
	border-image-repeat
	border-image-slice
	border-image-source
	border-image-width
	* border-left
	border-left-color
	border-left-style
	border-left-width
	border-radius
	* border-right
	border-right-color
	border-right-style
	border-right-width
	border-spacing
	border-style
	* border-top
	border-top-color
	border-top-left-radius
	border-top-right-radius
	border-top-style
	border-top-width
	border-width
	bottom
	box-shadow
	box-sizing
	caption-side
	clear
	clip
	* color
	column-count
	column-fill
	column-gap
	column-rule
	column-rule-color
	column-rule-style
	column-rule-width
	column-span
	column-width
	columns
	content
	counter-increment
	counter-reset
	cursor
	direction
	display
	empty-cells
	filter
	flex
	flex-basis
	flex-direction
	flex-flow
	flex-grow
	flex-shrink
	flex-wrap
	float
	font
	@font-face
	* font-family
	* font-size
	font-size-adjust
	font-stretch
	* font-style
	* font-variant
	* font-weight
	hanging-punctuation
	* height
	justify-content
	@keyframes
	left
	letter-spacing
	line-height
	list-style
	list-style-image
	list-style-position
	list-style-type
	* margin
	* margin-bottom
	* margin-left
	* margin-right
	* margin-top
	max-height
	max-width
	@media
	min-height
	min-width
	nav-down
	nav-index
	nav-left
	nav-right
	nav-up
	opacity
	order
	outline
	outline-color
	outline-offset
	outline-style
	outline-width
	overflow
	overflow-x
	overflow-y
	* padding
	* padding-bottom
	* padding-left
	* padding-right
	* padding-top
	page-break-after
	page-break-before
	page-break-inside
	perspective
	perspective-origin
	position
	quotes
	resize
	right
	tab-size
	table-layout
	* text-align
	text-align-last
	* text-decoration
	text-decoration-color
	text-decoration-line
	text-decoration-style
	text-indent
	* text-justify
	* text-overflow
	text-shadow
	text-transform
	top
	transform
	transform-origin
	transform-style
	transition
	transition-delay
	transition-duration
	transition-property
	transition-timing-function
	unicode-bidi
	vertical-align
	visibility
	* white-space
	* width
	word-break
	word-spacing
	word-wrap
	z-index
	*/

}
