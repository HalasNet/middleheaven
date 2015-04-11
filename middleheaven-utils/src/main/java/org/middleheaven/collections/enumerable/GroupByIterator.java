/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.util.function.Function;

/**
 * 
 */
public class GroupByIterator<K,T> implements Iterator<GroupedElements<K, T>> {

	private Iterator<T> original;
	private Map<K, EditableGroupedElements> grouping = new HashMap<>();
	private Function<K, T> classificator;
	private Set<K> visitedSets = new HashSet<K>();
	private List<K> iterationKeys = new ArrayList<K>();
	private Element<EditableGroupedElements>next;
	private int currentKeyIndex = -1;

	/**
	 * Constructor.
	 * @param iterator
	 */
	public GroupByIterator(Iterator<T> original, Function<K, T> classificator) {
		this.original = original;
		this.classificator = classificator;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		Element<EditableGroupedElements> item = fetchNextKey();
		if (item.isFound()){
			this.next = item;
			return true;
		} 
		return false;
	}

	private Element<EditableGroupedElements> fetchNextKey(){
		while(original.hasNext()){
			T obj = original.next();
			K key = classificator.apply(obj);

			EditableGroupedElements elements = grouping.get(key);

			if (elements == null){
				elements =  new EditableGroupedElements(key);
				grouping.put(key, elements);
				iterationKeys.add(key);
			} 
			elements.add(obj);


			if (visitedSets.add(key)){
				// new key
				currentKeyIndex++;
				return new Element<EditableGroupedElements>(elements);
			}

		}

		if (++currentKeyIndex < iterationKeys.size()){
			K key = iterationKeys.get(currentKeyIndex);
			return new Element<EditableGroupedElements>(grouping.get(key));
		}
		return Element.noSuchElement();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroupedElements<K, T> next() {
		if (next == null){
			next = fetchNextKey();
		}
		GroupedElements<K, T> obj = next.object();
		next = null;
		return obj;

	}


	public class EditableGroupedElements extends AbstractBaseEnumerable<T> implements GroupedElements<K,T>{


		private K key;
		private List<T> list = new ArrayList<>();

		public EditableGroupedElements(K key){
			this.key = key;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public K getKey() {
			return key;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Enumerable<T> elements() {
			return this;
		}


		public void add(T element){
			list.add(element);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Iterator<T> iterator() {
			return new ElementIterator();
		}

		public class ElementIterator implements Iterator<T> {

			private Element<T> next;
			private int index = -1;
			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				Element<T> item = fetchNextElement();
				if (item.isFound()){
					this.next = item;
					return true;
				} 
				return false;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public T next() {
				if (next == null){
					next = fetchNextElement();
				}
				T obj = next.object();
				next = null;
				return obj;
			}

			/**
			 * @param index2
			 * @return
			 */
			private Element<T> fetchNextElement() {
				if (++index < list.size()){
					return new Element<T>(list.get(index));
				} else {
					while(original.hasNext()){
						T obj = original.next();
						K key = classificator.apply(obj);

						if (key.equals(EditableGroupedElements.this.key)){
							// the next element
							list.add(obj);
							return new Element<T>(obj);
						} else {
							// some element from another key
							EditableGroupedElements elements = grouping.get(key);

							if (elements == null){
								elements =  new EditableGroupedElements(key);
								grouping.put(key, elements);
								iterationKeys.add(key);
							} 
							elements.add(obj);
						}
					}
					return Element.noSuchElement();
				}

			}

		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public EnumerableSize getSizeInfo() {
			return new IterableBasedComputableEnumeratorSize(this);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public long count() throws InfiniteSizeException {
			throw new UnsupportedOperationException("Not implememented yet");
		}
	}
}
