package org.nasdanika.html;

/**
 * Interface for creating Font Awesome (http://fortawesome.github.io/Font-Awesome/) styled UI elements.
 * @author Pavel Vlasov
 *
 */
public interface FontAwesome<T extends UIElement<?>> {
	
	String VERSION = "4.4.0";
	
	FontAwesome<T> custom(String custom);	

	enum WebApplication {
		adjust, anchor, archive, area_chart, arrows, arrows_h, arrows_v, asterisk, at, automobile, balance_scale, ban, bank, bar_chart, bar_chart_o, barcode, bars, battery_0, battery_1, battery_2, battery_3, battery_4, battery_empty, battery_full, battery_half, battery_quarter, battery_three_quarters, bed, beer, bell, bell_o, bell_slash, bell_slash_o, bicycle, binoculars, birthday_cake, bolt, bomb, book, bookmark, bookmark_o, briefcase, bug, building, building_o, bullhorn, bullseye, bus, cab, calculator, calendar, calendar_check_o, calendar_minus_o, calendar_o, calendar_plus_o, calendar_times_o, camera, camera_retro, car, caret_square_o_down, caret_square_o_left, caret_square_o_right, caret_square_o_up, cart_arrow_down, cart_plus, cc, certificate, check, check_circle, check_circle_o, check_square, check_square_o, child, circle, circle_o, circle_o_notch, circle_thin, clock_o, clone, close, cloud, cloud_download, cloud_upload, code, code_fork, coffee, cog, cogs, comment, comment_o, commenting, commenting_o, comments, comments_o, compass, copyright, creative_commons, credit_card, crop, crosshairs, cube, cubes, cutlery, dashboard, database, desktop, diamond, dot_circle_o, download, edit, ellipsis_h, ellipsis_v, envelope, envelope_o, envelope_square, eraser, exchange, exclamation, exclamation_circle, exclamation_triangle, external_link, external_link_square, eye, eye_slash, eyedropper, fax, feed, female, fighter_jet, file_archive_o, file_audio_o, file_code_o, file_excel_o, file_image_o, file_movie_o, file_pdf_o, file_photo_o, file_picture_o, file_powerpoint_o, file_sound_o, file_video_o, file_word_o, file_zip_o, film, filter, fire, fire_extinguisher, flag, flag_checkered, flag_o, flash, flask, folder, folder_o, folder_open, folder_open_o, frown_o, futbol_o, gamepad, gavel, gear, gears, gift, glass, globe, graduation_cap, group, hand_grab_o, hand_lizard_o, hand_paper_o, hand_peace_o, hand_pointer_o, hand_rock_o, hand_scissors_o, hand_spock_o, hand_stop_o, hdd_o, headphones, heart, heart_o, heartbeat, history, home, hotel, hourglass, hourglass_1, hourglass_2, hourglass_3, hourglass_end, hourglass_half, hourglass_o, hourglass_start, i_cursor, image, inbox, industry, info, info_circle, institution, key, keyboard_o, language, laptop, leaf, legal, lemon_o, level_down, level_up, life_bouy, life_buoy, life_ring, life_saver, lightbulb_o, line_chart, location_arrow, lock, magic, magnet, mail_forward, mail_reply, mail_reply_all, male, map, map_marker, map_o, map_pin, map_signs, meh_o, microphone, microphone_slash, minus, minus_circle, minus_square, minus_square_o, mobile, mobile_phone, money, moon_o, mortar_board, motorcycle, mouse_pointer, music, navicon, newspaper_o, object_group, object_ungroup, paint_brush, paper_plane, paper_plane_o, paw, pencil, pencil_square, pencil_square_o, phone, phone_square, photo, picture_o, pie_chart, plane, plug, plus, plus_circle, plus_square, plus_square_o, power_off, print, puzzle_piece, qrcode, question, question_circle, quote_left, quote_right, random, recycle, refresh, registered, remove, reorder, reply, reply_all, retweet, road, rocket, rss, rss_square, search, search_minus, search_plus, send, send_o, server, share, share_alt, share_alt_square, share_square, share_square_o, shield, ship, shopping_cart, sign_in, sign_out, signal, sitemap, sliders, smile_o, soccer_ball_o, sort, sort_alpha_asc, sort_alpha_desc, sort_amount_asc, sort_amount_desc, sort_asc, sort_desc, sort_down, sort_numeric_asc, sort_numeric_desc, sort_up, space_shuttle, spinner, spoon, square, square_o, star, star_half, star_half_empty, star_half_full, star_half_o, star_o, sticky_note, sticky_note_o, street_view, suitcase, sun_o, support, tablet, tachometer, tag, tags, tasks, taxi, television, terminal, thumb_tack, thumbs_down, thumbs_o_down, thumbs_o_up, thumbs_up, ticket, times, times_circle, times_circle_o, tint, toggle_down, toggle_left, toggle_off, toggle_on, toggle_right, toggle_up, trademark, trash, trash_o, tree, trophy, truck, tty, tv, umbrella, university, unlock, unlock_alt, unsorted, upload, user, user_plus, user_secret, user_times, users, video_camera, volume_down, volume_off, volume_up, warning, wheelchair, wifi, wrench
	}
	
	FontAwesome<T> webApplication(WebApplication webApplication);	
	
	enum Hand {
		hand_grab_o, hand_lizard_o, hand_o_down, hand_o_left, hand_o_right, hand_o_up, hand_paper_o, hand_peace_o, hand_pointer_o, hand_rock_o, hand_scissors_o, hand_spock_o, hand_stop_o, thumbs_down, thumbs_o_down, thumbs_o_up, thumbs_up,
	}

	FontAwesome<T> hand(Hand hand);
	
	enum Transportation {
		ambulance, automobile, bicycle, bus, cab, car, fighter_jet, motorcycle, plane, rocket, ship, space_shuttle, subway, taxi, train, truck, wheelchair
	}
	
	FontAwesome<T> transportation(Transportation transportation);
	
	enum Gender {
		genderless, intersex, mars, mars_double, mars_stroke, mars_stroke_h, mars_stroke_v, mercury, neuter, transgender, transgender_alt, venus, venus_double, venus_mars
	}

	FontAwesome<T> gender(Gender gender);
	
	enum FileType {
		file, file_archive_o, file_audio_o, file_code_o, file_excel_o, file_image_o, file_movie_o, file_o, file_pdf_o, file_photo_o, file_picture_o, file_powerpoint_o, file_sound_o, file_text, file_text_o, file_video_o, file_word_o, file_zip_o
	}
	
	FontAwesome<T> fileType(FileType fileType);
	
	enum Spinner {
		circle_o_notch, cog, gear, refresh, spinner
	}

	FontAwesome<T> spinner(Spinner spinner);

	enum FormControl {
		check_square, check_square_o, circle, circle_o, dot_circle_o, minus_square, minus_square_o, plus_square, plus_square_o, square, square_o
	}
	
	FontAwesome<T> formControl(FormControl formControl);

	enum Payment {
		cc_amex, cc_diners_club, cc_discover, cc_jcb, cc_mastercard, cc_paypal, cc_stripe, cc_visa, credit_card, google_wallet, paypal
	}
	
	FontAwesome<T> payment(Payment payment);

	enum Chart {
		area_chart, bar_chart, bar_chart_o, line_chart, pie_chart
	}

	FontAwesome<T> chart(Chart chart);

	enum Currency {
		bitcoin, btc, cny, dollar, eur, euro, gbp, gg, gg_circle, ils, inr, jpy, krw, money, rmb, rouble, rub, ruble, rupee, shekel, sheqel, turkish_lira, usd, won, yen
	}

	FontAwesome<T> currency(Currency currency);
	
	enum TextEditor {
		align_center, align_justify, align_left, align_right, bold, chain, chain_broken, clipboard, columns, copy, cut, dedent, eraser, file, file_o, file_text, file_text_o, files_o, floppy_o, font, header, indent, italic, link, list, list_alt, list_ol, list_ul, outdent, paperclip, paragraph, paste, repeat, rotate_left, rotate_right, save, scissors, strikethrough, subscript, superscript, table, text_height, text_width, th, th_large, th_list, underline, undo, unlink
	}
	
	FontAwesome<T> textEditor(TextEditor textEditor);

	enum Directional {
		angle_double_down, angle_double_left, angle_double_right, angle_double_up, angle_down, angle_left, angle_right, angle_up, arrow_circle_down, arrow_circle_left, arrow_circle_o_down, arrow_circle_o_left, arrow_circle_o_right, arrow_circle_o_up, arrow_circle_right, arrow_circle_up, arrow_down, arrow_left, arrow_right, arrow_up, arrows, arrows_alt, arrows_h, arrows_v, caret_down, caret_left, caret_right, caret_square_o_down, caret_square_o_left, caret_square_o_right, caret_square_o_up, caret_up, chevron_circle_down, chevron_circle_left, chevron_circle_right, chevron_circle_up, chevron_down, chevron_left, chevron_right, chevron_up, exchange, hand_o_down, hand_o_left, hand_o_right, hand_o_up, long_arrow_down, long_arrow_left, long_arrow_right, long_arrow_up, toggle_down, toggle_left, toggle_right, toggle_up
	}

	FontAwesome<T> directional(Directional directional);

	enum VideoPlayer {
		arrows_alt, backward, compress, eject, expand, fast_backward, fast_forward, forward, pause, play, play_circle, play_circle_o, random, step_backward, step_forward, stop, youtube_play
	}
	
	FontAwesome<T> videoPlayer(VideoPlayer videoPlayer);

	enum Brand {
		adn, amazon, android, angellist, apple, behance, behance_square, bitbucket, bitbucket_square, bitcoin, black_tie, btc, buysellads, cc_amex, cc_diners_club, cc_discover, cc_jcb, cc_mastercard, cc_paypal, cc_stripe, cc_visa, chrome, codepen, connectdevelop, contao, css3, dashcube, delicious, deviantart, digg, dribbble, dropbox, drupal, empire, expeditedssl, facebook, facebook_f, facebook_official, facebook_square, firefox, flickr, fonticons, forumbee, foursquare, ge, get_pocket, gg, gg_circle, git, git_square, github, github_alt, github_square, gittip, google, google_plus, google_plus_square, google_wallet, gratipay, hacker_news, houzz, html5, instagram, internet_explorer, ioxhost, joomla, jsfiddle, lastfm, lastfm_square, leanpub, linkedin, linkedin_square, linux, maxcdn, meanpath, medium, odnoklassniki, odnoklassniki_square, opencart, openid, opera, optin_monster, pagelines, paypal, pied_piper, pied_piper_alt, pinterest, pinterest_p, pinterest_square, qq, ra, rebel, reddit, reddit_square, renren, safari, sellsy, share_alt, share_alt_square, shirtsinbulk, simplybuilt, skyatlas, skype, slack, slideshare, soundcloud, spotify, stack_exchange, stack_overflow, steam, steam_square, stumbleupon, stumbleupon_circle, tencent_weibo, trello, tripadvisor, tumblr, tumblr_square, twitch, twitter, twitter_square, viacoin, vimeo, vimeo_square, vine, vk, wechat, weibo, weixin, whatsapp, wikipedia_w, windows, wordpress, xing, xing_square, y_combinator, y_combinator_square, yahoo, yc, yc_square, yelp, youtube, youtube_play, youtube_square
	}

	FontAwesome<T> brand(Brand brand);

	enum Medical {
		ambulance, h_square, heart, heart_o, heartbeat, hospital_o, medkit, plus_square, stethoscope, user_md, wheelchair
	}
	
	FontAwesome<T> medical(Medical medical);
	
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