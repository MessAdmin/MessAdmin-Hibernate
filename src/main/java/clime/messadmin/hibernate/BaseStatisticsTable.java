/**
 *
 */
package clime.messadmin.hibernate;

import java.text.NumberFormat;

import javax.servlet.ServletContext;

import org.hibernate.stat.Statistics;

import clime.messadmin.i18n.I18NSupport;
import clime.messadmin.providers.spi.BaseTabularDataProvider;

/**
 * @author C&eacute;drik LIME
 */
abstract class BaseStatisticsTable extends BaseTabularDataProvider {
	static final String BUNDLE_NAME = HibernateStatistics.class.getName();

	/**
	 *
	 */
	public BaseStatisticsTable() {
		super();
	}

	/** {@inheritDoc} */
	public StringBuffer getXHTMLApplicationData(StringBuffer buffer, ServletContext context, Statistics statistics) {
		String[] labels = getApplicationTabularDataLabels(context);
		Object[][] values = getApplicationTabularData(context, statistics);
		String tableId = "extraApplicationAttributesTable-"+getClass().getName();
		buildXHTML(buffer, labels, values, tableId, getTableCaption(statistics));
		return buffer;
	}

	abstract protected String[] getApplicationTabularDataLabels(ServletContext context);
	abstract protected Object[][] getApplicationTabularData(ServletContext context, Statistics statistics);

	protected String getTableCaption(Statistics statistics) {
		return "";
	}

	/** {@inheritDoc} */
	@Override
	protected String getCellClass(int cellNumber, Object value) {
		return cellNumber > 0 ? "number" : super.getCellClass(cellNumber, value);
	}

	@Override
	protected void appendFooter(StringBuffer buffer, String[] labels) {
		buffer.append("<tfoot><tr>\n");
		for (int i = 0; i < labels.length; ++i) {
			String label = labels[i];
			buffer.append("<th>");
			appendFooterLabel(buffer, i, label);
			buffer.append("</th>");
		}
		buffer.append("\n</tr></tfoot>\n");
	}

	protected String getPercentageOfTotalForAppend(long measured, long remainder, boolean hideExtremes) {
		long total = measured + remainder;
		if (total == 0 || (hideExtremes && (measured == 0 || measured == total))) {
			return "";
		} else {
			NumberFormat formatter = NumberFormat.getPercentInstance(I18NSupport.getAdminLocale());
			return " (" + formatter.format(measured / (double)total) + ')';
		}
	}
}
