package org.nasdanika.html;

/**
 * Enumeration for frequently used HTML events
 * @author Pavel
 *
 */
public enum Event {
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