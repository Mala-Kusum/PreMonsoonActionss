package com.example.premonsoonaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    Button login;
    public static String RO;
    private FirebaseFirestore db;
    private CollectionReference Ref;
    EditText id,password;
    Query query;
    String email,pswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=findViewById(R.id.id);
        password=findViewById(R.id.pswd);
        login=findViewById(R.id.login);
        db = FirebaseFirestore.getInstance();
        Ref = db.collection("accounts");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = id.getText().toString();
                pswd = password.getText().toString();
                query = Ref.whereEqualTo("EMAIL", email).whereEqualTo("PASSWORD", pswd);
                query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Wrong ID or Pin", Toast.LENGTH_SHORT).show();
                        } else {
                            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                                if (doc.getData().get("TYPE").toString().equals(new String("HQ"))) {
                                    RO="HQ";
                                    Intent i=new Intent(MainActivity.this,ROs.class);
                                    startActivity(i);
                                } else {
                                    RO = doc.getData().get("TYPE").toString();
                                    Intent intent = new Intent(MainActivity.this, Action.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("mainActivity ", "inquery");
                        //Log.e("tage", e.toString());
                    }
                });
            }
        });
    }
}