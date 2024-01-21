package com.varasccatalina.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

      private TextView infoTextView;
    private List<Facultad> mFacultades;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        infoTextView = view.findViewById(R.id.info_text_view);

        mFacultades = obtenerListaFacultades();
        GridView gridView = view.findViewById(R.id.grid_view);
        ImageAdapter adapter = new ImageAdapter(getContext(), mFacultades);
        gridView.setAdapter(adapter);

        // Agregar un listener al GridView para manejar los clics en las imágenes
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la facultad seleccionada
                Facultad facultad = mFacultades.get(position);

                // Crear un AlertDialog personalizado
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);
                builder.setView(popupView);

                // Obtener referencias a los elementos del layout personalizado
                TextView popupTitle = popupView.findViewById(R.id.popup_title);
                TextView popupDescription = popupView.findViewById(R.id.popup_description);

                // Establecer la información de la facultad en los elementos del layout personalizado
                popupTitle.setText(facultad.getNombre());
                popupDescription.setText(facultad.getDescripcion());

                // Crear y mostrar el AlertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return view;
    }

    // Método para obtener la lista de facultades con su información

    private List<Facultad> obtenerListaFacultades() {
        List<Facultad> listaFacultades = new ArrayList<>();
        listaFacultades.add(new Facultad("Facultad de Ingeniería", "Lunes a Viernes: 8hs a 21hs", "4268539"));
        listaFacultades.add(new Facultad("Facultad de Economía", "Lunes a Viernes: 8hs a 21hs", "4268517"));
        listaFacultades.add(new Facultad("Facultad de Cs Jurídicas", "Lunes a Viernes: 8hs a 20hs", "4268649"));
        listaFacultades.add(new Facultad("Facultad de Artes y Ciencias", "Lunes a Viernes:  8hs a 13hs y 14hs a 21hs", "4268552"));
        listaFacultades.add(new Facultad("Facultad de  Arquitectura", "Lunes a Viernes: 8hs a 21hs", "4268542"));
        listaFacultades.add(new Facultad("Escuela Cs Salud", "Lunes a Viernes: 8hs a 20hs", "4268630"));
        listaFacultades.add(new Facultad("Facultad de Turismo", "Lunes a Viernes: 11hs a 21hs", "4268567"));
        listaFacultades.add(new Facultad("Facultad de Veterinaria", "Lunes a Viernes: 8hs a 20hs", "4268566 / 4268565"));

        return listaFacultades;
    }

    public class ImageAdapter extends BaseAdapter {

        private Context mContext;

        private String[] mTitles = {
                "Facultad Ingeniería", "Facultad Economía", "Facultad Jurídicas", "Facultad Artes y Ciencias",
                "Facultad Arquitectura", "Escuela Cs Salud", "Facultad Turismo", "Facultad Veterinaria"
        };
        private int[] mImageIds = {R.drawable.ingenieria, R.drawable.economia, R.drawable.juridicas,
                R.drawable.artes, R.drawable.arquitectura, R.drawable.salud, R.drawable.turismo,
                R.drawable.veterinaria};

        public ImageAdapter(Context context, List<Facultad> facultades) {
            mContext = context;
            mFacultades = facultades;
        }

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.grid_item_layout, null);
            }

            ImageView imageView = view.findViewById(R.id.image_view);
            TextView titleText = view.findViewById(R.id.title_text);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(18));
            Glide.with(mContext)
                    .load(mImageIds[position])
                    .apply(requestOptions)
                    .into(imageView);
            titleText.setText(mTitles[position]);

            return view;
        }

    }


}

