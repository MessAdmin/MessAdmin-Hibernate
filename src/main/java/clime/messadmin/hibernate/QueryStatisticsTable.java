/**
 *
 */
package clime.messadmin.hibernate;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.SessionFactory;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.stat.Statistics;

import clime.messadmin.hibernate.support.QueryStatistics;
import clime.messadmin.i18n.I18NSupport;
import clime.messadmin.utils.StringUtils;

/**
 * @author C&eacute;drik LIME
 */
class QueryStatisticsTable extends BaseStatisticsTable {
	protected SessionFactory sessionFactory;

	/**
	 *
	 */
	public QueryStatisticsTable(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	protected String[] getApplicationTabularDataLabels(ServletContext context) {
		return new String[] {
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.queries.label.name"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.queries.label.cacheHitCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.queries.label.cacheMissCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.queries.label.cachePutCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.queries.label.executionCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.queries.label.executionRowCount"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.queries.label.executionMinTime"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.queries.label.executionMaxTime"),//$NON-NLS-1
				I18NSupport.getLocalizedMessage(BUNDLE_NAME, "detail.queries.label.executionAvgTime")//$NON-NLS-1
		};
	}

	@Override
	protected Object[][] getApplicationTabularData(ServletContext context, Statistics statistics) {
		NumberFormat numberFormatter = NumberFormat.getNumberInstance(I18NSupport.getAdminLocale());
		List<Object> data = new LinkedList<Object>();

		String[] names = statistics.getQueries();
		for (int i = 0; i < names.length; ++i) {
			QueryStatistics querytatistics = new QueryStatistics(statistics.getQueryStatistics(names[i]));
			long hits = querytatistics.getCacheHitCount();
			long misses = querytatistics.getCacheMissCount();
			data.add(new Object[] {
					names[i],
					numberFormatter.format(hits) + getPercentageOfTotalForAppend(hits, misses, false),
					numberFormatter.format(misses) + getPercentageOfTotalForAppend(misses, hits, false),
					numberFormatter.format(querytatistics.getCachePutCount()),
					numberFormatter.format(querytatistics.getExecutionCount()),
					numberFormatter.format(querytatistics.getExecutionRowCount()),
					numberFormatter.format(querytatistics.getExecutionMinTime()),
					numberFormatter.format(querytatistics.getExecutionMaxTime()),
					numberFormatter.format(querytatistics.getExecutionAvgTime())
				});
		}

		Object[][] result = data.toArray(new Object[data.size()][]);
		return result;
	}

	/** {@inheritDoc} */
	@Override
	protected void appendValue(StringBuffer buffer, int cellNumber, Object value) {
		if (cellNumber == 0) {
			buffer.append("<code>");
			buffer.append(StringUtils.escapeXml((String)value));
			buffer.append("</code>");
			if (sessionFactory instanceof SessionFactoryImplementor) {
				buffer.append("<hr /><code>");
				buffer.append(StringUtils.escapeXml(HibernateStatistics.hql2sql((SessionFactoryImplementor)sessionFactory, (String)value)));
				buffer.append("</code>");
			}
		} else {
			super.appendValue(buffer, cellNumber, value);
		}
	}
}
