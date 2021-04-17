package com.egreksystems.yoloveggies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    Thread Thread1 = null;
    String ip = "";
    int port;

    RecyclerView list;
    ArrayList<Fruit> fruits;
    ArrayList<String> displayed = new ArrayList<>();
    FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        list = findViewById(R.id.recyclerView);
        fruits = new ArrayList<>();

        Intent intent = getIntent();
        ip = intent.getStringExtra(MainActivity.IP_EXTRA);
        port = intent.getIntExtra(MainActivity.PORT_EXTRA, 12345);

        adapter = new FruitAdapter(this, fruits);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));

        Thread1 = new Thread(new Thread1());
        Thread1.start();
    }

    private PrintWriter output;
    private BufferedReader input;
    class Thread1 implements Runnable {
        public void run() {
            Socket socket;
            try {
                //Log.e("SERVER", ip);
                socket = new Socket(ip, port);

                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Log.e("SERVER", String.valueOf(socket.getPort()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                    }
                });
                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class Thread2 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();

                    Log.i("SERVER", "run: " + message);
                    if (message != null) {
                        final String[] msg = message.split(",");
                        if(!displayed.contains(msg[0])) {
                            Fruit f = new Fruit(msg[0].toUpperCase(), msg[0], msg[1]);
                            fruits.add(f);
                           
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(!displayed.contains(msg[0])) {
                                    adapter.notifyDataSetChanged();
                                    displayed.add(msg[0]);
                                }

//                                FruitAdapter adapter = new FruitAdapter(getApplicationContext(), fruits);
//                                list.setAdapter(adapter);
//                                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                //tvMessages.append("server: " + message + "\n");
                            }
                        });
                    } else {
                        Thread1 = new Thread(new Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Thread3 implements Runnable {
        private String message;
        Thread3(String message) {
            this.message = message;
        }
        @Override
        public void run() {
            output.write(message);
            output.flush();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    tvMessages.append("client: " + message + "\n");
//                    etMessage.setText("");
                }
            });
        }
    }
}