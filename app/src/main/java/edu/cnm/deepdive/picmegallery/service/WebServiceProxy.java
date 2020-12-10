package edu.cnm.deepdive.picmegallery.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.picmegallery.BuildConfig;
import edu.cnm.deepdive.picmegallery.model.Event;
import edu.cnm.deepdive.picmegallery.model.Photo;
import edu.cnm.deepdive.picmegallery.model.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * This class works as a proxy to the server side.
 */
public interface WebServiceProxy {

  @GET("users/me")
  Single<User> getProfile(@Header("Authorization") String bearerToken);

  @GET("events/{id}")
  Single<Event> getEvent(@Header("Authorization") String bearerToken, @Header("Passkey") String passkey, @Path("id") long id);

  @GET("events/{id}")
  Single<Event> getOwnEvent(@Header("Authorization") String bearerToken, @Header("Creator") boolean creator,  @Path("id") long id);

  @GET("events")
  Single<List<Event>> getUserEvents(@Header("Authorization") String bearerToken);

  @GET("events/{id}/photos")
  Single<List<Photo>> getAllImages(@Header("Authorization") String bearerToken);

  @POST("events")
  Single<Event> createEvent(@Header("Authorization") String bearerToken, @Body Event event);

  @Multipart
  @POST("events/{id}/photos")
  Single<Photo> post(@Header("Authorization") String bearerToken, @Part MultipartBody.Part file);




  /**
   * This is a getter for Instance.
   */
  static WebServiceProxy getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * This nested class is the retrofit Gson builder.
   */
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
