package org.nasdanika.html.emf;

import org.nasdanika.ncore.GitMarker;

/**
 * Context service interface for resolving Git web URL links from repository paths.
 * @author Pavel
 *
 */
public interface GitLinkResolver {
	
	/**
	 * Knows how to resolve GitHub links.
	 */
	static GitLinkResolver INSTANCE = new GitLinkResolver() {
		
		@Override
		public String resolve(GitMarker marker, String remoteURL) {
			if (remoteURL.startsWith("https://github.com")) {
				return remoteURL.substring(0, remoteURL.length() - 4) + "/blob/" + marker.getHead() + "/" + marker.getPath() + "#L" + marker.getLine(); 
			}
			return null;
		}
	};
	
	/**
	 * @param marker
	 * @param remoteURL
	 * @return Link for a given Git marker for a given remote URL.
	 */
	String resolve(GitMarker marker, String remoteURL);

}
