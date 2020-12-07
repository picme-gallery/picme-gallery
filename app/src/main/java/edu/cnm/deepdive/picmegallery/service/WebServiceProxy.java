package edu.cnm.deepdive.picmegallery.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.picmegallery.BuildConfig;
import edu.cnm.deepdive.picmegallery.model.Event;
import edu.cnm.deepdive.picmegallery.model.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceProxy {

  @GET("users/me")
  Single<User> getProfile(@Header("Authorization") String bearerToken);

  @GET("events/{id}")
  Single<Event> getEvent(@Header("Authorization") String bearerToken, @Header("Passkey") String passkey, @Path("id") long id);

  @GET("events/{id}")
  Single<Event> getOwnEvent(@Header("Authorization") String bearerToken,  @Path("id") long id);

  @POST
  Completable createEvent(@Body Event event);





  static WebServiceProxy getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {
    private static final WebServiceProxy INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(WebServiceProxy.class);
    }
  }

}
