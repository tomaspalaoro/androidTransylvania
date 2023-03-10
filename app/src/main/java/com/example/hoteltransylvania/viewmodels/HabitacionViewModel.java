package com.example.hoteltransylvania.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.hoteltransylvania.data.entities.Habitacion;
import com.example.hoteltransylvania.data.repositories.HabitacionRepository;

import java.util.List;

public class HabitacionViewModel extends AndroidViewModel {
    private final HabitacionRepository hRepo;
    private final LiveData<List<Habitacion>> listadoHabitaciones;

    public HabitacionViewModel(@NonNull Application application) {
        super(application);
        this.hRepo = new HabitacionRepository(application);
        this.listadoHabitaciones = hRepo.rDevolverTodasHabitaciones();
    }

    public LiveData<List<Habitacion>> devolverTodasHabitaciones(){
        return listadoHabitaciones;
    }

    public void insertarHabitacion(Habitacion habitacion){
        hRepo.rInsertarHabitacion(habitacion);
    }

    public void eliminarHabitacion(Habitacion habitacion){
        hRepo.delete(habitacion);
    }

    public LiveData<Habitacion> devolverHabitacionConId(String miD){
        return hRepo.rDevolverHabitacionConId(miD);
    }

    public LiveData<List<Habitacion>> devolverHabitacionesWhere(Integer maxPrecion, Integer numPersonas){
        return hRepo.rDevolverHabitacionesWhere(maxPrecion,numPersonas);
    }
}
