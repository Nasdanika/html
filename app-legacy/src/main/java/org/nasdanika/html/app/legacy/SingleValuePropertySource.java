package org.nasdanika.html.app;

/**
 * Source of single value.
 * Single value can be rendered as a link/label if there is a single property descriptor
 * or as a tree columns table with property descriptor labels in the first column, values in the second, and actions in the third.
 * @author Pavel Vlasov
 *
 */
public interface SingleValuePropertySource extends PropertySource, SingleValueDataSource {

}
