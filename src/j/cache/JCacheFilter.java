package j.cache;

import java.io.Serializable;

/**
 * 
 * @author JFramework
 *
 */
public interface JCacheFilter extends Serializable{
	/**
	 * 判断某对象是否符合给定的条件
	 * @param object
	 * @return
	 */
	public boolean matches(Object object);
}