package co.eam.colpensiones.repositorio.listaNegra.impl;

import co.eam.colpensiones.repositorio.listaNegra.IListaNegra;
import co.eam.colpensiones.util.Nodo;

import java.util.Iterator;

public class ListaNegra<T> implements IListaNegra<T>, Iterable<T> {
    private Nodo<T> primero;
    private int size;

    public ListaNegra() {
        this.primero = null;
        this.size = 0;
    }

    @Override
    public void add(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo<T> actual = primero;
            while (actual.getNodoSiguiente() != null) {
                actual = actual.getNodoSiguiente();
            }
            actual.setNodoSiguiente(nuevo);
        }
        size++;
    }

    @Override
    public T get(int index) {
        validarIndice(index);
        Nodo<T> actual = primero;
        int contador = 0;
        while (contador < index) {
            actual = actual.getNodoSiguiente();
            contador++;
        }
        return actual.getDato();
    }

    @Override
    public void add(T dato, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        if (index == 0) {
            nuevo.setNodoSiguiente(primero);
            primero = nuevo;
        } else {
            Nodo<T> actual = primero;
            int contador = 0;
            while (contador < index - 1) {
                actual = actual.getNodoSiguiente();
                contador++;
            }
            nuevo.setNodoSiguiente(actual.getNodoSiguiente());
            actual.setNodoSiguiente(nuevo);
        }
        size++;
    }

    @Override
    public void remove(int index) {
        validarIndice(index);
        if (index == 0) {
            primero = primero.getNodoSiguiente();
        } else {
            Nodo<T> actual = primero;
            int contador = 0;
            while (contador < index - 1) {
                actual = actual.getNodoSiguiente();
                contador++;
            }
            actual.setNodoSiguiente(actual.getNodoSiguiente().getNodoSiguiente());
        }
        size--;
    }

    @Override
    public void remove(T dato) {
        if (primero == null) return;

        if (primero.getDato().equals(dato)) {
            primero = primero.getNodoSiguiente();
            size--;
            return;
        }

        Nodo<T> actual = primero;
        while (actual.getNodoSiguiente() != null && !actual.getNodoSiguiente().getDato().equals(dato)) {
            actual = actual.getNodoSiguiente();
        }

        if (actual.getNodoSiguiente() != null) {
            actual.setNodoSiguiente(actual.getNodoSiguiente().getNodoSiguiente());
            size--;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(T dato) {
        Nodo<T> actual = primero;
        int index = 0;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return index;
            }
            actual = actual.getNodoSiguiente();
            index++;
        }
        return -1;
    }

    private void validarIndice(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorListaNegra<>(this.primero);
    }
}
