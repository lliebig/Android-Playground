package de.leoliebig.playground.data;


import java.util.List;

/**
 * Defines the basic operations of a data source. Use this interface to encapsulate a data source
 * and the technology used to access it.
 * <p>
 * Created by Leo on 25.02.2017.
 */
public interface DataSource<T> {

    void load(long id, LoadListener listener);

    void loadList(int page, LoadListener listener);

    void save(T data, SaveListener listener);

    void saveList(List<T> data, SaveListener listener);

    void delete(long id, DeleteListener listener);

    void deleteList(List<Long> ids, DeleteListener listener);

    interface LoadListener<T> {
        void onLoaded(long id, T data, int resultCode);

        void onListLoaded(int page, List<T> data, int resultCode);

        void onLoadError(long id, Throwable t);

        void onListLoadError(int page, Throwable t);
    }

    interface SaveListener<T> {
        void onSaved(T data, int resultCode);

        void onListSaved(List<T> data, int resultCode);

        void onSaveError(T data, Throwable t);

        void onListSaveError(List<T> data, Throwable t);
    }

    interface DeleteListener<T> {
        void onDeleted(long id, int resultCode);

        void onListDeleted(List<Long> ids, int resultCode);

        void onDeleteError(long id, Throwable t);

        void onListDeleteError(List<Long> ids, Throwable t);
    }

}
