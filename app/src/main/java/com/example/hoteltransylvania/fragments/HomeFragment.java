package com.example.hoteltransylvania.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.activities.InicioActivity;
import com.example.hoteltransylvania.data.entities.Habitacion;
import com.example.hoteltransylvania.viewmodels.HabitacionViewModel;

public class HomeFragment extends Fragment {

    Button bRes, bHab, bOcio;
    TextView telefono,mapa;

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        telefono=view.findViewById(R.id.telefono);
        mapa=view.findViewById(R.id.mapa);

        telefono.setOnClickListener(vista -> realizarLlamada());
        mapa.setOnClickListener(vista -> mostrarLocalizacion());
        
        bRes = view.findViewById(R.id.bRes);
        bHab = view.findViewById(R.id.bHab);
        bOcio = view.findViewById(R.id.bOcio);

        bRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioActivity.irFragment("reservas");
            }
        });
        bHab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioActivity.irFragment("habitaciones");
            }
        });
        bOcio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioActivity.irFragment("ocio");
            }
        });

        crearHabitaciones();

        return view;
    }

    public void realizarLlamada(){
        Uri number= Uri.parse("tel:+40264594429");
        Intent callIntent = new Intent(Intent.ACTION_DIAL,number);
        startActivity(callIntent);
    }

    public void mostrarLocalizacion(){
        Uri location = Uri.parse("geo:46.77257698029504, 23.589283580187296");
        // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        try {
            startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), "NO SE PUEDE MOSTRAR EL MAPA", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Crea las habitaciones por defecto si no existen en la base de datos
     */
    public void crearHabitaciones(){
        habitacionViewModel.insertarHabitacion(new Habitacion(
                "Hab. Descanso Macabro",
                1,
                "Esta habitaci??n es perfecta para personas que viajan solas y quieren tener una noche de sue??o c??moda y espeluznante. Las paredes de la habitaci??n est??n adornadas con carteles de pel??culas de terror cl??sicas y un retrato de una figura fantasmal que cuelga sobre la cama. El ba??o tambi??n ha sido decorado con un toque macabro, con una ba??era con patas y una cortina de ducha negra y naranja. El lavabo est?? decorado con un dosificador de jab??n en forma de calavera y un portacepillos a juego.",
                45,
                R.drawable.habitacion));
        habitacionViewModel.insertarHabitacion(new Habitacion(
                "Hab. Stranger Things",
                2,
                "Esta habitaci??n es perfecta para los fan??ticos de \"Stranger Things\". Es una experiencia divertida e inmersiva que te transportar?? a los a??os 80 y te sumergir?? en el mundo de la serie. Al entrar en la habitaci??n te encontrar??as con un mural de la ic??nica pared alfabeto del programa. La habitaci??n tambi??n tiene una caracter??stica ??nica, una puerta oculta que conduce a una habitaci??n secreta, decorada con un colorido letrero de ne??n.",
                65,
                R.drawable.habstrangerthings));
        habitacionViewModel.insertarHabitacion(new Habitacion(
                "Hab. Asesinato misterioso",
                4,
                "La caracter??stica principal de esta habitaci??n es una serie de pistas y acertijos que los visitantes pueden resolver para descubrir la identidad del \"asesino\". Estas pistas se pueden encontrar ocultas por toda la habitaci??n, como mensajes cr??pticos escritos en las paredes, compartimentos ocultos y pasadizos secretos. Esta habitaci??n es perfecta para los hu??spedes que aman un buen misterio y disfrutan resolviendo acertijos.",
                80,
                R.drawable.habasesinatomisterioso));
        habitacionViewModel.insertarHabitacion(new Habitacion(
                "Hab. Halloween",
                1,
                "Al entrar en la habitaci??n, ser??s recibido por una iluminaci??n tenue y sombras espeluznantes que bailan a trav??s de las paredes. La habitaci??n est?? decorada con telara??as y calabazas espeluznantes. La cama est?? hecha con s??banas negras y naranjas y una funda n??rdica blanca fantasmal. Las mesitas de noche est??n adornadas con candelabros y dulces en forma de calavera. Las paredes est??n adornadas con carteles de pel??culas de terror cl??sicas y un retrato de una figura fantasmal que cuelga sobre la cama.",
                40,
                R.drawable.habhalloween));
        habitacionViewModel.insertarHabitacion(new Habitacion(
                "Hab. Infantil",
                3,
                "Esta es la habitaci??n perfecta para los m??s peque??os de la familia. Disfrutar??n de una ambientaci??n tenebrosa a la vez que amistosa. Las mesitas de noche est??n adornadas con candelabros y dulces en forma de calavera. Se puede activar un sistema de sonido incorporado en la habitaci??n, que reproduce m??sica y distintos efectos de sonido para crear el ambiente.",
                50,
                R.drawable.habinfantil));
    }
}