package j.util;

import j.common.JObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author 肖炯
 * 
 */
public class ConcurrentList<E> implements List<E>, Serializable {
	private static final long serialVersionUID = 1L;
	private JObject lock = null;

	private List container = null;// 实际数据存储对象
	private int total=-1;//当container为某个子集时，total表示父集元素总数
	
	/**
	 * 
	 * 
	 */
	public ConcurrentList() {
		lock = new JObject();
		container = new ArrayList<E>();
	}

	/**
	 * 同步锁，即实际存储数据的LinkedList对象，当外部程序需要与该LinkedList对象的操作同步的话，需通过getLock()得到锁
	 * 
	 * @return
	 */
	public Object getLock() {
		return lock;
	}
	
	/**
	 * 
	 * @param total
	 */
	public void setTotal(int total){
		this.total=total;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getTotal(){
		return this.total==-1?this.size():this.total;
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public boolean add(E e) {
		synchronized(lock){
			boolean r= container.add(e);
			return r;
		}
	}

	/**
	 * 
	 * @param index
	 * @param element
	 */
	public void add(int index, Object element) {
		synchronized(lock){
			container.add(index,element);
		}
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean addAll(Collection c) {
		synchronized(lock){
			boolean r= container.addAll(c);
			return r;
		}
	}

	/**
	 * 
	 * @param index
	 * @param c
	 * @return
	 */
	public boolean addAll(int index, Collection c) {
		synchronized(lock){
			boolean r= container.addAll(index,c);
			return r;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#clear()
	 */
	public void clear() {
		synchronized (lock) {
			container.clear();
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		synchronized (lock) {
			return container.contains(o);
		}
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean containsAll(Collection c) {
		synchronized(lock){
			return container.containsAll(c);
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	public E get(int index) {
		try{
			synchronized(lock){
				return (E)container.get(index);
			}
		}catch(Exception e){
			return null;
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	public int indexOf(Object o) {
		synchronized(lock){
			return container.indexOf(o);
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty() {
		synchronized(lock){
			return container.isEmpty();
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator iterator() {
		synchronized(lock){
			return container.iterator();
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf(Object o) {
		synchronized(lock){
			return container.lastIndexOf(o);
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	public ListIterator listIterator() {
		synchronized(lock){
			return container.listIterator();
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	public ListIterator listIterator(int index) {
		synchronized(lock){
			return container.listIterator(index);
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	public E remove(int index) {
		try{
			synchronized(lock){
				return (E)container.remove(index);
			}
		}catch(Exception e){
			return null;
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		synchronized(lock){
			boolean r= container.remove(o);
			return r;
		}
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean removeAll(Collection c) {
		synchronized(lock){
			boolean r= container.removeAll(c);
			return r;
		}
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean retainAll(Collection c) {
		synchronized(lock){
			boolean r= container.retainAll(c);
			return r;
		}
	}

	/**
	 * 
	 * @param index
	 * @param element
	 * @return
	 */
	public E set(int index, Object element) {
		synchronized(lock){
			return (E)container.set(index,element);
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.Collection#size()
	 */
	public int size() {
		synchronized(lock){
			return container.size();
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	public List<E> subList(int fromIndex, int toIndex) {
		synchronized(lock){
			return container.subList(fromIndex,toIndex);
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see java.util.Collection#toArray()
	 */
	public E[] toArray() {
		synchronized(lock){
			return (E[])container.toArray();
		}
	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	public E[] toArray(Object[] a) {
		synchronized(lock){
			return (E[])container.toArray(a);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ConcurrentList<E> snapshot() {
		ConcurrentList<E> _snapshot=new ConcurrentList<E>();
		synchronized(lock){
			_snapshot.addAll(container);
		}
		return _snapshot;
	}
	
	public static void main(String[] args) {
		ConcurrentList lst=new ConcurrentList();
		lst.add("aaa");
		lst.add("bbb");
		
		ConcurrentList lst2=lst.snapshot();
		lst.remove(0);
		
		System.out.println(lst.size());
		System.out.println(lst2.size());
	}
}
