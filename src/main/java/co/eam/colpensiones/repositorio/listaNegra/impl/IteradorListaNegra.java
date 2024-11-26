package co.eam.colpensiones.repositorio.listaNegra.impl;

import co.eam.colpensiones.util.Nodo;

import java.util.Iterator;

public class IteradorListaNegra<T> implements Iterator<T> {

    private Nodo<T> actual;

    public IteradorListaNegra(Nodo<T> actual) {
        this.actual = actual;
    }

    public boolean hasNext() {
        return this.actual != null;
    }

    public T next() {
        if (hasNext()) {
            T dato = actual.getDato();
            actual = actual.getNodoSiguiente();
            return dato;
        }
        try {
            throw new Exception("No se puede acceder al elemento");
        } catch (Exception ex) {
            ex.getMessage();
        }
        return null;
    }

}
