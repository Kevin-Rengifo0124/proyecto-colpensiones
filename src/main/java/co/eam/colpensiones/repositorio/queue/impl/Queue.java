package co.eam.colpensiones.repositorio.queue.impl;

import co.eam.colpensiones.repositorio.queue.IQueue;
import co.eam.colpensiones.util.Nodo;

import java.io.Serializable;

public class Queue <T> implements IQueue<T>, Serializable {

  Nodo<T> primero;

  public Queue(){
    this.primero = null;
  }

  @Override
  public void enQueue(T dato) {
    Nodo<T> nuevo = new Nodo(dato);
    if(isEmpty()){
      primero = nuevo;
    }else{
      Nodo<T> observador = primero;
      while(observador.getNodoSiguiente() != null){
        observador = observador.getNodoSiguiente();
      }
      observador.setNodoSiguiente(nuevo);
    }
  }

  @Override
  public T deQueue() {
    if(!isEmpty()){
      T retorno = primero.getDato();
      primero = primero.getNodoSiguiente();
      return retorno;
    }
    throw new RuntimeException("La cola está vacía");
  }

  @Override
  public T peek() {
    if(!isEmpty()){
      return primero.getDato();
    }
    throw new RuntimeException("La cola está vacía");
  }

  @Override
  public boolean isEmpty() {
    return primero == null;
  }
}
