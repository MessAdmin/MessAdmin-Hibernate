/**
 *
 */
package clime.messadmin.hibernate;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.stat.Statistics;

import clime.messadmin.hibernate.support.EntityStatistics;
import clime.messadmin.i18n.I18NSupport;

/**
 * @author C&eacute;drik LIME
 */
class EntityStatisticsTable extends BaseStatisticsTable {

	/**
	 *
	 */
	public EntityStatisticsTable() {
		super();
	}

	protected String[] getApplicationTabularDataLabels(ServletContext context) {
		return new String[] {
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.entities.label.name"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.entities.label.loadCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.entities.label.fetchCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.entities.label.insertCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.entities.label.updateCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.entities.label.deleteCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.entities.label.optimisticFailureCount")//$NON-NLS-1
		};
	}

	protected Object[][] getApplicationTabularData(ServletContext context, Statistics statistics) {
		NumberFormat numberFormatter = NumberFormat.getNumberInstance(I18NSupport.getAdminLocale());
		List data = new LinkedList();

		String[] names = statistics.getEntityNames();
		for (int i = 0; i < names.length; ++i) {
			EntityStatistics entityStatistics = new EntityStatistics(statistics.getEntityStatistics(names[i]));
			data.add(new Object[] {
					names[i],
					numberFormatter.format(entityStatistics.getLoadCount()),
					numberFormatter.format(entityStatistics.getFetchCount()),
					numberFormatter.format(entityStatistics.getInsertCount()),
					numberFormatter.format(entityStatistics.getUpdateCount()),
					numberFormatter.format(entityStatistics.getDeleteCount()),
					numberFormatter.format(entityStatistics.getOptimisticFailureCount())
				});
		}

		Object[][] result = (Object[][]) data.toArray(new Object[data.size()][]);
		return result;
	}
}
