package GoGlobalProject.APIApp.Interfaces;

import java.util.List;

public interface IService<T> {

    T GetById(long id);

    List<T> GetAll();

    boolean Create(T t);

    boolean Update(T tOriginal, T tDetails);

    void Delete(long id);
}
