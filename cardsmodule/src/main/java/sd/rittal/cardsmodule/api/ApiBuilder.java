package sd.rittal.cardsmodule.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiBuilder {

    private static Retrofit retrofit;

    public static ApiService callAPI(String url) {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();


        if (retrofit == null) {

            retrofit = new Retrofit.Builder().client(client).baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit.create(ApiService.class);
    }


}

