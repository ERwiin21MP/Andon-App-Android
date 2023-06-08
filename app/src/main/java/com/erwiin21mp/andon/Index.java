package com.erwiin21mp.andon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Index extends AppCompatActivity {

    private static final String REGISTROS_ANDON = "registros_andon";
    private static final String MAQUINA_1 = "Maquina_1";
    private static final String LED = "Led";
    public static final String ROJO = "Rojo";
    public static final String AMARILLO = "Amarillo";
    public static final String AZUL = "Azul";
    public static final String VERDE = "Verde";
    private static final String NINGUNO = "Ninguno";
    private static final String ID = "id";
    private static final String NUMERO_DE_MAQUINA = "numero_de_maquina";
    private static final String NOMBRE_DE_LA_MAQUINA = "nombre_de_la_maquina";
    private static final String ESTADO = "estado";
    private static final String DIA = "dia";
    private static final String MES = "mes";
    private static final String YEAR = "año";
    private static final String HORA = "hora";
    private static final String MINUTO = "minuto";
    private static final String SEGUNDO = "segundo";
    private static final String RESPONSABLE = "responsable";
    private static final String TIEMPO_DE_RESOLUCION_HORAS = "tiempo_de_resolucion_horas";
    private static final String ACCION = "accion";
    private static final String DESCRIPCION = "descripcion";
    private static final String TIEMPO_DE_RESOLUCION_MINUTOS = "tiempo_de_resolucion_minutos";
    private ImageView LedRojo, LedAmarillo, LedAzul, LedVerde;
    private RecyclerView Lista;
    private final DatabaseReference TablaRegistrosAndon = FirebaseDatabase.getInstance().getReference(REGISTROS_ANDON);
    private final DatabaseReference TablaEstado = FirebaseDatabase.getInstance().getReference(ESTADO);
    private String EstadoDelLedDeLaMaquina1 = "Ninguno";
    private Context context;
    private ArrayList<AndonModel> list;
    private String id, nombre_de_la_maquina, estado, responsable, accion, descripcion;
    private int numero_de_maquina, dia, mes, year, hora, minuto, segundo, tiempo_de_resolucion_horas, tiempo_de_resolucion_minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        context = Index.this;
        setViews();
        getDatos();
    }

    private void getDatos() {
        TablaRegistrosAndon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Log.e("DATOS", String.valueOf(snapshot.child(MAQUINA_1)));
                    list = new ArrayList<>();
                    for (DataSnapshot i : snapshot.child(MAQUINA_1).getChildren()) {
                        id = String.valueOf(i.child(ID).getValue());
                        numero_de_maquina = Integer.parseInt(String.valueOf(i.child(NUMERO_DE_MAQUINA).getValue()));
                        nombre_de_la_maquina = String.valueOf(i.child(NOMBRE_DE_LA_MAQUINA).getValue());
                        estado = String.valueOf(i.child(ESTADO).getValue());
                        dia = Integer.parseInt(String.valueOf(i.child(DIA).getValue()));
                        mes = Integer.parseInt(String.valueOf(i.child(MES).getValue()));
                        year = Integer.parseInt(String.valueOf(i.child(YEAR).getValue()));
                        hora = Integer.parseInt(String.valueOf(i.child(HORA).getValue()));
                        minuto = Integer.parseInt(String.valueOf(i.child(MINUTO).getValue()));
                        segundo = Integer.parseInt(String.valueOf(i.child(SEGUNDO).getValue()));
                        responsable = String.valueOf(i.child(RESPONSABLE).getValue());
                        tiempo_de_resolucion_horas = Integer.parseInt(String.valueOf(i.child(TIEMPO_DE_RESOLUCION_HORAS).getValue()));
                        tiempo_de_resolucion_minutos = Integer.parseInt(String.valueOf(i.child(TIEMPO_DE_RESOLUCION_MINUTOS).getValue()));
                        accion = String.valueOf(i.child(ACCION).getValue());
                        descripcion = String.valueOf(i.child(DESCRIPCION).getValue());
                        list.add(new AndonModel(id, nombre_de_la_maquina, estado, responsable, accion, descripcion, numero_de_maquina, dia, mes, year, hora, minuto, segundo, tiempo_de_resolucion_horas, tiempo_de_resolucion_minutos));
                    }
                    EstadoDelLedDeLaMaquina1 = String.valueOf(snapshot.child(MAQUINA_1).child(LED).getValue());
                    setDrawable(EstadoDelLedDeLaMaquina1);
                    for (int i = 0; i < list.size(); i++) {
                        Log.e("DATOS - id", list.get(i).getId());
                        Log.e("DATOS - numero de maqui", String.valueOf(list.get(i).getNumero_de_maquina()));
                        Log.e("DATOS - nombre de la m", String.valueOf(list.get(i).getNombre_de_la_maquina()));
                        Log.e("DATOS - estado", String.valueOf(list.get(i).getEstado()));
                        Log.e("DATOS - dia", String.valueOf(list.get(i).getDia()));
                        Log.e("DATOS - mes", String.valueOf(list.get(i).getMes()));
                        Log.e("DATOS - año", String.valueOf(list.get(i).getYear()));
                        Log.e("DATOS - hora", String.valueOf(list.get(i).getHora()));
                        Log.e("DATOS - minuto", String.valueOf(list.get(i).getMinuto()));
                        Log.e("DATOS - segundo", String.valueOf(list.get(i).getSegundo()));
                        Log.e("DATOS - responsable", String.valueOf(list.get(i).getResponsable()));
                        Log.e("DATOS - tiempo_de_res h", String.valueOf(list.get(i).getTiempo_de_resolucion_horas()));
                        Log.e("DATOS - accion", String.valueOf(list.get(i).getAccion()));
                        Log.e("DATOS - descripcion", String.valueOf(list.get(i).getDescripcion()));
                        Log.e("DATOS - tiempo_de_res m", String.valueOf(list.get(i).getTiempo_de_resolucion_minutos()));
                        Log.e("DATOS - id", "-----------------------");
                    }

                    Collections.reverse(list);
                    setDrawable(list.get(0).getEstado());
                    AndonAdapter andonAdapter = new AndonAdapter(context, list);
                    andonAdapter.notifyDataSetChanged();
                    Lista.setAdapter(andonAdapter);
                    Lista.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                }
                else {
                    Toast.makeText(Index.this, "No hay datos en la tabla", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        TablaEstado.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    setDrawable(String.valueOf(snapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setDrawable(@NonNull String estadoDelLedDeLaMaquina1) {
        switch (estadoDelLedDeLaMaquina1) {
            case ROJO:
                LedRojo.setBackgroundResource(R.drawable.rojo);
                LedAmarillo.setBackgroundResource(R.drawable.ninguno);
                LedAzul.setBackgroundResource(R.drawable.ninguno);
                LedVerde.setBackgroundResource(R.drawable.ninguno);
                break;
            case AMARILLO:
                LedAmarillo.setBackgroundResource(R.drawable.amarillo);
                LedRojo.setBackgroundResource(R.drawable.ninguno);
                LedAzul.setBackgroundResource(R.drawable.ninguno);
                LedVerde.setBackgroundResource(R.drawable.ninguno);
                break;
            case AZUL:
                LedAzul.setBackgroundResource(R.drawable.azul);
                LedAmarillo.setBackgroundResource(R.drawable.ninguno);
                LedRojo.setBackgroundResource(R.drawable.ninguno);
                LedVerde.setBackgroundResource(R.drawable.ninguno);
                break;
            case VERDE:
                LedVerde.setBackgroundResource(R.drawable.verde);
                LedAmarillo.setBackgroundResource(R.drawable.ninguno);
                LedAzul.setBackgroundResource(R.drawable.ninguno);
                LedRojo.setBackgroundResource(R.drawable.ninguno);
                break;
            case NINGUNO:
                LedRojo.setBackgroundResource(R.drawable.ninguno);
                LedAmarillo.setBackgroundResource(R.drawable.ninguno);
                LedVerde.setBackgroundResource(R.drawable.ninguno);
                LedAzul.setBackgroundResource(R.drawable.ninguno);
        }
    }

    private void setViews() {
        LedRojo = findViewById(R.id.ImageView_Rojo);
        LedAmarillo = findViewById(R.id.ImageView_Amarillo);
        LedAzul = findViewById(R.id.ImageView_Azul);
        LedVerde = findViewById(R.id.ImageView_Verde);
        Lista = findViewById(R.id.RecyclerView);
    }
}