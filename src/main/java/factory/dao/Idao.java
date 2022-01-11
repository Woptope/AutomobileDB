package factory.dao;

import java.util.List;

public interface Idao<T> {

        T get(int id);

        List<T> getAll();

        void add(T t);

        void update(T t);

        void delete(T t) ;

}
