package org.middleheaven.collections.tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.middleheaven.collections.enumerable.AbstractIterableEnumerable;
import org.middleheaven.collections.enumerable.InfiniteSizeException;
import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;
import org.middleheaven.util.function.Block;


public class LinkedTreeNode<E> extends AbstractIterableEnumerable<TreeNode<E>> implements TreeNode<E>{

	private List<TreeNode<E>> nodes = new LinkedList<TreeNode<E>>();
	E value;
	private TreeNode<E> parent;
	
	public void addChild(TreeNode<E> node){
		nodes.add(node);
	}
	
	public void removeChild(TreeNode<E> node){
		nodes.remove(node);
	}
	
	public Iterator<TreeNode<E>> iterator(){
		return nodes.iterator();
	}
	
	public E getValue(){
		return value;
	}
	
	public void setValue(E value){
		this.value = value;
	}

	@Override
	public int childCount() {
		return nodes.size();
	}

	@Override
	public EnumerableSize getSizeInfo() {
		return IntPreciseEnumerableSize.of(nodes.size());
	}
	
	@Override
	public Collection<TreeNode<E>> children() {
		return nodes;
	}

	@Override
	public TreeNode<E> getParent() {
		return this.parent;
	}

	@Override
	public boolean isEmpty() {
		return this.nodes.isEmpty();
	}

	@Override
	public void setParent(TreeNode<E> parent) {
		if(this.parent !=null && !this.parent.equals(parent)){
			throw new IllegalStateException("Parent already set");
		}
		this.parent = parent;
	}

	@Override
	public void forEachParent(Block<TreeNode<E>> walker) {
		if (parent != null){
			walker.apply(this.parent);
			parent.forEachParent(walker);
		}
	}

	@Override
	public void forEachRecursive(Block<TreeNode<E>> block) {
		for (TreeNode<E> t : this){
			block.apply(t);
			t.forEachRecursive(block);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		 return nodes.size() == 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return nodes.size();
	}
	

}
