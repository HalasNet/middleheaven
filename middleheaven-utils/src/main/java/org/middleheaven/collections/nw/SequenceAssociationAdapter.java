/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.middleheaven.collections.enumerable.Enumerable;

/**
 * 
 */
public class SequenceAssociationAdapter<T> implements Association<Integer, T> {

	private Sequence<T> sequence;

	/**
	 * Constructor.
	 * @param arraySequence
	 */
	public SequenceAssociationAdapter(Sequence<T> sequence) {
		this.sequence = sequence;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return sequence.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return sequence.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<Association.Entry<Integer, T>> entries() {
		return sequence.entries().map(i -> PairAssociationEntry.of(i.getIndex(), i.getValue()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<T> tryGet(Integer key) {
		return sequence.tryGet(key.intValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(Integer key) {
		return sequence.get(key.intValue());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Association<Integer, T> associate(Integer key, T value) {
		
		Map<Integer, T> map = new HashMap<>();
		for (Sequence.Entry<T> entry : sequence.entries()){
			map.put(entry.getIndex(), entry.getValue());
		}
		
		map.put(key, value);
		
		return new DictionaryAssociation<>(map);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<org.middleheaven.collections.nw.Association.Entry<Integer, T>> iterator() {
		return entries().iterator();
	}


	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean contains(Object object) {
		if( object instanceof Association.Entry)
		{
			Association.Entry entry = (Association.Entry)object;
			if (entry.getKey() instanceof Integer){
				Object value = this.sequence.get(((Integer)entry.getValue()).intValue());
				return value == null ? value == entry.getValue() : value.equals(entry.getValue()); 
			}
		}
		return false;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsKey(Object key) {
		if( key instanceof Integer){
			int index = ((Integer)key).intValue();
			return index >=0 && index < sequence.size();
		}
		return false;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsValue(Object value) {
		return sequence.contains(value);
	}




}
