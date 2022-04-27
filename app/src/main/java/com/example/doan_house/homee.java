package com.example.doan_house;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class homee extends AppCompatActivity {

    Switch switchledkhach,switchledbep,switchledngu,switchledtam,switchfankhach,switchfanbep,switchfanngu,switchfantam,switchpool;
    TextView temp,hum, times;
    AppCompatButton ledon,ledoff,logout;
    ProgressBar progressBar;
    private DatabaseReference ref,tem,ref1,ref2,ref3,ref4,ref5,lt1,lt2,lt3,lt4,f1,f2;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();


        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseAuth.getInstance().signOut();

        ref = FirebaseDatabase.getInstance().getReference().child("Pool/Sensor");
        ref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue().toString();
                if (data.indexOf("A") >= 0){
                    final String Channel_ID = "HEADS_UP_NOTIFICATION";
                    NotificationChannel channel = new NotificationChannel(
                            Channel_ID,"Heads Up Notification", NotificationManager.IMPORTANCE_HIGH
                    );
                    getSystemService(NotificationManager.class).createNotificationChannel(channel);
                    Notification.Builder builder = new Notification.Builder(homee.this, Channel_ID)
                            .setContentTitle("Warning!")
                            .setContentText("Abnormal movement")
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setAutoCancel(true);
                    NotificationManagerCompat.from(homee.this).notify(1, builder.build());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setContentView(R.layout.homee);
        logout = findViewById(R.id.logout);
        progressBar = findViewById(R.id.progressBar_homee);
        switchledkhach = findViewById(R.id.switchlightkhack);
        switchledbep = findViewById(R.id.switchlightbep);
        switchledngu = findViewById(R.id.switchlightngu);
        switchledtam = findViewById(R.id.switchlighttam);
        switchfankhach = findViewById(R.id.switchfankhach);
        switchfanngu = findViewById(R.id.switchfanngu);
        switchpool = findViewById(R.id.switchinfrared);
        ledon = findViewById(R.id.ledallon);
        ledoff = findViewById(R.id.ledalloff);
        temp = findViewById(R.id.temp);
        hum = findViewById(R.id.humidp);
        times = findViewById(R.id.assssss);
        times.setText(currentTime());


        f1 = FirebaseDatabase.getInstance().getReference().child("LivingRoom/Fan");
        f1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String da1 = snapshot.getValue().toString();
                if(da1.indexOf("on") >= 0){
                    switchfankhach.setChecked(true);
                }
                if(da1.indexOf("off") >= 0){
                    switchfankhach.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        f2 = FirebaseDatabase.getInstance().getReference().child("BedRoom/Fan");
        f2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String da2 = snapshot.getValue().toString();
                if(da2.indexOf("on") >= 0){
                    switchfanngu.setChecked(true);
                }
                if(da2.indexOf("off") >= 0){
                    switchfanngu.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lt1 = FirebaseDatabase.getInstance().getReference().child("LivingRoom/Led");
        lt1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dat1 = snapshot.getValue().toString();
                if(dat1.indexOf("on") >=0){
                    switchledkhach.setChecked(true);
                }
                if(dat1.indexOf("off") >=0){
                    switchledkhach.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lt2 = FirebaseDatabase.getInstance().getReference().child("BedRoom/Led");
        lt2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dat2 = snapshot.getValue().toString();
                if(dat2.indexOf("on") >=0){
                    switchledngu.setChecked(true);
                }
                if(dat2.indexOf("off") >=0){
                    switchledngu.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lt3 = FirebaseDatabase.getInstance().getReference().child("Kitchen/Led");
        lt3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dat3 = snapshot.getValue().toString();
                if(dat3.indexOf("on") >=0){
                    switchledbep.setChecked(true);
                }
                if(dat3.indexOf("off") >=0){
                    switchledbep.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lt4 = FirebaseDatabase.getInstance().getReference().child("BathRoom/Led");
        lt4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dat4 = snapshot.getValue().toString();
                if(dat4.indexOf("on") >=0){
                    switchledtam.setChecked(true);
                }
                if(dat4.indexOf("off") >=0){
                    switchledtam.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        tem = FirebaseDatabase.getInstance().getReference().child("BedRoom/Temp");

        ledon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                ref2 = database.getReference("BedRoom/Led");
                ref2.setValue("on");
                ref5 = database.getReference("Kitchen/Led");
                ref5.setValue("on");
                ref3 = database.getReference("BathRoom/Led");
                ref3.setValue("on");
                ref4 = database.getReference("LivingRoom/Led");
                ref4.setValue("on");
            }
        });
        ledoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                ref2 = database.getReference("BedRoom/Led");
                ref2.setValue("off");
                ref5 = database.getReference("Kitchen/Led");
                ref5.setValue("off");
                ref3 = database.getReference("BathRoom/Led");
                ref3.setValue("off");
                ref4 = database.getReference("LivingRoom/Led");
                ref4.setValue("off");
            }
        });
        tem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue().toString();
                temp.setText(data + " °C");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref1 = FirebaseDatabase.getInstance().getReference().child("BedRoom/Humid");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data4 = snapshot.getValue().toString();
                hum.setText(data4 +  " %");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        switchledkhach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchledkhach.isChecked()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("LivingRoom/Led");
                    myRef.setValue("on");
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("LivingRoom/Led");//LED_STATUS is Firebase database LED_STATUS
                    myRef.setValue("off", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Off", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        switchledbep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchledbep.isChecked()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Kitchen/Led");//LED_STATUS is Firebase database LED_STATUS
                    myRef.setValue("on", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Kitchen/Led");//LED_STATUS is Firebase database LED_STATUS
                    myRef.setValue("off", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        switchfankhach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switchfankhach.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef6 = database.getReference("LivingRoom/Fan");//LED_STATUS is Firebase database LED_STATUS
                    myRef6.setValue("on", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef6 = database.getReference("LivingRoom/Fan");//LED_STATUS is Firebase database LED_STATUS
                    myRef6.setValue("off", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        switchfanngu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switchfanngu.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef7 = database.getReference("BedRoom/Fan");//LED_STATUS is Firebase database LED_STATUS
                    myRef7.setValue("on", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef7 = database.getReference("BedRoom/Fan");//LED_STATUS is Firebase database LED_STATUS
                    myRef7.setValue("off", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        switchledngu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchledngu.isChecked()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("BedRoom/Led");//LED_STATUS is Firebase database LED_STATUS
                    myRef.setValue("on", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("BedRoom/Led");//LED_STATUS is Firebase database LED_STATUS
                    myRef.setValue("off", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        switchledtam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchledtam.isChecked()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("BathRoom/Led");//LED_STATUS is Firebase database LED_STATUS
                    myRef.setValue("on", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("BathRoom/Led");//LED_STATUS is Firebase database LED_STATUS
                    myRef.setValue("off", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        switchpool.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchpool.isChecked()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Pool/Sensor");//LED_STATUS is Firebase database LED_STATUS
                    myRef.setValue("A", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Pool/Sensor");//LED_STATUS is Firebase database LED_STATUS
                    myRef.setValue("B", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(homee.this, "Success", Toast.LENGTH_SHORT).show();
                        }

                    });
                }

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == logout){
                    mFirebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(homee.this,Login_activity.class));
                }
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private String currentTime(){
    return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
}
}