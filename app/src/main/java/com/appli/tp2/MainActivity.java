package com.appli.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button envoyer = null;
    Button reset = null;
    EditText taille = null;
    EditText poids = null;
    CheckBox commentaire = null;
    RadioGroup group = null;
    TextView result = null;
    private String texteInit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texteInit=getString(R.string.instruction);
        envoyer = (Button) findViewById(R.id.calcul);
        reset = (Button) findViewById(R.id.reset);
        taille = (EditText) findViewById(R.id.taille);
        poids = (EditText) findViewById(R.id.poids);
        commentaire = (CheckBox) findViewById(R.id.commentaire);
        group = (RadioGroup) findViewById(R.id.group);
        result = (TextView) findViewById(R.id.result);


        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = taille.getText().toString();
                String p = poids.getText().toString();
                float tValue = Float.valueOf(t);
                float pValue = Float.valueOf(p);

                if (tValue < 0) {
                    Toast.makeText(MainActivity.this, getString(R.string.Taillebasse), Toast.LENGTH_SHORT).show();
                } else {
                    if (pValue < 0) {
                        Toast.makeText(MainActivity.this,  getString(R.string.PoidsBas), Toast.LENGTH_SHORT).show();
                    } else {
                        if (group.getCheckedRadioButtonId() == R.id.radio_centimetre)
                            tValue = tValue / 100;
                        float imc = pValue / (tValue * tValue);
                        String resultat = getString(R.string.VotreIMCest) + imc + " . ";
                        if (commentaire.isChecked()) resultat += interpreteIMC(imc);
                        result.setText(resultat);

                    }
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taille.getText().clear();
                poids.getText().clear();
                result.setText(texteInit);
            }
        });
        commentaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    result.setText(texteInit);
                }
            }
        });
        taille.setOnKeyListener(modifListener);
        poids.setOnKeyListener(modifListener);


    }

    String interpreteIMC(double imc) {
        if (imc < 16.5) {
            return "famine";
        } else if (16.5 < imc && imc < 18.5) {
            return getString(R.string.maigreur);
        } else if (18.5 < imc && imc < 25) {
            return getString(R.string.normal);
        } else if (25 < imc && imc < 30) {
            return getString(R.string.surpoids);
        } else if (30 < imc && imc < 35) {
            return getString(R.string.modéré);
        } else if (35 < imc && imc < 40) {
            return getString(R.string.severe);
        } else {
            return getString(R.string.morbide);
        }
    }

    // Se lance à chaque fois qu'on appuie sur une touche en étant sur un EditText
    private View.OnKeyListener modifListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            // On remet le texte à sa valeur par défaut
            //result.setText(texteInit);
            String s = taille.getText().toString();
            if (s.contains(".")) {
                group.check(R.id.radio_metre);
            }
            return false;
        }
    };
}