package com.example.hoteltransylvania.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.activities.InicioActivity;
import com.example.hoteltransylvania.data.entities.Habitacion;
import com.example.hoteltransylvania.viewmodels.HabitacionViewModel;

import java.util.ArrayList;

public class HabitacionesFragment extends Fragment {

    GridView grid;

    ArrayList<Integer> imagenes = new ArrayList<Integer>();
    ArrayList<String> descripciones = new ArrayList<String>();
    ArrayList<Double> precios = new ArrayList<Double>();
    ArrayList<String> ids = new ArrayList<String>();

    TextView mensaje;
    int personas, precioMax;
    /**
     * Distingue los llamamientos a infoHabitacion desde reservas
     */
    boolean esReserva;

    //CONEXION CON VIEW MODEL
    private HabitacionViewModel habitacionViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        habitacionViewModel = new ViewModelProvider(this).get(HabitacionViewModel.class);
    }
    ////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habitaciones, container, false);

        grid = view.findViewById(R.id.gridHabitaciones);

        mensaje = view.findViewById(R.id.mensajeHabitacion);
        Bundle arguments = getArguments();
        if (arguments != null){
            personas = arguments.getInt("numeroPersonas",0);
            precioMax = arguments.getInt("precioMaximo",0);
            mensaje.setText(personas+" "+precioMax);
            esReserva = true;
            buscar();
        }
        else{
            esReserva = false;
            mostrarListado();
        }

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InicioActivity.mostrarInfoHabitacion(ids.get(i),imagenes.get(i), precios.get(i),descripciones.get(i), personas, esReserva);
            }
        });

        return view;
    }

    public void buscar(){
        habitacionViewModel.devolverHabitacionesWhere(precioMax,personas).observe(getViewLifecycleOwner(),listaHabitaciones->{
            if(listaHabitaciones==null){
                System.out.println("null");
            }else{
                System.out.println("entra");
                for (Habitacion hab :listaHabitaciones){
                    if (!ids.contains(hab.getId())){
                        ids.add(hab.getId());
                        imagenes.add(hab.getImagen());
                        descripciones.add((hab.getDescrip()));
                        precios.add(hab.getPrecio());
                    }
                }
                HabitacionesCustomAdapter habitacionesCustomAdapter = new HabitacionesCustomAdapter(getActivity().getApplicationContext(),imagenes,ids,precios);
                grid.setAdapter(habitacionesCustomAdapter);
            }
        });
    }

    public void mostrarListado(){
        habitacionViewModel.devolverTodasHabitaciones().observe(getViewLifecycleOwner(),listaHabitaciones->{
            if(listaHabitaciones==null){
                System.out.println("null");
            }else{
                System.out.println("entra");
                for (Habitacion hab :listaHabitaciones){
                    if (!ids.contains(hab.getId())){
                        ids.add(hab.getId());
                        imagenes.add(hab.getImagen());
                        descripciones.add((hab.getDescrip()));
                        precios.add(hab.getPrecio());
                    }
                }
                HabitacionesCustomAdapter habitacionesCustomAdapter = new HabitacionesCustomAdapter(getActivity().getApplicationContext(),imagenes,ids,precios);
                grid.setAdapter(habitacionesCustomAdapter);
            }
        });
    }
}