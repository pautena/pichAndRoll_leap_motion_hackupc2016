import com.google.gson.JsonObject;
import retrofit.GsonConverterFactory;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Duffman on 20/2/16.
 */
public class ServerAdapter {
    private static final String TAG="ServerAdapter";

    private static ServerAdapter instance;
    public static ServerAdapter getInstance(){
        if(instance==null) instance = new ServerAdapter();
        return instance;
    }

    private ServerService service;


    private ServerAdapter(){
        String ip="192.168.43.107";
        int port = 8080;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+ip+":"+port+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ServerService.class);
    }

    public void reset(){
        System.out.println(TAG+" ----- REQUEST: reset");
        Call<JsonObject> call = service.reset();

        call.enqueue(new Callback<JsonObject>() {
            public void onResponse(Response<JsonObject> response) {
                System.out.println(TAG+" ----- onResponse code: " + response.code());

                if (response.code() == 200) {
                    System.out.println(TAG+" ----- onResponse body: " + response.body());
                } else {
                    System.out.println(TAG+" ----- onResponse errorBody: " + response.body());
                }
            }

            public void onFailure(Throwable throwable) {
                System.out.println(TAG+" ----- onFailure. message: " + throwable.getMessage());

            }
        });

    }

    public void sendDegrees(float pitch, float roll) {
        pitch *= -1;
        //TODO: recalibrate roll

        if(pitch<5) pitch=5;
        else if (pitch>85) pitch = 85;

        if(roll<10) roll = 10;
        else if (roll>170) roll = 170;

        System.out.println(TAG+" ----- REQUEST: sendDegrees. pitch: "+pitch+", roll: "+roll);
        Call<JsonObject> call = service.degrees(pitch, roll);
        call.enqueue(new Callback<JsonObject>() {
            public void onResponse(Response<JsonObject> response) {
                System.out.println(TAG+" ----- onResponse code: " + response.code());

                if (response.code() == 200) {
                    System.out.println(TAG+" ----- onResponse body: " + response.body());
                } else {
                    System.out.println(TAG+" ----- onResponse errorBody: " + response.body());
                }
            }

            public void onFailure(Throwable throwable) {
                System.out.println(TAG+" ----- onFailure. message: " + throwable.getMessage());

            }
        });
    }

    public void sendAction(MoveAction action) {
        System.out.println(TAG+" ----- REQUEST: sendAction");
        Call<JsonObject> call = service.action(action.getValue());
        call.enqueue(new Callback<JsonObject>() {
            public void onResponse(Response<JsonObject> response) {
                System.out.println(TAG+" ----- onResponse code: " + response.code());

                if (response.code() == 200) {
                    System.out.println(TAG+" ----- onResponse body: " + response.body());
                } else {
                    System.out.println(TAG+" ----- onResponse errorBody: " + response.body());
                }
            }

            public void onFailure(Throwable t) {
                System.out.println(TAG+" ----- onFailure. message: " + t.getMessage());
            }
        });
    }
}
