/**
 *
 */
package clime.messadmin.hibernate.support;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Entity related statistics
 *
 * @author C&eacute;drik LIME
 */
public class EntityStatistics extends BaseStatisticsSupport implements Serializable {
	private static transient Method getDeleteCount;
	private static transient Method getInsertCount;
	private static transient Method getLoadCount;
	private static transient Method getUpdateCount;
	private static transient Method getFetchCount;
	private static transient Method getOptimisticFailureCount;

	static {
		try {
			Class<?> entityStatistics = Class.forName("org.hibernate.stat.EntityStatistics");//$NON-NLS-1$
			getDeleteCount            = entityStatistics.getMethod("getDeleteCount");//$NON-NLS-1$
			getInsertCount            = entityStatistics.getMethod("getDeleteCount");//$NON-NLS-1$
			getLoadCount              = entityStatistics.getMethod("getLoadCount"  );//$NON-NLS-1$
			getUpdateCount            = entityStatistics.getMethod("getUpdateCount");//$NON-NLS-1$
			getFetchCount             = entityStatistics.getMethod("getFetchCount" );//$NON-NLS-1$
			getOptimisticFailureCount = entityStatistics.getMethod("getOptimisticFailureCount");//$NON-NLS-1$
		} catch (NoSuchMethodException e) {
		} catch (ClassNotFoundException e) {
		} catch (SecurityException e) {
		}
	}

	public EntityStatistics(Serializable delegate) {
		super(delegate);
	}

	/**
	 * @see org.hibernate.stat.EntityStatistics#getDeleteCount()
	 */
	public long getDeleteCount() {
		return invokeLong(getDeleteCount, -1);
	}

	/**
	 * @see org.hibernate.stat.EntityStatistics#getInsertCount()
	 */
	public long getInsertCount() {
		return invokeLong(getInsertCount, -1);
	}

	/**
	 * @see org.hibernate.stat.EntityStatistics#getLoadCount()
	 */
	public long getLoadCount() {
		return invokeLong(getLoadCount, -1);
	}

	/**
	 * @see org.hibernate.stat.EntityStatistics#getUpdateCount()
	 */
	public long getUpdateCount() {
		return invokeLong(getUpdateCount, -1);
	}

	/**
	 * @see org.hibernate.stat.EntityStatistics#getFetchCount()
	 */
	public long getFetchCount() {
		return invokeLong(getFetchCount, -1);
	}

	/**
	 * @see org.hibernate.stat.EntityStatistics#getOptimisticFailureCount()
	 */
	public long getOptimisticFailureCount() {
		return invokeLong(getOptimisticFailureCount, -1);
	}

}
