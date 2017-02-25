package de.leoliebig.playground.data.net.apis;

import java.util.List;

import de.leoliebig.playground.data.net.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * HTTP API interface for <a href="https://reqres.in/">https://reqres.in</a>.
 * Created by Leo on 25.02.2017.
 */
public interface FakeUserService {

    public static final String BASE_URL = "https://reqres.in/";

    @GET("/api/users?page={pageNumber}")
    Call<List<User>> getUsers(@Path("pageNumber") int pageNumber);

    @GET("/api/users/{userId}")
    Call<User> getSingleUser(@Path("userId") long userId);

}
