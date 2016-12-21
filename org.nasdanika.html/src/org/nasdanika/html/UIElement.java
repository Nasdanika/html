package org.nasdanika.html;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Abstraction of a UI element. toString() produces HTML markup.
 * @author Pavel
 *
 */
public interface UIElement<T extends UIElement<T>> extends AutoCloseable, Producer {
	
	/**
	 * HTML Color codes.
	 * @author Pavel
	 *
	 */
	enum HTMLColor {
		AliceBlue,
		AntiqueWhite,
		Aqua,                     
		Aquamarine,               
		Azure,                    
		Beige,                    
		Bisque,                   
		Black,                    
		BlanchedAlmond,           
		Blue,                     
		BlueViolet,               
		Brown,                    
		BurlyWood,                
		CadetBlue,                
		Chartreuse,               
		Chocolate,                
		Coral,                    
		CornflowerBlue,           
		Cornsilk,                 
		Crimson,                  
		Cyan,                     
		DarkBlue,                 
		DarkCyan,                 
		DarkGoldenRod,            
		DarkGray,                 
		DarkGreen,                
		DarkKhaki,                
		DarkMagenta,              
		DarkOliveGreen,           
		DarkOrange,               
		DarkOrchid,               
		DarkRed,                  
		DarkSalmon,               
		DarkSeaGreen,             
		DarkSlateBlue,            
		DarkSlateGray,            
		DarkTurquoise,            
		DarkViolet,               
		DeepPink,                 
		DeepSkyBlue,              
		DimGray,                  
		DodgerBlue,               
		FireBrick,                
		FloralWhite,              
		ForestGreen,              
		Fuchsia,                  
		Gainsboro,                
		GhostWhite,               
		Gold,                     
		GoldenRod,                
		Gray,                     
		Green,                    
		GreenYellow,              
		HoneyDew,                 
		HotPink,                  
		IndianRed ,               
		Indigo ,                  
		Ivory,                    
		Khaki,                    
		Lavender,                 
		LavenderBlush,            
		LawnGreen,                
		LemonChiffon,             
		LightBlue,                
		LightCoral,               
		LightCyan,                
		LightGoldenRodYellow,     
		LightGray,                
		LightGreen,               
		LightPink,                
		LightSalmon,              
		LightSeaGreen,            
		LightSkyBlue,             
		LightSlateGray,           
		LightSteelBlue,           
		LightYellow,              
		Lime,                     
		LimeGreen,                
		Linen,                    
		Magenta,                  
		Maroon,                   
		MediumAquaMarine,         
		MediumBlue,               
		MediumOrchid,             
		MediumPurple,             
		MediumSeaGreen,           
		MediumSlateBlue,          
		MediumSpringGreen,        
		MediumTurquoise,          
		MediumVioletRed,          
		MidnightBlue,             
		MintCream,                
		MistyRose,                
		Moccasin,                 
		NavajoWhite,              
		Navy,                     
		OldLace,                  
		Olive,                    
		OliveDrab,                
		Orange,                   
		OrangeRed,                
		Orchid,                   
		PaleGoldenRod,            
		PaleGreen,                
		PaleTurquoise,            
		PaleVioletRed,            
		PapayaWhip,               
		PeachPuff,                
		Peru,                     
		Pink,                     
		Plum,                     
		PowderBlue,               
		Purple,                   
		Red,                      
		RosyBrown,                
		RoyalBlue,                
		SaddleBrown,              
		Salmon,                   
		SandyBrown,               
		SeaGreen,                 
		SeaShell,                 
		Sienna,                   
		Silver,                   
		SkyBlue,                  
		SlateBlue,                
		SlateGray,                
		Snow,                     
		SpringGreen,              
		SteelBlue,                
		Tan,                      
		Teal,                     
		Thistle,                  
		Tomato,                   
		Turquoise,                
		Violet,                   
		Wheat,                    
		White,                    
		WhiteSmoke,               
		Yellow,                   
		YellowGreen		
	}
	
	/**
	 * Enumeration for frequently used HTML events
	 * @author Pavel
	 *
	 */
	enum Event {
		// Form Events
		// Events triggered by actions inside a HTML form (applies to almost all HTML elements, but is most used in form elements):

		/** Fires the moment that the element loses focus **/
		blur, 
		
		/** Fires the moment when the value of the element is changed **/
		change,
		
		/** Script to be run when a context menu is triggered **/
		contextmenu, 
		
		/** Fires the moment when the element gets focus **/
		focus, 
		
		/** Script to be run when a form changes **/
		formchange,
		
		/** Script to be run when a form gets user input **/
		forminput,
		
		/** Script to be run when an element gets user input **/
		input, 
		
		/** Script to be run when an element is invalid **/
		invalid, 
		
		/** Fires when the Reset button in a form is clicked **/
		reset, 
		
		/** Fires after some text has been selected in an element **/
		select, 
		
		/** Fires when a form is submitted **/
		submit, 

		// Keyboard Events
		
		/** Fires when a user is pressing a key **/
		keydown, 
		
		/** Fires when a user presses a key **/
		keypress,
		
		/** Fires when a user releases a key **/
		keyup, 

		// Mouse Events
		// Events triggered by a mouse, or similar user actions:

		/** Fires on a mouse click on the element **/
		click, 
		
		/** Fires on a mouse double-click on the element **/
		dblclick,
		
		/** Script to be run when an element is dragged **/
		drag, 
		
		/** Script to be run at the end of a drag operation **/
		dragend, 
		
		/** Script to be run when an element has been dragged to a valid drop target **/
		dragenter, 
		
		/** Script to be run when an element leaves a valid drop target **/
		dragleave, 
		
		/** Script to be run when an element is being dragged over a valid drop target **/
		dragover, 
		
		/** Script to be run at the start of a drag operation **/
		dragstart, 
		
		/** Script to be run when dragged element is being dropped **/
		drop, 
		
		/** Fires when a mouse button is pressed down on an element **/
		mousedown, 
		
		/** Fires when the mouse pointer moves over an element **/
		mousemove, 
		
		/** Fires when the mouse pointer moves out of an element **/
		mouseout, 
		
		/** Fires when the mouse pointer moves over an element **/
		mouseover, 
		
		/** Fires when a mouse button is released over an element **/
		mouseup, 
		
		/** Script to be run when the mouse wheel is being rotated **/
		mousewheel, 
		
		/** Script to be run when an element's scrollbar is being scrolled **/
		scroll 		
	}

	/**
	 * 
	 * @param event Event type.
	 * @param handler Handler script. 
	 * @return
	 */
	T on(Event event, Object handler);

	/**
	 * 
	 * @param event Event name without 'on' prefix, e.g. 'click' for 'onclick' handler
	 * @param handler Handler script.
	 * @return
	 */
	T on(String event, Object handler);

	/**
	 * 
	 * @param event Event type.
	 * @param handler Handler script.
	 * @return
	 */
	T on(Event event, Reader handler) throws IOException;

	/**
	 * 
	 * @param event Event name without 'on' prefix, e.g. 'click' for 'onclick' handler
	 * @param handler Handler script. 
	 * @return
	 */
	T on(String event, Reader handler) throws IOException;

	/**
	 * 
	 * @param event Event type.
	 * @param handler Handler script. 
	 * @return
	 */
	T on(Event event, InputStream handler) throws IOException;

	/**
	 * 
	 * @param event Event name without 'on' prefix, e.g. 'click' for 'onclick' handler
	 * @param handler Handler script. 
	 * @return
	 */
	T on(String event, InputStream handler) throws IOException;
		
	/**
	 * Sets element id.
	 * @param id
	 * @return
	 */
	T id(Object id);
	
	Object getId();
	
	/**
	 * Sets element attribute
	 * @param name  if name is 'id' then value replaces value set through id() and vice versa. If attribute name is 'style' then
	 * this definition is merged with styles defined through style() method.
	 * @param value Attribute value. This method HTML-escapes the value, e.g. replaces " with &quot;
	 * @return
	 * 
	 */
	T attribute(String name, Object value);
	
	String getAttribute(String name);
	
	/**
	 * Sets style attribute
	 * @param name
	 * @param value
	 * @return
	 */
	T style(String name, Object value);
	
	Style<T> style();
	
	/**
	 * Adds class definition.
	 * @param clazz
	 * @return
	 */
	T addClass(Object... clazz);

	/**
	 * If this method is invoked, then remote content at <code>href</code>
	 * is loaded to the UI element by <code>nsdLoad</code> function.
	 * @param href
	 * @return
	 */
	T remoteContent(Object href);
	
	Angular<T> angular();
	
	FontAwesome<T> fontAwesome();
	
	/**
	 * @return Knockout interface instance to manipulate Knockout.js binding.
	 */
	Knockout<T> knockout();
	
	// Grid<T> grid();
	
	T comment(String comment);
	
	Bootstrap<T> bootstrap();
	
	/**
	 * 
	 * @return Factory used to create this element.
	 */
	HTMLFactory getFactory();	
	
}
