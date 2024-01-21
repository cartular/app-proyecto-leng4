package com.varasccatalina.myapplication;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class HorarioFragment extends Fragment {

    private Spinner daySpinner;
    private TextView dayNameTextView;
    private LinearLayout hoursTable;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_horario, container, false);

        // Initialize interface elements
        daySpinner = view.findViewById(R.id.daySpinner);
        dayNameTextView = view.findViewById(R.id.dayNameTextView);


        // Configure the Spinner with days of the week (you can use an ArrayAdapter)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.days_of_week, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapter);

        // Handle day selection
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the name of the selected day
                String selectedDay = daySpinner.getSelectedItem().toString();
                dayNameTextView.setText(selectedDay);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No need to do anything in this case
            }
        });

        // Add button click listener to open dialog
        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogAgregarMateria();
            }
        });

        return view;
    }

    private List<Materia> materias = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void showDialogAgregarMateria() {
        // Create dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Agregar materia");

        // Create layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_agregar_materia, null);
        builder.setView(dialogView);

        // Get dialog controls
        TextInputEditText txtNombre = dialogView.findViewById(R.id.txtNombre);
        Spinner spinnerTipo = dialogView.findViewById(R.id.tipoSpinner);
        TextInputEditText txtHorario = dialogView.findViewById(R.id.txtHorario);
        TextInputEditText txtAula = dialogView.findViewById(R.id.txtAula);
        TextInputEditText txtProfesor = dialogView.findViewById(R.id.txtProfesor);
        TextInputEditText txtEdificio = dialogView.findViewById(R.id.txtEdificio);
        TextInputEditText txtHorarioFin = dialogView.findViewById(R.id.txtHorarioFin);// Add this line


        // Add options to the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.tipos_materias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        // Create listener for the "Aceptar" button
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get information entered by the user
                String nombre = txtNombre.getText().toString();
                String tipo = spinnerTipo.getSelectedItem().toString();
                String horario = txtHorario.getText().toString();
                String aula = txtAula.getText().toString();
                String edifcio = txtEdificio.getText().toString();
                String profesor = txtProfesor.getText().toString();
                String horariofin = txtHorarioFin.getText().toString();
                // Create a new materia
                Materia materia = new Materia(nombre, tipo, horario, aula, profesor, edifcio, horariofin);

                // Add the materia to the list
                materias.add(materia);

                // Update the view
                updateView(nombre, tipo, horario, aula, profesor, edifcio, horariofin);

                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Create listener for the "Cancelar" button
        builder.setNegativeButton("Cancelar", null);

        // Show the dialog
        builder.show();
    }

    private void updateView(String nombre, String tipo, String horarioInicio, String horarioFin, String aula, String edificio, String profesor) {

        // Aquí puedes agregar la información debajo de la columna "Clases"
        TableRow infoRow = view.findViewById(R.id.infoRow);
        TextView infoTextView = new TextView(getContext());
        infoTextView.setText("Nombre: " + nombre ); // Ajusta según tus necesidades
        infoRow.addView(infoTextView);
    }
}

