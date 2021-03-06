package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import com.cooksys.ftd.assignments.collections.model.WageSlave;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	private FatCat FatCat;
	//private HashSet<Capitalist> kids = new HashSet<Capitalist>();
	private HashSet<Capitalist> hs = new HashSet<Capitalist>();
    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false^^^
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Capitalist capitalist) {
        if(hs.contains(capitalist)|| capitalist == null)
    		return false;
        
        if(capitalist.hasParent()) {
        		this.add(capitalist.getParent());
        		hs.add(capitalist.getParent());
        		return hs.add(capitalist);
        	
        }
        
        else if(!this.getChildren(FatCat).isEmpty()) {
        	return hs.add(capitalist);
        }
        
        else if(capitalist.getClass() == FatCat.class){
        	return hs.add(capitalist);
        }
        else 
        	return false;
    }

    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
    	return(hs.contains(capitalist));
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
        return (Set<Capitalist>) hs.clone();
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
    	Set<FatCat> rents = new HashSet<FatCat>();
       	for(Capitalist cap : hs) {
       		if(cap instanceof FatCat)
       			rents.add((FatCat)cap);
       	}
    	return new HashSet<FatCat>(rents);
    	//return hm.keySet();
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
    	HashSet<Capitalist> kids = new HashSet<Capitalist>();
    	//kids.clear();
    	for(Capitalist i: hs){
    		if(i.hasParent()) {
    			if(i.getParent() == fatCat)
    				kids.add(i);
    		}
    	}
    	return (Set<Capitalist>) kids.clone();
    }

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
    	HashMap<FatCat, Set<Capitalist>> hm = new HashMap<FatCat, Set<Capitalist>>();
    	for(FatCat capAmerica : this.getParents())
    	{
    		hm.put(capAmerica, this.getChildren(capAmerica));
    	}
    	return new HashMap<FatCat, Set<Capitalist>>(hm);
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
    	ArrayList<FatCat> al= new ArrayList<FatCat>();
    	Capitalist it = capitalist;

        if(capitalist != null && !hs.isEmpty())
        {
        	while(it.hasParent())
        	{
        		al.add(it.getParent());
        		it = it.getParent();
        	}
        }
        return al;
    }
}
