package com.nb.animaciones;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvContenido;
    ProgressBar pbLoading;
    private int duracionAnimacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContenido = findViewById(R.id.tvContenido);
        pbLoading = findViewById(R.id.pbLoading);

        // Define tiempo de animacion
        duracionAnimacion = getResources().getInteger(android.R.integer.config_mediumAnimTime);

        //Ocultar texto
        tvContenido.setVisibility(View.GONE);

        pbLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crossFadeAnimation();
            }
        });
    }

    private void crossFadeAnimation() {

        //Muestra progresivamente el texto
        tvContenido.setAlpha(0f);
        tvContenido.setVisibility(View.VISIBLE);

        tvContenido.animate().alpha(1f).setDuration(duracionAnimacion);

        //Oculta progresivamente barra de carga
        pbLoading.animate().alpha(0f).setDuration(duracionAnimacion).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) { // cuando la animacion finaliza
                pbLoading.setVisibility(View.GONE);
            }
        });
    }
}
