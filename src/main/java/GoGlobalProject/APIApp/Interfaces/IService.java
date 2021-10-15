package GoGlobalProject.APIApp.Interfaces;

import java.util.List;

public interface IService<T> {

    T GetById(long id);

    List<T> GetAll();

    boolean Create(T t);

    boolean Update(long tId, T tDetails);

    void Delete(long id);
}
