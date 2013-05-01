package clime.messadmin.hibernate.support;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Collection related statistics
 *
 * @author C&eacute;drik LIME
 */
public class CollectionStatistics extends BaseStatisticsSupport implements Serializable {
	private static transient Method getLoadCount;
	private static transient Method getFetchCount;
	private static transient Method getRecreateCount;
	private static transient Method getRemoveCount;
	private static transient Method getUpdateCount;

	static {
		try {
			Class collectionStatistics = Class.forName("org.hibernate.stat.CollectionStatistics");//$NON-NLS-1$
			getLoadCount     = collectionStatistics.getMethod("getLoadCount",     null);//$NON-NLS-1$
			getFetchCount    = collectionStatistics.getMethod("getFetchCount",    null);//$NON-NLS-1$
			getRecreateCount = collectionStatistics.getMethod("getRecreateCount", null);//$NON-NLS-1$
			getRemoveCount   = collectionStatistics.getMethod("getRemoveCount",   null);//$NON-NLS-1$
			getUpdateCount   = collectionStatistics.getMethod("getUpdateCount",   null);//$NON-NLS-1$
		} catch (NoSuchMethodException e) {
		} catch (ClassNotFoundException e) {
		} catch (SecurityException e) {
		}
	}

	public CollectionStatistics(Serializable delegate) {
		super(delegate);
	}

	/**
	 * @see org.hibernate.stat.CollectionStatistics#getLoadCount()
	 */
	public long getLoadCount() {
		return invokeLong(getLoadCount, -1);
	}

	/**
	 * @see org.hibernate.stat.CollectionStatistics#getFetchCount()
	 */
	public long getFetchCount() {
		return invokeLong(getFetchCount, -1);
	}

	/**
	 * @see org.hibernate.stat.CollectionStatistics#getRecreateCount()
	 */
	public long getRecreateCount() {
		return invokeLong(getRecreateCount, -1);
	}

	/**
	 * @see org.hibernate.stat.CollectionStatistics#getRemoveCount()
	 */
	public long getRemoveCount() {
		return invokeLong(getRemoveCount, -1);
	}

	/**
	 * @see org.hibernate.stat.CollectionStatistics#getUpdateCount()
	 */
	public long getUpdateCount() {
		return invokeLong(getUpdateCount, -1);
	}
}
