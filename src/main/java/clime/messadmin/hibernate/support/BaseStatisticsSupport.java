/**
 *
 */
package clime.messadmin.hibernate.support;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @author C&eacute;drik LIME
 */
abstract class BaseStatisticsSupport implements Serializable {
	protected Serializable delegate;

	/**
	 *
	 */
	public BaseStatisticsSupport(Serializable delegate) {
		super();
		this.delegate = delegate;
	}

	/** {@inheritDoc} */
	public String toString() {
		return delegate != null ? delegate.toString() : super.toString();
	}

	/** {@inheritDoc} */
	public boolean equals(Object obj) {
		return delegate != null ? delegate.equals(obj) : super.equals(obj);
	}

	/** {@inheritDoc} */
	public int hashCode() {
		return delegate != null ? delegate.hashCode() : super.hashCode();
	}

//	/** {@inheritDoc} */
//	protected Object clone() throws CloneNotSupportedException {
//		return delegate instanceof Cloneable ? ((Cloneable)delegate).clone() : super.clone();
//	}

	protected Object invoke(Method method, Object defaultValue) {
		if (method == null || delegate == null) {
			return defaultValue;
		}
		try {
			return method.invoke(delegate, null);
		} catch (Exception ignore) {
			return defaultValue;
		}
	}

	protected long invokeLong(Method method, long defaultValue) {
		if (method == null || delegate == null) {
			return defaultValue;
		}
		try {
			return ((Long) method.invoke(delegate, null)).longValue();
		} catch (Exception ignore) {
			return defaultValue;
		}
	}
}
