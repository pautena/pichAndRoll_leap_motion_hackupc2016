import com.google.gson.JsonObject;
import retrofit.Call;
import retrofit.http.*;

/**
 * Created by Duffman on 20/2/16.
 */
public interface ServerService {

    @GET("reset")
    Call<JsonObject> reset();

    @GET("rotateservo/{x}/{y}")
    Call<JsonObject> degrees(@Path("x") float pitch, @Path("y") float roll);

    @GET("move/{action}")
    Call<JsonObject> action(@Path("action") int action);
}
