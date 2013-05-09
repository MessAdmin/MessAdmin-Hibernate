/**
 *
 */
package clime.messadmin.hibernate.support;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Query statistics (HQL and SQL)
 * <p/>
 * Note that for a cached query, the cache miss is equals to the db count
 *
 * @author C&eacute;drik LIME
 */
public class QueryStatistics extends BaseStatisticsSupport implements Serializable {
	private static transient Method getExecutionCount;
	private static transient Method getCacheHitCount;
	private static transient Method getCachePutCount;
	private static transient Method getCacheMissCount;
	private static transient Method getExecutionRowCount;
	private static transient Method getExecutionAvgTime;
	private static transient Method getExecutionMaxTime;
	private static transient Method getExecutionMinTime;

	static {
		try {
			Class<?> queryStatistics = Class.forName("org.hibernate.stat.QueryStatistics");//$NON-NLS-1$
			getExecutionCount    = queryStatistics.getMethod("getExecutionCount"   );//$NON-NLS-1$
			getCacheHitCount     = queryStatistics.getMethod("getCacheHitCount"    );//$NON-NLS-1$
			getCachePutCount     = queryStatistics.getMethod("getCachePutCount"    );//$NON-NLS-1$
			getCacheMissCount    = queryStatistics.getMethod("getCacheMissCount"   );//$NON-NLS-1$
			getExecutionRowCount = queryStatistics.getMethod("getExecutionRowCount");//$NON-NLS-1$
			getExecutionAvgTime  = queryStatistics.getMethod("getExecutionAvgTime" );//$NON-NLS-1$
			getExecutionMaxTime  = queryStatistics.getMethod("getExecutionMaxTime" );//$NON-NLS-1$
			getExecutionMinTime  = queryStatistics.getMethod("getExecutionMinTime" );//$NON-NLS-1$
		} catch (NoSuchMethodException e) {
		} catch (ClassNotFoundException e) {
		} catch (SecurityException e) {
		}
	}

	public QueryStatistics(Serializable delegate) {
		super(delegate);
	}

	/**
	 * queries executed to the DB
	 * @see org.hibernate.stat.QueryStatistics#getExecutionCount()
	 */
	public long getExecutionCount() {
		return invokeLong(getExecutionCount, -1);
	}

	/**
	 * Queries retrieved successfully from the cache
	 * @see org.hibernate.stat.QueryStatistics#getCacheHitCount()
	 */
	public long getCacheHitCount() {
		return invokeLong(getCacheHitCount, -1);
	}

	/**
	 * @see org.hibernate.stat.QueryStatistics#getCachePutCount()
	 */
	public long getCachePutCount() {
		return invokeLong(getCachePutCount, -1);
	}

	/**
	 * @see org.hibernate.stat.QueryStatistics#getCacheMissCount()
	 */
	public long getCacheMissCount() {
		return invokeLong(getCacheMissCount, -1);
	}

	/**
	 * Number of lines returned by all the executions of this query (from DB)
	 * For now, {@link org.hibernate.Query#iterate()}
	 * and {@link org.hibernate.Query#scroll()()} do not fill this statistic
	 *
	 * @return The number of rows cumulatively returned by the given query; iterate
	 *         and scroll queries do not effect this total as their number of returned rows
	 *         is not known at execution time.
	 *
	 * @see org.hibernate.stat.QueryStatistics#getExecutionRowCount()
	 */
	public long getExecutionRowCount() {
		return invokeLong(getExecutionRowCount, -1);
	}

	/**
	 * average time in ms taken by the execution of this query onto the DB
	 * @see org.hibernate.stat.QueryStatistics#getExecutionAvgTime()
	 */
	public long getExecutionAvgTime() {
		return invokeLong(getExecutionAvgTime, -1);
	}

	/**
	 * max time in ms taken by the execution of this query onto the DB
	 * @see org.hibernate.stat.QueryStatistics#getExecutionMaxTime()
	 */
	public long getExecutionMaxTime() {
		return invokeLong(getExecutionMaxTime, -1);
	}

	/**
	 * min time in ms taken by the execution of this query onto the DB
	 * @see org.hibernate.stat.QueryStatistics#getExecutionMinTime()
	 */
	public long getExecutionMinTime() {
		return invokeLong(getExecutionMinTime, -1);
	}
}
