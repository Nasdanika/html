package org.nasdanika.html;

/**
 * Helper interface for setting Bootstrap classes.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface Bootstrap<T> {
	
	enum Glyphicon {
		asterisk,
		plus,
		euro,
		minus,
		cloud,
		envelope,
		pencil,
		glass,
		music,
		search,
		heart,
		star,
		star_empty,
		user,
		film,
		th_large,
		th,
		th_list,
		ok,
		remove,
		zoom_in,
		zoom_out,
		off,
		signal,
		cog,
		trash,
		home,
		file,
		time,
		road,
		download_alt,
		download,
		upload,
		inbox,
		play_circle,
		repeat,
		refresh,
		list_alt,
		lock,
		flag,
		headphones,
		volume_off,
		volume_down,
		volume_up,
		qrcode,
		barcode,
		tag,
		tags,
		book,
		bookmark,
		print,
		camera,
		font,
		bold,
		italic,
		text_height,
		text_width,
		align_left,
		align_center,
		align_right,
		align_justify,
		list,
		indent_left,
		indent_right,
		facetime_video,
		picture,
		map_marker,
		adjust,
		tint,
		edit,
		share,
		check,
		move,
		step_backward,
		fast_backward,
		backward,
		play,
		pause,
		stop,
		forward,
		fast_forward,
		step_forward,
		eject,
		chevron_left,
		chevron_right,
		plus_sign,
		minus_sign,
		remove_sign,
		ok_sign,
		question_sign,
		info_sign,
		screenshot,
		remove_circle,
		ok_circle,
		ban_circle,
		arrow_left,
		arrow_right,
		arrow_up,
		arrow_down,
		share_alt,
		resize_full,
		resize_small,
		exclamation_sign,
		gift,
		leaf,
		fire,
		eye_open,
		eye_close,
		warning_sign,
		plane,
		calendar,
		random,
		comment,
		magnet,
		chevron_up,
		chevron_down,
		retweet,
		shopping_cart,
		folder_close,
		folder_open,
		resize_vertical,
		resize_horizontal,
		hdd,
		bullhorn,
		bell,
		certificate,
		thumbs_up,
		thumbs_down,
		hand_right,
		hand_left,
		hand_up,
		hand_down,
		circle_arrow_right,
		circle_arrow_left,
		circle_arrow_up,
		circle_arrow_down,
		globe,
		wrench,
		tasks,
		filter,
		briefcase,
		fullscreen,
		dashboard,
		paperclip,
		heart_empty,
		link,
		phone,
		pushpin,
		usd,
		gbp,
		sort,
		sort_by_alphabet,
		sort_by_alphabet_alt,
		sort_by_order,
		sort_by_order_alt,
		sort_by_attributes,
		sort_by_attributes_alt,
		unchecked,
		expand,
		collapse_down,
		collapse_up,
		log_in,
		flash,
		log_out,
		new_window,
		record,
		save,
		open,
		saved,
		import_icon,
		export,
		send,
		floppy_disk,
		floppy_saved,
		floppy_remove,
		floppy_save,
		floppy_open,
		credit_card,
		transfer,
		cutlery,
		header,
		compressed,
		earphone,
		phone_alt,
		tower,
		stats,
		sd_video,
		hd_video,
		subtitles,
		sound_stereo,
		sound_dolby,
		sound_5_1,
		sound_6_1,
		sound_7_1,
		copyright_mark,
		registration_mark,
		cloud_download,
		cloud_upload,
		tree_conifer,
		tree_deciduous;
		
		public String code() {
			if ("import_icon".equals(name())) {
				return "import";
			}
			return name().replace("_", "-");
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
