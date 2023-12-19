package com.example.dbproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNom, editTextAge, editTextDescription, editTextPoste;
    private Button buttonAjouter, buttonAfficher;
    private TextView textView;
    private SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNom = findViewById(R.id.editTextNom);
        editTextAge = findViewById(R.id.editTextAge);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPoste = findViewById(R.id.editTextPoste);
        buttonAjouter = findViewById(R.id.buttonAjouter);
        buttonAfficher = findViewById(R.id.buttonAfficher);
        textView = findViewById(R.id.textView);

        sqlHelper = new SQLHelper(this, null, null, SQLHelper.DATABASE_VERSION);

        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterPersonne();
            }
        });

        buttonAfficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherDonnees();
            }
        });
    }

    private void ajouterPersonne() {
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String nom = editTextNom.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim(); // Nouveau champ description
        String poste = editTextPoste.getText().toString().trim();

        values.put(PersonneContrat.Personne.TABLE_COLUM_NAME_NOM, nom);
        values.put(PersonneContrat.Personne.TABLE_COLUM_NAME_AGE, age);
        values.put(PersonneContrat.Personne.TABLE_COLUM_NAME_DESCRIPTION, description); // Champ description
        values.put(PersonneContrat.Personne.TABLE_COLUM_NAME_POSTE, poste);

        db.insert(PersonneContrat.Personne.TABLE_NAME, null, values);
        db.close();
    }

    private void afficherDonnees() {
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PersonneContrat.Personne.TABLE_NAME, null);

        textView.setText("Données :\n");

        if (cursor.moveToFirst()) {
            do {
                String nom = cursor.getString(cursor.getColumnIndex(PersonneContrat.Personne.TABLE_COLUM_NAME_NOM));
                String age = cursor.getString(cursor.getColumnIndex(PersonneContrat.Personne.TABLE_COLUM_NAME_AGE));
                String description = cursor.getString(cursor.getColumnIndex(PersonneContrat.Personne.TABLE_COLUM_NAME_DESCRIPTION)); // Champ description
                String poste = cursor.getString(cursor.getColumnIndex(PersonneContrat.Personne.TABLE_COLUM_NAME_POSTE));

                String data = "Nom: " + nom + ", Âge: " + age + ", Description: " + description + ", Poste: " + poste + "\n";
                textView.append(data);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
    }
}
