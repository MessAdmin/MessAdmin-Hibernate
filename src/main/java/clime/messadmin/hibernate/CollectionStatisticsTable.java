/**
 *
 */
package clime.messadmin.hibernate;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.stat.Statistics;

import clime.messadmin.hibernate.support.CollectionStatistics;
import clime.messadmin.i18n.I18NSupport;

/**
 * @author C&eacute;drik LIME
 */
class CollectionStatisticsTable extends BaseStatisticsTable {

	/**
	 *
	 */
	public CollectionStatisticsTable() {
		super();
	}

	@Override
	protected String[] getApplicationTabularDataLabels(ServletContext context) {
		final ClassLoader cl = I18NSupport.getClassLoader(context);
		return new String[] {
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, cl, "detail.collections.label.name"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, cl, "detail.collections.label.loadCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, cl, "detail.collections.label.fetchCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, cl, "detail.collections.label.recreateCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, cl, "detail.collections.label.updateCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, cl, "detail.collections.label.removeCount")//$NON-NLS-1
		};
	}

	@Override
	protected Object[][] getApplicationTabularData(ServletContext context, Statistics statistics) {
		NumberFormat numberFormatter = NumberFormat.getNumberInstance(I18NSupport.getAdminLocale());
		List<Object> data = new LinkedList<Object>();

		String[] names = statistics.getCollectionRoleNames();
		for (int i = 0; i < names.length; ++i) {
			CollectionStatistics collectionStatistics = new CollectionStatistics(statistics.getCollectionStatistics(names[i]));
			data.add(new Object[] {
					names[i],
					numberFormatter.format(collectionStatistics.getLoadCount()),
					numberFormatter.format(collectionStatistics.getFetchCount()),
					numberFormatter.format(collectionStatistics.getRecreateCount()),
					numberFormatter.format(collectionStatistics.getUpdateCount()),
					numberFormatter.format(collectionStatistics.getRemoveCount())
				});
		}

		Object[][] result = data.toArray(new Object[data.size()][]);
		return result;
	}
}
