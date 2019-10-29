package com.nb.animaciones;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView ivCheck;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCheck = findViewById(R.id.ivCheck);
        // Establece animacion creada
        ivCheck.setBackgroundResource(R.drawable.animation_check);

        //Obtiene animacion
        animationDrawable = (AnimationDrawable) ivCheck.getBackground();

        ivCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Inicia nuevamente la animacion
                animationDrawable.start();
            }
        });
    }
}
