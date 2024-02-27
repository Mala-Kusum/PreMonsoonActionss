package com.example.premonsoonaction.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.premonsoonaction.Models.ModelEquipment;
import com.example.premonsoonaction.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class Add_Material extends AppCompatActivity {
    Spinner mat;
    ImageView i;
    ArrayAdapter<CharSequence> ad1;
    private CollectionReference Ref;
    EditText t;
    String name, n;
    Button save;
    Query querya;
    Dialog customDialog;
    String[] locs;
    ModelEquipment me;
    int j;
    private String loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);
        j = 0;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Ref = db.collection("materials");

        t = findViewById(R.id.no);
        save = findViewById(R.id.save);
        save.setEnabled(false);
        i = findViewById(R.id.addloc);
        mat = findViewById(R.id.Type);
        loc= MainActivity.pmu;

        ad1 = ArrayAdapter.createFromResource(Add_Material.this, R.array.Materials, android.R.layout.simple_spinner_item);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mat.setAdapter(ad1);

        mat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                name = (String) adapterView.getItemAtPosition(i);
                me.setName(name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                View selectedView = mat.getSelectedView();
                if (selectedView instanceof TextView) {
                    mat.requestFocus();
                    TextView selectedTextView = (TextView) selectedView;
                    selectedTextView.setError("error"); // any name of the error will do
                    selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
                    selectedTextView.setText("Please select an option"); // actual error message
                    mat.performClick(); // to open the spinner list if error is found.
                    save.setEnabled(false);
                }
            }
        });

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog = new Dialog(Add_Material.this);
                customDialog.setContentView(R.layout.dialog);
                customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                Button sb = customDialog.findViewById(R.id.save);
                sb.setEnabled(false);
                EditText e1, e2;
                e1 = customDialog.findViewById(R.id.ownership);
                e2 = customDialog.findViewById(R.id.Location);
                e1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (e1.getText().toString().trim().length() == 0) {
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }
                });
                e2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (e2.getText().toString().trim().length() == 0) {
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }
                });
                customDialog.show();
                sb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s;
                        s = e1.getText().toString() + "," + e2.getText().toString();
                        locs[j++] = s;
                        customDialog.cancel();
                    }
                });
            }
        });
        t.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(t.getText().toString().trim().isEmpty()){
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n = t.getText().toString();
                me.setNo(n);
                me.setPmu(MainActivity.pmu);
                me.setLocations(locs);
                querya = Ref.whereEqualTo("NAME", name).whereEqualTo("PMU", loc);
                querya.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            Ref.document(loc + name).set(me).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void v) {
                                    Log.d("TAG", "DocumentSnapshot successfully updated!");
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("Error updating details", e.toString());
                                    finish();
                                }
                            });
                        } else {
                            Toast.makeText(Add_Material.this, "This " + "Material" + " for " + loc + "already exists. You may edit that data.", Toast.LENGTH_SHORT).show();
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("mainActivity ", "inquery");
                        Log.e("tage", e.toString());
                    }
                });
            }
        });
    }
}