package org.pcmiel.blog.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class Sitemesh extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/app/*", "/WEB-INF/jsp/appDecorator.jsp");
		builder.addDecoratorPath("/*", "/WEB-INF/jsp/decorator.jsp");
	}
}
