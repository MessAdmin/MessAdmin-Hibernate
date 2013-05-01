package clime.messadmin.hibernate.support;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

/**
 * Second level cache statistics of a specific region
 *
 * @author C&eacute;drik LIME
 */
public class SecondLevelCacheStatistics extends BaseStatisticsSupport implements Serializable {
	private static transient Method getHitCount;
	private static transient Method getMissCount;
	private static transient Method getPutCount;
	private static transient Method getElementCountInMemory;
	private static transient Method getElementCountOnDisk;
	private static transient Method getSizeInMemory;
	private static transient Method getEntries;

	static {
		try {
			Class secondLevelCacheStatistics = Class.forName("org.hibernate.stat.SecondLevelCacheStatistics");//$NON-NLS-1$
			getHitCount             = secondLevelCacheStatistics.getMethod("getHitCount",             null);//$NON-NLS-1$
			getMissCount            = secondLevelCacheStatistics.getMethod("getMissCount",            null);//$NON-NLS-1$
			getPutCount             = secondLevelCacheStatistics.getMethod("getPutCount",             null);//$NON-NLS-1$
			getElementCountInMemory = secondLevelCacheStatistics.getMethod("getElementCountInMemory", null);//$NON-NLS-1$
			getElementCountOnDisk   = secondLevelCacheStatistics.getMethod("getElementCountOnDisk",   null);//$NON-NLS-1$
			getSizeInMemory         = secondLevelCacheStatistics.getMethod("getSizeInMemory",         null);//$NON-NLS-1$
			getEntries              = secondLevelCacheStatistics.getMethod("getEntries",              null);//$NON-NLS-1$
		} catch (NoSuchMethodException e) {
		} catch (ClassNotFoundException e) {
		} catch (SecurityException e) {
		}
	}

	public SecondLevelCacheStatistics(Serializable delegate) {
		super(delegate);
	}

	/**
	 * @see org.hibernate.stat.SecondLevelCacheStatistics#getHitCount()
	 */
	public long getHitCount() {
		return invokeLong(getHitCount, -1);
	}

	/**
	 * @see org.hibernate.stat.SecondLevelCacheStatistics#getMissCount()
	 */
	public long getMissCount() {
		return invokeLong(getMissCount, -1);
	}

	/**
	 * @see org.hibernate.stat.SecondLevelCacheStatistics#getPutCount()
	 */
	public long getPutCount() {
		return invokeLong(getPutCount, -1);
	}

	/**
	 * @see org.hibernate.stat.SecondLevelCacheStatistics#getElementCountInMemory()
	 */
	public long getElementCountInMemory() {
		return invokeLong(getElementCountInMemory, -1);
	}

	/**
	 * @see org.hibernate.stat.SecondLevelCacheStatistics#getElementCountOnDisk()
	 */
	public long getElementCountOnDisk() {
		return invokeLong(getElementCountOnDisk, -1);
	}

	/**
	 * @see org.hibernate.stat.SecondLevelCacheStatistics#getSizeInMemory()
	 */
	public long getSizeInMemory() {
		return invokeLong(getSizeInMemory, -1);
	}

	/**
	 * @see org.hibernate.stat.SecondLevelCacheStatistics#getEntries()
	 */
	public Map getEntries() {
		return (Map) invoke(getEntries, Collections.EMPTY_MAP);
	}
}
