package co.eam.colpensiones.util;

public interface InterfaceQueue<T> {
    public void enQueue(T dato);

    public T deQueue();

    public T peek();

    public boolean isEmpty();
}
