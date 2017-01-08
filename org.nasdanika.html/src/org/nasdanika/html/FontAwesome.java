package org.nasdanika.html;

/**
 * Interface for creating Font Awesome (http://fortawesome.github.io/Font-Awesome/) styled UI elements.
 * @author Pavel Vlasov
 *
 */
public interface FontAwesome<T extends UIElement<?>> {
	
	String VERSION = "4.7.0";
	
	FontAwesome<T> custom(String custom);

	enum Accessibility {
		
		wheelchair("wheelchair"), tty("tty"), cc("cc"), universal_access("universal-access"), wheelchair_alt("wheelchair-alt"), question_circle_o("question-circle-o"), blind("blind"), audio_description("audio-description"), volume_control_phone("volume-control-phone"), braille("braille"), assistive_listening_systems("assistive-listening-systems"), american_sign_language_interpreting("american-sign-language-interpreting"), deaf("deaf"), sign_language("sign-language"), low_vision("low-vision");
		
		public final String literal;
		
		private Accessibility(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> accessibility(Accessibility accessibility);		
	
	enum Brand {
		
		twitter_square("twitter-square"), facebook_square("facebook-square"), linkedin_square("linkedin-square"), github_square("github-square"), twitter("twitter"), facebook("facebook"), github("github"), pinterest("pinterest"), pinterest_square("pinterest-square"), google_plus_square("google-plus-square"), google_plus("google-plus"), linkedin("linkedin"), github_alt("github-alt"), maxcdn("maxcdn"), html5("html5"), css3("css3"), btc("btc"), youtube_square("youtube-square"), youtube("youtube"), xing("xing"), xing_square("xing-square"), youtube_play("youtube-play"), dropbox("dropbox"), stack_overflow("stack-overflow"), instagram("instagram"), flickr("flickr"), adn("adn"), bitbucket("bitbucket"), bitbucket_square("bitbucket-square"), tumblr("tumblr"), tumblr_square("tumblr-square"), apple("apple"), windows("windows"), android("android"), linux("linux"), dribbble("dribbble"), skype("skype"), foursquare("foursquare"), trello("trello"), gratipay("gratipay"), vk("vk"), weibo("weibo"), renren("renren"), pagelines("pagelines"), stack_exchange("stack-exchange"), vimeo_square("vimeo-square"), slack("slack"), wordpress("wordpress"), openid("openid"), yahoo("yahoo"), google("google"), reddit("reddit"), reddit_square("reddit-square"), stumbleupon_circle("stumbleupon-circle"), stumbleupon("stumbleupon"), delicious("delicious"), digg("digg"), pied_piper_pp("pied-piper-pp"), pied_piper_alt("pied-piper-alt"), drupal("drupal"), joomla("joomla"), behance("behance"), behance_square("behance-square"), steam("steam"), steam_square("steam-square"), spotify("spotify"), deviantart("deviantart"), soundcloud("soundcloud"), vine("vine"), codepen("codepen"), jsfiddle("jsfiddle"), rebel("rebel"), empire("empire"), git_square("git-square"), git("git"), hacker_news("hacker-news"), tencent_weibo("tencent-weibo"), qq("qq"), weixin("weixin"), share_alt("share-alt"), share_alt_square("share-alt-square"), slideshare("slideshare"), twitch("twitch"), yelp("yelp"), paypal("paypal"), google_wallet("google-wallet"), cc_visa("cc-visa"), cc_mastercard("cc-mastercard"), cc_discover("cc-discover"), cc_amex("cc-amex"), cc_paypal("cc-paypal"), cc_stripe("cc-stripe"), lastfm("lastfm"), lastfm_square("lastfm-square"), ioxhost("ioxhost"), angellist("angellist"), meanpath("meanpath"), buysellads("buysellads"), connectdevelop("connectdevelop"), dashcube("dashcube"), forumbee("forumbee"), leanpub("leanpub"), sellsy("sellsy"), shirtsinbulk("shirtsinbulk"), simplybuilt("simplybuilt"), skyatlas("skyatlas"), facebook_official("facebook-official"), pinterest_p("pinterest-p"), whatsapp("whatsapp"), viacoin("viacoin"), medium("medium"), y_combinator("y-combinator"), optin_monster("optin-monster"), opencart("opencart"), expeditedssl("expeditedssl"), cc_jcb("cc-jcb"), cc_diners_club("cc-diners-club"), gg("gg"), gg_circle("gg-circle"), tripadvisor("tripadvisor"), odnoklassniki("odnoklassniki"), odnoklassniki_square("odnoklassniki-square"), get_pocket("get-pocket"), wikipedia_w("wikipedia-w"), safari("safari"), chrome("chrome"), firefox("firefox"), opera("opera"), internet_explorer("internet-explorer"), contao("contao"), _500px("500px"), amazon("amazon"), houzz("houzz"), vimeo("vimeo"), black_tie("black-tie"), fonticons("fonticons"), reddit_alien("reddit-alien"), edge("edge"), codiepie("codiepie"), modx("modx"), fort_awesome("fort-awesome"), usb("usb"), product_hunt("product-hunt"), mixcloud("mixcloud"), scribd("scribd"), bluetooth("bluetooth"), bluetooth_b("bluetooth-b"), gitlab("gitlab"), wpbeginner("wpbeginner"), wpforms("wpforms"), envira("envira"), glide("glide"), glide_g("glide-g"), viadeo("viadeo"), viadeo_square("viadeo-square"), snapchat("snapchat"), snapchat_ghost("snapchat-ghost"), snapchat_square("snapchat-square"), pied_piper("pied-piper"), first_order("first-order"), yoast("yoast"), themeisle("themeisle"), google_plus_official("google-plus-official"), font_awesome("font-awesome"), linode("linode"), quora("quora"), free_code_camp("free-code-camp"), telegram("telegram"), bandcamp("bandcamp"), grav("grav"), etsy("etsy"), imdb("imdb"), ravelry("ravelry"), eercast("eercast"), superpowers("superpowers"), wpexplorer("wpexplorer"), meetup("meetup");
				
		public final String literal;
		
		private Brand(String literal) {
			this.literal = literal;
		}		
		
	}
	
	FontAwesome<T> brand(Brand brand);		
	
	enum Chart {
		
		bar_chart("bar-chart"), area_chart("area-chart"), pie_chart("pie-chart"), line_chart("line-chart"); 
		
		public final String literal;
		
		private Chart(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> chart(Chart chart);			
	
	enum Currency {
		
		money("money"), eur("eur"), gbp("gbp"), usd("usd"), inr("inr"), jpy("jpy"), rub("rub"), krw("krw"), btc("btc"), _try("try"), ils("ils"), gg("gg"), gg_circle("gg-circle"); 
		
		public final String literal;
		
		private Currency(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> currency(Currency currency);				
	
	enum Directional {
		
		arrow_circle_o_down("arrow-circle-o-down"), arrow_circle_o_up("arrow-circle-o-up"), arrows("arrows"), chevron_left("chevron-left"), chevron_right("chevron-right"), arrow_left("arrow-left"), arrow_right("arrow-right"), arrow_up("arrow-up"), arrow_down("arrow-down"), chevron_up("chevron-up"), chevron_down("chevron-down"), arrows_v("arrows-v"), arrows_h("arrows-h"), hand_o_right("hand-o-right"), hand_o_left("hand-o-left"), hand_o_up("hand-o-up"), hand_o_down("hand-o-down"), arrow_circle_left("arrow-circle-left"), arrow_circle_right("arrow-circle-right"), arrow_circle_up("arrow-circle-up"), arrow_circle_down("arrow-circle-down"), arrows_alt("arrows-alt"), caret_down("caret-down"), caret_up("caret-up"), caret_left("caret-left"), caret_right("caret-right"), exchange("exchange"), angle_double_left("angle-double-left"), angle_double_right("angle-double-right"), angle_double_up("angle-double-up"), angle_double_down("angle-double-down"), angle_left("angle-left"), angle_right("angle-right"), angle_up("angle-up"), angle_down("angle-down"), chevron_circle_left("chevron-circle-left"), chevron_circle_right("chevron-circle-right"), chevron_circle_up("chevron-circle-up"), chevron_circle_down("chevron-circle-down"), caret_square_o_down("caret-square-o-down"), caret_square_o_up("caret-square-o-up"), caret_square_o_right("caret-square-o-right"), long_arrow_down("long-arrow-down"), long_arrow_up("long-arrow-up"), long_arrow_left("long-arrow-left"), long_arrow_right("long-arrow-right"), arrow_circle_o_right("arrow-circle-o-right"), arrow_circle_o_left("arrow-circle-o-left"), caret_square_o_left("caret-square-o-left"); 
		
		public final String literal;
		
		private Directional(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> directional(Directional directional);			
	
	enum FileType {
		
		file_o("file-o"), file_text_o("file-text-o"), file("file"), file_text("file-text"), file_pdf_o("file-pdf-o"), file_word_o("file-word-o"), file_excel_o("file-excel-o"), file_powerpoint_o("file-powerpoint-o"), file_image_o("file-image-o"), file_archive_o("file-archive-o"), file_audio_o("file-audio-o"), file_video_o("file-video-o"), file_code_o("file-code-o"); 
		
		public final String literal;
		
		private FileType(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> fileType(FileType fileType);		
	
	enum FormControl {
		
		check_square_o("check-square-o"), square_o("square-o"), square("square"), plus_square("plus-square"), circle_o("circle-o"), circle("circle"), minus_square("minus-square"), minus_square_o("minus-square-o"), check_square("check-square"), dot_circle_o("dot-circle-o"), plus_square_o("plus-square-o"); 
		
		public final String literal;
		
		private FormControl(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> formControl(FormControl formControl);		
	
	enum Gender {
		
		venus("venus"), mars("mars"), mercury("mercury"), transgender("transgender"), transgender_alt("transgender-alt"), venus_double("venus-double"), mars_double("mars-double"), venus_mars("venus-mars"), mars_stroke("mars-stroke"), mars_stroke_v("mars-stroke-v"), mars_stroke_h("mars-stroke-h"), neuter("neuter"), genderless("genderless"); 
		
		public final String literal;
		
		private Gender(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> gender(Gender gender);			
	
	enum Hand {
		
		thumbs_o_up("thumbs-o-up"), thumbs_o_down("thumbs-o-down"), hand_o_right("hand-o-right"), hand_o_left("hand-o-left"), hand_o_up("hand-o-up"), hand_o_down("hand-o-down"), thumbs_up("thumbs-up"), thumbs_down("thumbs-down"), hand_rock_o("hand-rock-o"), hand_paper_o("hand-paper-o"), hand_scissors_o("hand-scissors-o"), hand_lizard_o("hand-lizard-o"), hand_spock_o("hand-spock-o"), hand_pointer_o("hand-pointer-o"), hand_peace_o("hand-peace-o"); 
		
		public final String literal;
		
		private Hand(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> hand(Hand hand);		
	
	
	enum Medical {
		
		heart("heart"), heart_o("heart-o"), user_md("user-md"), stethoscope("stethoscope"), hospital_o("hospital-o"), ambulance("ambulance"), medkit("medkit"), h_square("h-square"), plus_square("plus-square"), wheelchair("wheelchair"), heartbeat("heartbeat"), wheelchair_alt("wheelchair-alt"); 
		
		public final String literal;
		
		private Medical(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> medical(Medical medical);		
	
	enum Payment {
		
		credit_card("credit-card"), paypal("paypal"), google_wallet("google-wallet"), cc_visa("cc-visa"), cc_mastercard("cc-mastercard"), cc_discover("cc-discover"), cc_amex("cc-amex"), cc_paypal("cc-paypal"), cc_stripe("cc-stripe"), cc_jcb("cc-jcb"), cc_diners_club("cc-diners-club"), credit_card_alt("credit-card-alt"); 
		
		public final String literal;
		
		private Payment(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> payment(Payment payment);		
	
	enum Spinner {
		
		cog("cog"), refresh("refresh"), spinner("spinner"), circle_o_notch("circle-o-notch"); 
		
		public final String literal;
		
		private Spinner(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> spinner(Spinner spinner);		
	
	enum TextEditor {
		
		th_large("th-large"), th("th"), th_list("th-list"), file_o("file-o"), repeat("repeat"), list_alt("list-alt"), font("font"), bold("bold"), italic("italic"), text_height("text-height"), text_width("text-width"), align_left("align-left"), align_center("align-center"), align_right("align-right"), align_justify("align-justify"), list("list"), outdent("outdent"), indent("indent"), link("link"), scissors("scissors"), files_o("files-o"), paperclip("paperclip"), floppy_o("floppy-o"), list_ul("list-ul"), list_ol("list-ol"), strikethrough("strikethrough"), underline("underline"), table("table"), columns("columns"), undo("undo"), clipboard("clipboard"), file_text_o("file-text-o"), chain_broken("chain-broken"), superscript("superscript"), subscript("subscript"), eraser("eraser"), file("file"), file_text("file-text"), header("header"), paragraph("paragraph"); 
		
		public final String literal;
		
		private TextEditor(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> textEditor(TextEditor textEditor);		
	
	enum Transportation {
		
		plane("plane"), truck("truck"), ambulance("ambulance"), fighter_jet("fighter-jet"), rocket("rocket"), wheelchair("wheelchair"), space_shuttle("space-shuttle"), car("car"), taxi("taxi"), bicycle("bicycle"), bus("bus"), ship("ship"), motorcycle("motorcycle"), train("train"), subway("subway"), wheelchair_alt("wheelchair-alt"); 
		
		public final String literal;
		
		private Transportation(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> transportation(Transportation transportation);		
	
	enum VideoPlayer {
		
		play_circle_o("play-circle-o"), step_backward("step-backward"), fast_backward("fast-backward"), backward("backward"), play("play"), pause("pause"), stop("stop"), forward("forward"), fast_forward("fast-forward"), step_forward("step-forward"), eject("eject"), expand("expand"), compress("compress"), random("random"), arrows_alt("arrows-alt"), play_circle("play-circle"), youtube_play("youtube-play"), pause_circle("pause-circle"), pause_circle_o("pause-circle-o"), stop_circle("stop-circle"), stop_circle_o("stop-circle-o"); 
		
		public final String literal;
		
		private VideoPlayer(String literal) {
			this.literal = literal;
		}
		
	}
	
	FontAwesome<T> videoPlayer(VideoPlayer videoPlayer);		
	
	enum WebApplication {
		
		glass("glass"), music("music"), search("search"), envelope_o("envelope-o"), heart("heart"), star("star"), star_o("star-o"), user("user"), film("film"), check("check"), times("times"), search_plus("search-plus"), search_minus("search-minus"), power_off("power-off"), signal("signal"), cog("cog"), trash_o("trash-o"), home("home"), clock_o("clock-o"), road("road"), download("download"), inbox("inbox"), refresh("refresh"), lock("lock"), flag("flag"), headphones("headphones"), volume_off("volume-off"), volume_down("volume-down"), volume_up("volume-up"), qrcode("qrcode"), barcode("barcode"), tag("tag"), tags("tags"), book("book"), bookmark("bookmark"), print("print"), camera("camera"), video_camera("video-camera"), picture_o("picture-o"), pencil("pencil"), map_marker("map-marker"), adjust("adjust"), tint("tint"), pencil_square_o("pencil-square-o"), share_square_o("share-square-o"), check_square_o("check-square-o"), arrows("arrows"), plus_circle("plus-circle"), minus_circle("minus-circle"), times_circle("times-circle"), check_circle("check-circle"), question_circle("question-circle"), info_circle("info-circle"), crosshairs("crosshairs"), times_circle_o("times-circle-o"), check_circle_o("check-circle-o"), ban("ban"), share("share"), plus("plus"), minus("minus"), asterisk("asterisk"), exclamation_circle("exclamation-circle"), gift("gift"), leaf("leaf"), fire("fire"), eye("eye"), eye_slash("eye-slash"), exclamation_triangle("exclamation-triangle"), plane("plane"), calendar("calendar"), random("random"), comment("comment"), magnet("magnet"), retweet("retweet"), shopping_cart("shopping-cart"), folder("folder"), folder_open("folder-open"), arrows_v("arrows-v"), arrows_h("arrows-h"), bar_chart("bar-chart"), camera_retro("camera-retro"), key("key"), cogs("cogs"), comments("comments"), thumbs_o_up("thumbs-o-up"), thumbs_o_down("thumbs-o-down"), star_half("star-half"), heart_o("heart-o"), sign_out("sign-out"), thumb_tack("thumb-tack"), external_link("external-link"), sign_in("sign-in"), trophy("trophy"), upload("upload"), lemon_o("lemon-o"), phone("phone"), square_o("square-o"), bookmark_o("bookmark-o"), phone_square("phone-square"), unlock("unlock"), credit_card("credit-card"), rss("rss"), hdd_o("hdd-o"), bullhorn("bullhorn"), bell("bell"), certificate("certificate"), globe("globe"), wrench("wrench"), tasks("tasks"), filter("filter"), briefcase("briefcase"), users("users"), cloud("cloud"), flask("flask"), square("square"), bars("bars"), magic("magic"), truck("truck"), money("money"), sort("sort"), sort_desc("sort-desc"), sort_asc("sort-asc"), envelope("envelope"), gavel("gavel"), tachometer("tachometer"), comment_o("comment-o"), comments_o("comments-o"), bolt("bolt"), sitemap("sitemap"), umbrella("umbrella"), lightbulb_o("lightbulb-o"), exchange("exchange"), cloud_download("cloud-download"), cloud_upload("cloud-upload"), suitcase("suitcase"), bell_o("bell-o"), coffee("coffee"), cutlery("cutlery"), building_o("building-o"), fighter_jet("fighter-jet"), beer("beer"), plus_square("plus-square"), desktop("desktop"), laptop("laptop"), tablet("tablet"), mobile("mobile"), circle_o("circle-o"), quote_left("quote-left"), quote_right("quote-right"), spinner("spinner"), circle("circle"), reply("reply"), folder_o("folder-o"), folder_open_o("folder-open-o"), smile_o("smile-o"), frown_o("frown-o"), meh_o("meh-o"), gamepad("gamepad"), keyboard_o("keyboard-o"), flag_o("flag-o"), flag_checkered("flag-checkered"), terminal("terminal"), code("code"), reply_all("reply-all"), star_half_o("star-half-o"), location_arrow("location-arrow"), crop("crop"), code_fork("code-fork"), question("question"), info("info"), exclamation("exclamation"), eraser("eraser"), puzzle_piece("puzzle-piece"), microphone("microphone"), microphone_slash("microphone-slash"), shield("shield"), calendar_o("calendar-o"), fire_extinguisher("fire-extinguisher"), rocket("rocket"), anchor("anchor"), unlock_alt("unlock-alt"), bullseye("bullseye"), ellipsis_h("ellipsis-h"), ellipsis_v("ellipsis-v"), rss_square("rss-square"), ticket("ticket"), minus_square("minus-square"), minus_square_o("minus-square-o"), level_up("level-up"), level_down("level-down"), check_square("check-square"), pencil_square("pencil-square"), external_link_square("external-link-square"), share_square("share-square"), compass("compass"), caret_square_o_down("caret-square-o-down"), caret_square_o_up("caret-square-o-up"), caret_square_o_right("caret-square-o-right"), sort_alpha_asc("sort-alpha-asc"), sort_alpha_desc("sort-alpha-desc"), sort_amount_asc("sort-amount-asc"), sort_amount_desc("sort-amount-desc"), sort_numeric_asc("sort-numeric-asc"), sort_numeric_desc("sort-numeric-desc"), thumbs_up("thumbs-up"), thumbs_down("thumbs-down"), female("female"), male("male"), sun_o("sun-o"), moon_o("moon-o"), archive("archive"), bug("bug"), caret_square_o_left("caret-square-o-left"), dot_circle_o("dot-circle-o"), wheelchair("wheelchair"), plus_square_o("plus-square-o"), space_shuttle("space-shuttle"), envelope_square("envelope-square"), university("university"), graduation_cap("graduation-cap"), language("language"), fax("fax"), building("building"), child("child"), paw("paw"), spoon("spoon"), cube("cube"), cubes("cubes"), recycle("recycle"), car("car"), taxi("taxi"), tree("tree"), database("database"), file_pdf_o("file-pdf-o"), file_word_o("file-word-o"), file_excel_o("file-excel-o"), file_powerpoint_o("file-powerpoint-o"), file_image_o("file-image-o"), file_archive_o("file-archive-o"), file_audio_o("file-audio-o"), file_video_o("file-video-o"), file_code_o("file-code-o"), life_ring("life-ring"), circle_o_notch("circle-o-notch"), paper_plane("paper-plane"), paper_plane_o("paper-plane-o"), history("history"), circle_thin("circle-thin"), sliders("sliders"), share_alt("share-alt"), share_alt_square("share-alt-square"), bomb("bomb"), futbol_o("futbol-o"), tty("tty"), binoculars("binoculars"), plug("plug"), newspaper_o("newspaper-o"), wifi("wifi"), calculator("calculator"), bell_slash("bell-slash"), bell_slash_o("bell-slash-o"), trash("trash"), copyright("copyright"), at("at"), eyedropper("eyedropper"), paint_brush("paint-brush"), birthday_cake("birthday-cake"), area_chart("area-chart"), pie_chart("pie-chart"), line_chart("line-chart"), toggle_off("toggle-off"), toggle_on("toggle-on"), bicycle("bicycle"), bus("bus"), cc("cc"), cart_plus("cart-plus"), cart_arrow_down("cart-arrow-down"), diamond("diamond"), ship("ship"), user_secret("user-secret"), motorcycle("motorcycle"), street_view("street-view"), heartbeat("heartbeat"), server("server"), user_plus("user-plus"), user_times("user-times"), bed("bed"), battery_full("battery-full"), battery_three_quarters("battery-three-quarters"), battery_half("battery-half"), battery_quarter("battery-quarter"), battery_empty("battery-empty"), mouse_pointer("mouse-pointer"), i_cursor("i-cursor"), object_group("object-group"), object_ungroup("object-ungroup"), sticky_note("sticky-note"), sticky_note_o("sticky-note-o"), clone("clone"), balance_scale("balance-scale"), hourglass_o("hourglass-o"), hourglass_start("hourglass-start"), hourglass_half("hourglass-half"), hourglass_end("hourglass-end"), hourglass("hourglass"), hand_rock_o("hand-rock-o"), hand_paper_o("hand-paper-o"), hand_scissors_o("hand-scissors-o"), hand_lizard_o("hand-lizard-o"), hand_spock_o("hand-spock-o"), hand_pointer_o("hand-pointer-o"), hand_peace_o("hand-peace-o"), trademark("trademark"), registered("registered"), creative_commons("creative-commons"), television("television"), calendar_plus_o("calendar-plus-o"), calendar_minus_o("calendar-minus-o"), calendar_times_o("calendar-times-o"), calendar_check_o("calendar-check-o"), industry("industry"), map_pin("map-pin"), map_signs("map-signs"), map_o("map-o"), map("map"), commenting("commenting"), commenting_o("commenting-o"), credit_card_alt("credit-card-alt"), shopping_bag("shopping-bag"), shopping_basket("shopping-basket"), hashtag("hashtag"), bluetooth("bluetooth"), bluetooth_b("bluetooth-b"), percent("percent"), universal_access("universal-access"), wheelchair_alt("wheelchair-alt"), question_circle_o("question-circle-o"), blind("blind"), audio_description("audio-description"), volume_control_phone("volume-control-phone"), braille("braille"), assistive_listening_systems("assistive-listening-systems"), american_sign_language_interpreting("american-sign-language-interpreting"), deaf("deaf"), sign_language("sign-language"), low_vision("low-vision"), handshake_o("handshake-o"), envelope_open("envelope-open"), envelope_open_o("envelope-open-o"), address_book("address-book"), address_book_o("address-book-o"), address_card("address-card"), address_card_o("address-card-o"), user_circle("user-circle"), user_circle_o("user-circle-o"), user_o("user-o"), id_badge("id-badge"), id_card("id-card"), id_card_o("id-card-o"), thermometer_full("thermometer-full"), thermometer_three_quarters("thermometer-three-quarters"), thermometer_half("thermometer-half"), thermometer_quarter("thermometer-quarter"), thermometer_empty("thermometer-empty"), shower("shower"), bath("bath"), podcast("podcast"), window_maximize("window-maximize"), window_minimize("window-minimize"), window_restore("window-restore"), window_close("window-close"), window_close_o("window-close-o"), microchip("microchip"), snowflake_o("snowflake-o"); 
		
		public final String literal;
		
		private WebApplication(String literal) {
			this.literal = literal;
		}
		
	}
		
	FontAwesome<T> webApplication(WebApplication webApplication);		
		
	T getTarget();
	
	FontAwesome<T> fixedWidth();
	
	/**
	 * Adds fa-li class for list items.
	 * @return
	 */
	FontAwesome<T> li();
	
	/**
	 * Adds fa-ul for unordered lists.
	 * @return
	 */
	FontAwesome<T> ul();

	FontAwesome<T> pullLeft();
	
	FontAwesome<T> pullRight();
	
	enum Size { large, x2, x3, x4, x5 }
	
	FontAwesome<T> size(Size size);

	FontAwesome<T> spin();
	
	enum Rotate { R90, R180, R270 }
	
	FontAwesome<T> rotate(Rotate rotate);
	
	enum Flip { horizontal, vertical }
	
	FontAwesome<T> flip(Flip flip);
	
	
	interface Stack extends AutoCloseable {
		
		enum IconSize { x1, x2, x3, x4, x5 }
	
		Stack icon(UIElement<?> icon, IconSize size, boolean inverse);
		
		Stack icon(FontAwesome<?> icon, IconSize size, boolean inverse);
		
		Stack size(Size size);
		
	}
	
	FontAwesome<T> style(Bootstrap.Style style);

}