package com.example.tareaconsumowebservice;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.tareaconsumowebservice.webservice.Asynchtask;
import com.example.tareaconsumowebservice.webservice.WebService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity implements Asynchtask {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void processFinish(String result) throws JSONException {
        TextView Verusuarios = findViewById(R.id.Verusuarios);
        String lista = "";
        JSONArray jsonArray = new JSONArray(result);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject usuario = jsonArray.getJSONObject(i);
            String email = usuario.getString("email");
            lista += "Email: " + email + "\n\n";
        }
        Verusuarios.setText(lista);
    }
    public void Enviar(View view) {
        Map<String, String> datos = new HashMap<>();
        WebService ws = new WebService("https://jsonplaceholder.typicode.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }
}