package pkgUtil;

import java.util.List;

public interface API <E> {

		public void addAll(List<E> arrayList);
		public E advanceCurrent();
	    public void clear();
	    public void delete(E element);
	    public void deleteFromBeginning();
	    public void deleteFromEnd();
	    public E getCurrent();
	    public List<E> getItemsInOrder();
	    public int getRounds();
	    public int getSize();
	    public void newHead(E element);
	    public void placeAtBeginning(E element);
	    public void add(E element);
	    public void setCurrent(E element);
	    
	}