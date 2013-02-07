package org.juri.blog.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class Sitemesh extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		//builder.addDecoratorPath("/login.html", "/WEB-INF/jsp/login.jsp");
		builder.addDecoratorPath("/*", "/WEB-INF/jsp/decorator.jsp");
	}
}
