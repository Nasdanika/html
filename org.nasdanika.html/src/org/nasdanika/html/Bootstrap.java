package org.nasdanika.html;

/**
 * Helper interface for setting Bootstrap classes.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface Bootstrap<T> {
	
	/**
	 * @version 3.7.7
	 * @author Pavel
	 *
	 */
	enum Glyphicon {				
		asterisk("asterisk"),
		plus("plus"),
		euro("euro"),
		minus("minus"),
		cloud("cloud"),
		envelope("envelope"),
		pencil("pencil"),
		glass("glass"),
		music("music"),
		search("search"),
		heart("heart"),
		star("star"),
		star_empty("star-empty"),
		user("user"),
		film("film"),
		th_large("th-large"),
		th("th"),
		th_list("th-list"),
		ok("ok"),
		remove("remove"),
		zoom_in("zoom-in"),
		zoom_out("zoom-out"),
		off("off"),
		signal("signal"),
		cog("cog"),
		trash("trash"),
		home("home"),
		file("file"),
		time("time"),
		road("road"),
		download_alt("download-alt"),
		download("download"),
		upload("upload"),
		inbox("inbox"),
		play_circle("play-circle"),
		repeat("repeat"),
		refresh("refresh"),
		list_alt("list-alt"),
		lock("lock"),
		flag("flag"),
		headphones("headphones"),
		volume_off("volume-off"),
		volume_down("volume-down"),
		volume_up("volume-up"),
		qrcode("qrcode"),
		barcode("barcode"),
		tag("tag"),
		tags("tags"),
		book("book"),
		bookmark("bookmark"),
		print("print"),
		camera("camera"),
		font("font"),
		bold("bold"),
		italic("italic"),
		text_height("text-height"),
		text_width("text-width"),
		align_left("align-left"),
		align_center("align-center"),
		align_right("align-right"),
		align_justify("align-justify"),
		list("list"),
		indent_left("indent-left"),
		indent_right("indent-right"),
		facetime_video("facetime-video"),
		picture("picture"),
		map_marker("map-marker"),
		adjust("adjust"),
		tint("tint"),
		edit("edit"),
		share("share"),
		check("check"),
		move("move"),
		step_backward("step-backward"),
		fast_backward("fast-backward"),
		backward("backward"),
		play("play"),
		pause("pause"),
		stop("stop"),
		forward("forward"),
		fast_forward("fast-forward"),
		step_forward("step-forward"),
		eject("eject"),
		chevron_left("chevron-left"),
		chevron_right("chevron-right"),
		plus_sign("plus-sign"),
		minus_sign("minus-sign"),
		remove_sign("remove-sign"),
		ok_sign("ok-sign"),
		question_sign("question-sign"),
		info_sign("info-sign"),
		screenshot("screenshot"),
		remove_circle("remove-circle"),
		ok_circle("ok-circle"),
		ban_circle("ban-circle"),
		arrow_left("arrow-left"),
		arrow_right("arrow-right"),
		arrow_up("arrow-up"),
		arrow_down("arrow-down"),
		share_alt("share-alt"),
		resize_full("resize-full"),
		resize_small("resize-small"),
		exclamation_sign("exclamation-sign"),
		gift("gift"),
		leaf("leaf"),
		fire("fire"),
		eye_open("eye-open"),
		eye_close("eye-close"),
		warning_sign("warning-sign"),
		plane("plane"),
		calendar("calendar"),
		random("random"),
		comment("comment"),
		magnet("magnet"),
		chevron_up("chevron-up"),
		chevron_down("chevron-down"),
		retweet("retweet"),
		shopping_cart("shopping-cart"),
		folder_close("folder-close"),
		folder_open("folder-open"),
		resize_vertical("resize-vertical"),
		resize_horizontal("resize-horizontal"),
		hdd("hdd"),
		bullhorn("bullhorn"),
		bell("bell"),
		certificate("certificate"),
		thumbs_up("thumbs-up"),
		thumbs_down("thumbs-down"),
		hand_right("hand-right"),
		hand_left("hand-left"),
		hand_up("hand-up"),
		hand_down("hand-down"),
		circle_arrow_right("circle-arrow-right"),
		circle_arrow_left("circle-arrow-left"),
		circle_arrow_up("circle-arrow-up"),
		circle_arrow_down("circle-arrow-down"),
		globe("globe"),
		wrench("wrench"),
		tasks("tasks"),
		filter("filter"),
		briefcase("briefcase"),
		fullscreen("fullscreen"),
		dashboard("dashboard"),
		paperclip("paperclip"),
		heart_empty("heart-empty"),
		link("link"),
		phone("phone"),
		pushpin("pushpin"),
		usd("usd"),
		gbp("gbp"),
		sort("sort"),
		sort_by_alphabet("sort-by-alphabet"),
		sort_by_alphabet_alt("sort-by-alphabet-alt"),
		sort_by_order("sort-by-order"),
		sort_by_order_alt("sort-by-order-alt"),
		sort_by_attributes("sort-by-attributes"),
		sort_by_attributes_alt("sort-by-attributes-alt"),
		unchecked("unchecked"),
		expand("expand"),
		collapse_down("collapse-down"),
		collapse_up("collapse-up"),
		log_in("log-in"),
		flash("flash"),
		log_out("log-out"),
		new_window("new-window"),
		record("record"),
		save("save"),
		open("open"),
		saved("saved"),
		_import("import"),
		export("export"),
		send("send"),
		floppy_disk("floppy-disk"),
		floppy_saved("floppy-saved"),
		floppy_remove("floppy-remove"),
		floppy_save("floppy-save"),
		floppy_open("floppy-open"),
		credit_card("credit-card"),
		transfer("transfer"),
		cutlery("cutlery"),
		header("header"),
		compressed("compressed"),
		earphone("earphone"),
		phone_alt("phone-alt"),
		tower("tower"),
		stats("stats"),
		sd_video("sd-video"),
		hd_video("hd-video"),
		subtitles("subtitles"),
		sound_stereo("sound-stereo"),
		sound_dolby("sound-dolby"),
		sound_5_1("sound-5-1"),
		sound_6_1("sound-6-1"),
		sound_7_1("sound-7-1"),
		copyright_mark("copyright-mark"),
		registration_mark("registration-mark"),
		cloud_download("cloud-download"),
		cloud_upload("cloud-upload"),
		tree_conifer("tree-conifer"),
		tree_deciduous("tree-deciduous"),
		cd("cd"),
		save_file("save-file"),
		open_file("open-file"),
		level_up("level-up"),
		copy("copy"),
		paste("paste"),
		alert("alert"),
		equalizer("equalizer"),
		king("king"),
		queen("queen"),
		pawn("pawn"),
		bishop("bishop"),
		knight("knight"),
		baby_formula("baby-formula"),
		tent("tent"),
		blackboard("blackboard"),
		bed("bed"),
		apple("apple"),
		erase("erase"),
		hourglass("hourglass"),
		lamp("lamp"),
		duplicate("duplicate"),
		piggy_bank("piggy-bank"),
		scissors("scissors"),
		bitcoin("bitcoin"),
		btc("btc"),
		xbt("xbt"),
		yen("yen"),
		jpy("jpy"),
		ruble("ruble"),
		rub("rub"),
		scale("scale"),
		ice_lolly("ice-lolly"),
		ice_lolly_tasted("ice-lolly-tasted"),
		education("education"),
		option_horizontal("option-horizontal"),
		option_vertical("option-vertical"),
		menu_hamburger("menu-hamburger"),
		modal_window("modal-window"),
		oil("oil"),
		grain("grain"),
		sunglasses("sunglasses"),
		text_size("text-size"),
		text_color("text-color"),
		text_background("text-background"),
		object_align_top("object-align-top"),
		object_align_bottom("object-align-bottom"),
		object_align_horizontal("object-align-horizontal"),
		object_align_left("object-align-left"),
		object_align_vertical("object-align-vertical"),
		object_align_right("object-align-right"),
		triangle_right("triangle-right"),
		triangle_left("triangle-left"),
		triangle_bottom("triangle-bottom"),
		triangle_top("triangle-top"),
		console("console"),
		superscript("superscript"),
		subscript("subscript"),
		menu_left("menu-left"),
		menu_right("menu-right"),
		menu_down("menu-down"),
		menu_up("menu-up");
		
		private String code;
		
		private Glyphicon(String code) {
			this.code = code;
		}
		
		public String code() {
			return code;
		}
	}	

	enum Style { 
		DEFAULT, 
		PRIMARY, 
		SUCCESS, 
		INFO, 
		WARNING, 
		DANGER,
		/**
		 * Applies only to text color
		 */
		MUTED 
	}

	enum Size { 
		EXTRA_SMALL("xs"), 
		SMALL("sm"), 
		DEFAULT(null), 
		LARGE("lg");
	
		public final String code;
		Size(String code) {
			this.code = code;
		}		
	}

	/**
	 * Bootstrap colors
	 * @author Pavel
	 *
	 */
	enum Color { 
		
		GRAY_DARKER("#222"),
		GRAY_DARK("#333"),
		GRAY("#555"),
		GRAY_LIGHT("#999"),
		GRAY_LIGHTER("#eee"),
	
		PRIMARY("#428bca"),
		SUCCESS("#5cb85c"),
		INFO("#5bc0de"),
		WARNING("#f0ad4e"),
		DANGER("#d9534f"),
		MUTED("#777");
		
		public final String code;
		Color(String code) {
			this.code = code;
		}
	}

	enum DeviceSize { 
		EXTRA_SMALL("xs"), 
		SMALL("sm"), 
		MEDIUM("md"), 
		LARGE("lg"); 
		
		public final String code;
	
		private DeviceSize(String code) {
			this.code = code;
		}
	}
	
	interface Grid<T> extends AutoCloseable {
		
		T container();
		T fluidContainer();
		T row();
		T col(Bootstrap.DeviceSize deviceSize, int width);
		
		/**
		 * Sets width for all device sizes
		 * @param width
		 * @return
		 */
		T col(int width);
		
		T colOffset(Bootstrap.DeviceSize deviceSize, int width);
		T colOffset(int width);

		T colPush(Bootstrap.DeviceSize deviceSize, int width);
		T colPush(int width);

		T colPull(Bootstrap.DeviceSize deviceSize, int width);
		T colPull(int width);

	}	
	
	Grid<T> grid();
	
	T background(Style style);
	
	T pullLeft();
	
	T pullRight();
	
	T centerBlock();
	
	T clearfix();
	
	interface Text<T> {
		
		T color(Style style);

		T left();
		
		T center();
		
		T right();
		
		T justify();
		
		T nowrap();
		
		T lowercase();
		
		T uppercase();
		
		T capitalize();		
		
	}
	
	Text<T> text();
	
	T show();
	
	T hidden();
	
	T visibleBlock(DeviceSize deviceSize);
	
	T visibleInline(DeviceSize deviceSize);
	
	T visibleInlineBlock(DeviceSize deviceSize);
	
	T hidden(DeviceSize deviceSize);

}
