package co.eam.colpensiones.util;

import java.io.Serializable;

public class Nodo<T> implements Serializable {

  private T dato;
  private Nodo<T> nodoSiguiente;

  public Nodo(T dato) {
    this.dato = dato;
    nodoSiguiente = null;
  }

  public T getDato() {
    return dato;
  }

  public void setDato(T dato) {
    this.dato = dato;
  }

  public Nodo<T> getNodoSiguiente() {
    return nodoSiguiente;
  }

  public void setNodoSiguiente(Nodo<T> nodoSiguiente) {
    this.nodoSiguiente = nodoSiguiente;
  }
}
