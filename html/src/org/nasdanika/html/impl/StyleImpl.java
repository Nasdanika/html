package org.nasdanika.html.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Style;

class StyleImpl<T extends HTMLElement<T>> implements Style<T> {
	
	/**
	 * Helper base class
	 * @author Pavel Vlasov
	 *
	 * @param <T>
	 */
	private class NormalNoneInitialInheritImpl implements NormalInitialInherit<T>, NoneInitialInherit<T> {
		
		private String styleName;

		NormalNoneInitialInheritImpl(String styleName) {
			this.styleName = styleName;
		}
		
		protected T value(Object value) {
			return StyleImpl.this.style(styleName, value);
		}

		@Override
		public T initial() {
			return value("initial");
		}

		@Override
		public T inherit() {
			return value("inherit");
		}

		@Override
		public T normal() {
			return value("normal");
		}

		@Override
		public T none() {
			return value("none");
		}		
		
	}
		
	private T owner;

	public StyleImpl(T owner) {
		this.owner = owner;
	}
	
	protected T style(String name, Object value) {
		return owner.style(name, value);
	}

	@Override
	public T width(Object width) {
		return style("width", width);
	}

	@Override
	public T height(Object height) {
		return style("height", height);
	}

	@Override
	public Font<T> font() {
		 
		return new Font<T>() {

			@Override
			public T size(Object size) {
				return StyleImpl.this.style("font-size", size);
			}

			@Override
			public T family(Object family) {
				return StyleImpl.this.style("font-family", family);
			}

			@Override
			public FontStyle<T> style() {
				class FontStyleImpl extends NormalNoneInitialInheritImpl implements FontStyle<T> {

					FontStyleImpl() {
						super("font-style");
					}

					@Override
					public T italic() {
						return value("italic");
					}

					@Override
					public T oblique() {
						return value("oblique");
					}
					
				}

				return new FontStyleImpl();	
			}

			@Override
			public FontVariant<T> variant() {
				class FontVariantImpl extends NormalNoneInitialInheritImpl implements FontVariant<T> {

					FontVariantImpl() {
						super("font-variant");
					}

					@Override
					public T smallCaps() {
						return value("small-caps");
					}

					
				}

				return new FontVariantImpl();	
			}

			@Override
			public FontWeight<T> weight() {
				class FontWeightImpl extends NormalNoneInitialInheritImpl implements FontWeight<T> {

					FontWeightImpl() {
						super("font-weight");
					}

					@Override
					public T bold() {
						return value("bold");
					}

					@Override
					public T bolder() {
						return value("bolder");
					}

					@Override
					public T lighter() {
						return value("lighter");
					}

					@Override
					public T number(Object weight) {
						return value(weight);
					}
					
				}

				return new FontWeightImpl();	
			}
			
		};
	}
	
	private class BoxImpl implements Box<T> {
		
		private String category;

		public BoxImpl(String category) {
			this.category = category;
		}

		@Override
		public T left(Object spec) {
			return style(category+"-left", spec);
		}

		@Override
		public T right(Object spec) {
			return style(category+"-right", spec);
		}

		@Override
		public T top(Object spec) {
			return style(category+"-top", spec);
		}

		@Override
		public T bottom(Object spec) {
			return style(category+"-bottom", spec);
		}
		
		
	}

	@Override
	public T margin(Object spec) {
		return style("margin", spec);
	}
	
	private Box<T> marginBox = new BoxImpl("margin");

	@Override
	public Box<T> margin() {
		return marginBox;
	}

	@Override
	public T padding(Object spec) {
		return style("padding", spec);
	}

	private Box<T> paddingBox = new BoxImpl("padding");

	@Override
	public Box<T> padding() {
		return paddingBox;
	}

	@Override
	public T border(Object spec) {
		return style("border", spec);
	}

	private Box<T> borderBox = new BoxImpl("border");

	@Override
	public Box<T> border() {
		return borderBox;
	}

	@Override
	public Text<T> text() {
		return new Text<T>() {

			@Override
			public TextAlign<T> align() {
				class TextAlignImpl extends NormalNoneInitialInheritImpl implements TextAlign<T> {

					TextAlignImpl() {
						super("text-align");
					}

					@Override
					public T left() {
						return value("left");
					}

					@Override
					public T right() {
						return value("right");
					}

					@Override
					public T center() {
						return value("center");
					}

					@Override
					public T justify() {
						return value("justify");
					}
					
				}

				return new TextAlignImpl();	
			}

			@Override
			public TextDecoration<T> decoration() {
				class TextDecorationImpl extends NormalNoneInitialInheritImpl implements TextDecoration<T> {

					TextDecorationImpl() {
						super("text-decoration");
					}

					@Override
					public T underline() {
						return value("underline");
					}

					@Override
					public T overline() {
						return value("overline");
					}

					@Override
					public T lineThrough() {
						return value("line-through");
					}
					
				}

				return new TextDecorationImpl();	
			}

			@Override
			public TextJustify<T> justify() {
				class TextJustifyImpl extends NormalNoneInitialInheritImpl implements TextJustify<T> {

					TextJustifyImpl() {
						super("text-justify");
					}

					@Override
					public T auto() {
						return value("auto");
					}

					@Override
					public T interWord() {
						return value("inter-word");
					}

					@Override
					public T interIdeograph() {
						return value("inder-ideograph");
					}

					@Override
					public T interCluster() {
						return value("inter-cluster");
					}

					@Override
					public T distribute() {
						return value("distribute");
					}

					@Override
					public T kashida() {
						return value("kashida");
					}

					@Override
					public T trim() {
						return value("trim");
					}
					
				}

				return new TextJustifyImpl();	
			}

			@Override
			public TextOverflow<T> overflow() {
				class TextOverflowImpl extends NormalNoneInitialInheritImpl implements TextOverflow<T> {

					TextOverflowImpl() {
						super("text-overflow");
					}

					@Override
					public T clip() {
						return value("clip");
					}

					@Override
					public T ellipsis() {
						return value("ellipsis");
					}

					@Override
					public T string(Object value) {
						return value(value);
					}
					
				}

				return new TextOverflowImpl();	
			}
			
		};
	};
	
	class ColorImpl extends NormalNoneInitialInheritImpl implements Color<T> {
		
		public ColorImpl() {
			this("color");
		}

		protected ColorImpl(String name) {
			super(name);
		}

		@Override
		public T value(Object color) {
			return super.value(color);
		}

		@Override
		public T color(org.nasdanika.html.Color color) {
			return super.value(color.name());
		}
		
	}
		
	@Override
	public Color<T> color() {
		return new ColorImpl();	
	}
	
	@Override
	public Background<T> background() {
		class BackgroundImpl extends NormalNoneInitialInheritImpl implements Background<T> {

			BackgroundImpl() {
				super("background");
			}

			@Override
			public BackgroundColor<T> color() {
				class BackgroundColorImpl extends ColorImpl implements Background.BackgroundColor<T> {
					
					public BackgroundColorImpl() {
						super("background-color");
					}

					@Override
					public T transparent() {
						return value("transparent");
					}
					
				}
				
				return new BackgroundColorImpl();
			}
			
		}
		return new BackgroundImpl();
	}
	
	@Override
	public T background(Object spec) {
		return style("background", spec);
	}
	
	@Override
	public Display<T> display() {
		class DisplayImpl extends NormalNoneInitialInheritImpl implements Display<T> {

			DisplayImpl() {
				super("display");
			}

			@Override
			public T inline() {
				return value("inline");
			}

			@Override
			public T block() {
				return value("block");
			}

			@Override
			public T flex() {
				return value("flex");
			}

			@Override
			public T inlineBlock() {
				return value("inline-block");
			}

			@Override
			public T inlineFlex() {
				return value("inline-flex");
			}

			@Override
			public T inlineTable() {
				return value("inline-table");
			}

			@Override
			public T listItem() {
				return value("list-item");
			}

			@Override
			public T runIn() {
				return value("run-in");
			}

			@Override
			public T table() {
				return value("table");
			}

			@Override
			public T tableCaption() {
				return value("table-caption");
			}

			@Override
			public T tableColumnGroup() {
				return value("table-column-group");
			}

			@Override
			public T tableHeaderGroup() {
				return value("table-header-group");
			}

			@Override
			public T tableFooterGroup() {
				return value("table-footer-group");
			}

			@Override
			public T tableRowGroup() {
				return value("table-row-group");
			}

			@Override
			public T tableCell() {
				return value("table-cell");
			}

			@Override
			public T tableColumn() {
				return value("table-column");
			}

			@Override
			public T tableRow() {
				return value("table-row");
			}
			
		}
		return new DisplayImpl();
	}
	
	@Override
	public Float<T> float_() {
		class FloatImpl extends NormalNoneInitialInheritImpl implements Float<T> {

			FloatImpl() {
				super("float");
			}

			@Override
			public T left() {
				return value("left");
			}

			@Override
			public T right() {
				return value("right");
			}
			
		}
		return new FloatImpl();
	}
	
	@Override
	public org.nasdanika.html.Style.WhiteSpace<T> whiteSpace() {
		class WhiteSpaceImpl extends NormalNoneInitialInheritImpl implements WhiteSpace<T> {

			WhiteSpaceImpl() {
				super("white-space");
			}

			@Override
			public T nowrap() {
				return value("nowrap");
			}

			@Override
			public T pre() {
				return value("pre");
			}

			@Override
			public T preLine() {
				return value("pre-line");
			}

			@Override
			public T preWrap() {
				return value("pre-wrap");
			}

			
		}
		return new WhiteSpaceImpl();
	}
		

}
