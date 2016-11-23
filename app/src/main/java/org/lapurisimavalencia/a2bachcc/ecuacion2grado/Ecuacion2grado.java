package org.lapurisimavalencia.a2bachcc.ecuacion2grado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

public class Ecuacion2grado extends AppCompatActivity {

    private ImageView imagenPeque;
    private ImageView imagenGran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecuacion2grado);
        ((TextView)findViewById(R.id.textViewA)).setText(Html.fromHtml("x<sup>2</sup>  + "));
        ((TextView)findViewById(R.id.textViewB)).setText("x  + ");

        imagenPeque = (ImageView) findViewById(R.id.imageViewPeque);
        imagenGran = (ImageView) findViewById(R.id.imageViewGrande);

    }
}
