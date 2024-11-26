package co.eam.colpensiones.repositorio.queue;

public interface IQueue <T> {

  public void enQueue(T dato);

  public T deQueue();

  public T peek();

  public boolean isEmpty();
}
