package com.example.premonsoonaction.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.premonsoonaction.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {
    Button login;
    public static String RO,pmu;
    public static boolean HQ=false,ro=false,PMU=false;
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
               /* email = id.getText().toString();
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
                                    HQ=true;
                                    ro=false;
                                    PMU=false;
                                    Intent i=new Intent(MainActivity.this,ROs.class);
                                    startActivity(i);
                                } else if(doc.getData().get("TYPE").toString().contains("RO")){
                                    RO = doc.getData().get("TYPE").toString();
                                    HQ=false;
                                    ro=true;
                                    PMU=false;
                                    Intent intent = new Intent(MainActivity.this, PMUs.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    pmu = doc.getData().get("TYPE").toString();
                                    HQ=false;
                                    ro=false;
                                    PMU=true;
                                    Intent intent = new Intent(MainActivity.this, PMUWise.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener()                                                               {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("mainActivity ", "inquery");
                        //Log.e("tage", e.toString());
                    }
                });*/
                Intent i=new Intent(MainActivity.this, ROs.class);
                startActivity(i);
            }
        });
    }
}