package org.nasdanika.html.app.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.function.BiFunction;

import org.nasdanika.html.Producer;
import org.nasdanika.html.app.ApplicationException;

/**
 * Adapts different content types to {@link InputStream} - {@link Reader}, {@link CharSequence}, byte[], {@link URL}, {@link Producer}
 * @author Pavel
 *
 */
public class ResourceConsumerStreamAdapter implements BiFunction<String, Object, String> {

	private BiFunction<String, InputStream, String> streamConsumer;

	public ResourceConsumerStreamAdapter(BiFunction<String, InputStream, String> streamConsumer) {
		this.streamConsumer = streamConsumer;
	}

	@Override
	public String apply(String path, Object content) {
		if (content == null) {
			return null;
		}
		if (content instanceof InputStream) {
			return streamConsumer.apply(path, (InputStream) content);
		}
		if (content instanceof byte[]) {
			return streamConsumer.apply(path, new ByteArrayInputStream((byte[]) content));
		}
		if (content instanceof URL) {
			try {
				return streamConsumer.apply(path, ((URL) content).openStream());
			} catch (IOException e) {
				throw new ApplicationException(e);
			}
		}
		if (content instanceof CharSequence) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (Writer writer = new OutputStreamWriter(baos)) {
				writer.write(content.toString());
			} catch (IOException e) {
				throw new ApplicationException(e);
			}
			try {
				baos.close();				
				return apply(path, baos.toByteArray());
			} catch (IOException e) {
				throw new ApplicationException(e);
			}
		}
					
		if (content instanceof Producer) {
			return apply(path, ((Producer) content).produce(4));
		}

		if (content instanceof Reader) {
			try {			
				StringWriter sw = new StringWriter();
				for (int ch = ((Reader) content).read(); ch!=-1; ch = ((Reader) content).read()) {
					sw.write(ch);
				}
				((Reader) content).close();
				sw.close();
				return apply(path, sw.toString());
			} catch (IOException e) {
				throw new ApplicationException(e);
			}
		}
		return apply(path, content.toString());
	}

}
