package co.eam.colpensiones.repositorio.listaNegra;

public interface IListaNegra<T> {

    public void add(T dato);

    public T get(int index);

    public void add(T dato, int index);

    public void remove(int index);

    public void remove(T dato);

    public int size();

    public boolean isEmpty();

    public int indexOf(T dato);


}
