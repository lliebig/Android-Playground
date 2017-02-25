package de.leoliebig.playground.data.net.clients;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import de.leoliebig.playground.data.DataSource;
import de.leoliebig.playground.data.net.apis.FakeUserService;
import de.leoliebig.playground.data.net.models.User;
import de.leoliebig.playground.utils.JsonHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Loads fake user data from <a href="https://reqres.in/">https://reqres.in</a>.
 * <p>
 * Created by Leo on 25.02.2017.
 */
public class UserServiceClient implements DataSource<User> {

    private FakeUserService userService;

    public UserServiceClient() {

        ObjectMapper mapper = JsonHelper.getObjectMapperInstance(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FakeUserService.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();

        userService = retrofit.create(FakeUserService.class);

    }

    @Override
    public void load(final long id, final DataSource.LoadListener listener) {
        userService.getSingleUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                listener.onLoaded(id, response.body(), response.code());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onLoadError(id, t);
            }
        });
    }

    /**
     * Not implemented yet
     *
     * @param page
     * @param listener
     * @throws UnsupportedOperationException
     */
    @Override
    public void loadList(int page, DataSource.LoadListener listener) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Not implemented yet
     *
     * @param data
     * @param listener
     * @throws UnsupportedOperationException
     */
    @Override
    public void save(User data, DataSource.SaveListener listener) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Not implemented yet
     *
     * @param data
     * @param listener
     * @throws UnsupportedOperationException
     */
    @Override
    public void saveList(List<User> data, DataSource.SaveListener listener) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Not implemented yet
     *
     * @param id
     * @param listener
     * @throws UnsupportedOperationException
     */
    @Override
    public void delete(long id, DataSource.DeleteListener listener) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Not implemented yet
     *
     * @param ids
     * @param listener
     * @throws UnsupportedOperationException
     */
    @Override
    public void deleteList(List<Long> ids, DataSource.DeleteListener listener) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
