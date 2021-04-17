package com.egreksystems.yoloveggies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etIP, etPort;
    String SERVER_IP;
    int SERVER_PORT;
    public static String IP_EXTRA = "ip";
    public static String PORT_EXTRA = "port";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etIP = findViewById(R.id.etIP);
        etPort = findViewById(R.id.etPort);

        Button btnConnect = findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SERVER_IP = etIP.getText().toString().trim();
                SERVER_PORT = Integer.parseInt(etPort.getText().toString().trim());

                Intent i = new Intent(getApplicationContext(), ListActivity.class);
                //Change the activity.
                i.putExtra(IP_EXTRA, SERVER_IP); //this will be received at ledControl (class) Activity
                i.putExtra(PORT_EXTRA, SERVER_PORT);
                startActivity(i);

            }
        });
    }
}