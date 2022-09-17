package org.nasdanika.html.model.html.gen;

import java.util.function.Consumer;

import org.nasdanika.common.Context;

/**
 * Marker {@link Context} service interface which allows generators to contribute "reference" content. For example, a link may contribute a modal dialog which is triggered by that link. 
 * @author Pavel
 *
 */
public interface ContentConsumer extends Consumer<Object> {

}
