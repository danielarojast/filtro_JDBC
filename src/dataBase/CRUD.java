package dataBase;

import java.util.List;

public interface CRUD {
    public Object insert(Object obj);
    public List<Object> findAll();

    public boolean update(Object obj);

    public boolean delete(Object obj);
}



/*Si queremos agregar una sola columna, podemos usar la sintaxis siguiente: mysql> alter table personal add capital int not null -> after nom; Este formato de alter table permite, además, insertar las columnas antes (before) o después (after) de una columna en cuestión.*/
