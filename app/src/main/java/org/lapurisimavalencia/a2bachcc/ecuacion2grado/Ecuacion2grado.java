package org.lapurisimavalencia.a2bachcc.ecuacion2grado;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Ecuacion2grado extends AppCompatActivity {

    private ImageView imagenPeque;
    private ImageView imagenGran;
    private EditText edtA;
    private EditText edtB;
    private EditText edtC;
    private TextView tvResultsInfo;
    private TextView tvResults1;
    private TextView tvResults2;
    private TextView tvResultsDiscriminante;
    private Button botonResultados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecuacion2grado);
        ((TextView)findViewById(R.id.textViewA)).setText(Html.fromHtml("x<sup>2</sup> +"));
        ((TextView)findViewById(R.id.textViewB)).setText("x +");

        this.tvResultsInfo = (TextView) findViewById(R.id.textViewInfo);
        this.tvResults1 = (TextView) findViewById(R.id.textViewResults1);
        this.tvResults2 = (TextView) findViewById(R.id.textViewResults2);
        this.tvResultsDiscriminante = (TextView) findViewById(R.id.textViewDiscriminante);

        this.edtA = (EditText) findViewById(R.id.editTextA);
        this.edtB = (EditText) findViewById(R.id.editTextB);
        this.edtC = (EditText) findViewById(R.id.editTextC);

        imagenPeque = (ImageView) findViewById(R.id.imageViewPeque);
        imagenGran = (ImageView) findViewById(R.id.imageViewGrande);
        imagenPeque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagenGran.setVisibility(ImageView.VISIBLE);
            }
        });

        this.botonResultados = (Button) findViewById(R.id.buttonResultado);
        this.botonResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                resolucionEcuacion2grado();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            imagenGran.setVisibility(ImageView.INVISIBLE);
        }

        return true;
    }

    public void resolucionEcuacion2grado() {
        // Código de Adrián Ferrandis
        double a = 0, b = 0, c = 0;
        double discriminante, sol1, sol2;
        String sA, sB, sC;

        limpiarTextView(); // Limpiamos los textview de resultados e información.

        // Compruebo que los valores de a, b y c son correctos.
        try {
            sA = this.edtA.getText().toString();
            if (sA.isEmpty()) {
                a = 0;
            } else {
                a = Double.parseDouble(this.edtA.getText().toString());
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: Valor de a incorrecto.", Toast.LENGTH_LONG).show();
        }

        try {
            sB = this.edtC.getText().toString();
            if (sB.isEmpty()) {
                b = 0;
            } else {
                b = Double.parseDouble(this.edtB.getText().toString());
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: Valor de b incorrecto.", Toast.LENGTH_LONG).show();
        }

        try {
            sC = this.edtC.getText().toString();
            if (sC.isEmpty()) {
                c = 0;
            } else {
                c = Double.parseDouble(this.edtC.getText().toString());
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: Valor de c incorrecto.", Toast.LENGTH_LONG).show();
        }

        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    this.tvResultsInfo.setText("La solución de la ecuación es cualquier número real");
                } else {
                    this.tvResultsInfo.setText("Error: Ecuación Incorrecta");
                }
            } else {
                sol1 = (-c) / b;
                this.tvResultsInfo.setText("El resultado es: " + sol1);
            }
        } else {
            //se empieza a calcular la parte de la raiz
            discriminante = (b * b) - (4 * a * c);
            this.tvResultsDiscriminante.setText(Html.fromHtml("El discriminante (b<sup>2</sup> - 4ac) vale: " + discriminante));

            if (discriminante == 0) {
                sol1 = ((-1) * b) / (2 * a);
                this.tvResults1.setText("Solución real: " + sol1);
            } else if (discriminante < 0) {
                discriminante = discriminante * (-1);
                discriminante = Math.sqrt(discriminante);
                this.tvResultsInfo.setText("Solucion (Soluciones Imaginarias):");
                this.tvResults1.setText((-b / (2 * a)) + " + " + (discriminante / (2 * a)) + "i");
                this.tvResults2.setText((-b / (2 * a)) + " - " + (discriminante / (2 * a)) + "i");
            } else {
                discriminante = Math.sqrt(discriminante);
                //Calculamos las dos soluciones:
                sol1 = ((-1) * b + discriminante);
                sol2 = ((-1) * b - discriminante);
                this.tvResultsInfo.setText("Soluciones reales:");
                this.tvResults1.setText("Solucion 1: " + (sol1 / (2 * a)));
                this.tvResults2.setText("Solucion 2: " + (sol2 / (2 * a)));
            }
        }
    }

    public void limpiarTextView(){
        this.tvResultsInfo.setText("");
        this.tvResultsDiscriminante.setText("");
        this.tvResults1.setText("");
        this.tvResults2.setText("");
    }
}