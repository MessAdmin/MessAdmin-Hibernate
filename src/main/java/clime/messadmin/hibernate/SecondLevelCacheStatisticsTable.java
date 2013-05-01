/**
 *
 */
package clime.messadmin.hibernate;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.stat.Statistics;

import clime.messadmin.hibernate.support.SecondLevelCacheStatistics;
import clime.messadmin.i18n.I18NSupport;
import clime.messadmin.utils.BytesFormat;

/**
 * @author C&eacute;drik LIME
 */
class SecondLevelCacheStatisticsTable extends BaseStatisticsTable {

	/**
	 *
	 */
	public SecondLevelCacheStatisticsTable() {
		super();
	}

	protected String[] getApplicationTabularDataLabels(ServletContext context) {
		return new String[] {
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.secondLevelCaches.label.name"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.secondLevelCaches.label.hitCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.secondLevelCaches.label.missCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.secondLevelCaches.label.putCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.secondLevelCaches.label.elementCountInMemory"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.secondLevelCaches.label.elementCountOnDisk"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.secondLevelCaches.label.sizeInMemory")//$NON-NLS-1
		};
	}

	protected Object[][] getApplicationTabularData(ServletContext context, Statistics statistics) {
		NumberFormat numberFormatter = NumberFormat.getNumberInstance(I18NSupport.getAdminLocale());
		NumberFormat bytesFormatter = BytesFormat.getBytesInstance(I18NSupport.getAdminLocale(), true);
		List data = new LinkedList();

		String[] names = statistics.getSecondLevelCacheRegionNames();
		for (int i = 0; i < names.length; ++i) {
			SecondLevelCacheStatistics secondLevelCacheStatistics = new SecondLevelCacheStatistics(statistics.getSecondLevelCacheStatistics(names[i]));
			long hits = secondLevelCacheStatistics.getHitCount();
			long misses = secondLevelCacheStatistics.getMissCount();
			data.add(new Object[] {
					names[i],
					numberFormatter.format(hits) + getPercentageOfTotalForAppend(hits, misses, false),
					numberFormatter.format(misses) + getPercentageOfTotalForAppend(misses, hits, false),
					numberFormatter.format(secondLevelCacheStatistics.getPutCount()),
					formatIfPositive(numberFormatter, secondLevelCacheStatistics.getElementCountInMemory()),
					formatIfPositive(numberFormatter, secondLevelCacheStatistics.getElementCountOnDisk()),
					formatIfPositive(bytesFormatter, secondLevelCacheStatistics.getSizeInMemory())
				});
		}

		Object[][] result = (Object[][]) data.toArray(new Object[data.size()][]);
		return result;
	}

	protected String formatIfPositive(NumberFormat formatter, long value) {
		return value < 0 ? "" : formatter.format(value);
	}
}
