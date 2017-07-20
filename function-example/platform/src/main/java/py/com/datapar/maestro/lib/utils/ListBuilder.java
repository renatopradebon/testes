package py.com.datapar.maestro.lib.utils;

import java.util.ArrayList;
import java.util.List;

public class ListBuilder<T> {

    private ArrayList<T> lista;

    public ListBuilder() {
        lista = new ArrayList<>();
    }


    public ListBuilder(List<T> lista) {
        if (lista == null)
            lista = new ArrayList<>();

        this.lista = (ArrayList<T>) lista;
    }

    public ListBuilder<T> add(T entity) {
        lista.add(entity);

        return this;

    }

    public List<T> toList() {
        return lista;
    }
}
