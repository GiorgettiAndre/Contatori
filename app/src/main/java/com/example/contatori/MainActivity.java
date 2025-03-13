package com.example.contatori;

import android.annotation.SuppressLint;
import android.graphics.Path;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    TextView Storico;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Storico = (TextView) findViewById(R.id.Storico);
    }

    public void Incrementa(View view)
    {
        int b =  Character.getNumericValue(view.getTransitionName().charAt(3));
        TextView count = null;
        switch (b)
        {
            case 1: count = (TextView) findViewById(R.id.Lavoro); break;
            case 2: count = (TextView) findViewById(R.id.Sport); break;
            case 3: count = (TextView) findViewById(R.id.Acqua); break;
        }

        if(count != null)
        {
            try
            {
                int i = Integer.parseInt(count.getText().toString());

                StampaStorico(count.getTransitionName(), i , i+1);
                i++;

                count.setText(String.valueOf(i));
            } catch (NumberFormatException e) {
                System.out.println("Errore: la stringa non è un numero valido, sono in incrementa dal bottone " + view.getTransitionName());
            }
        }
    }

    public void Decrementa(View view)
    {
        int b =  Character.getNumericValue(view.getTransitionName().charAt(3));
        TextView count = null;
        switch (b)
        {
            case 1: count = (TextView) findViewById(R.id.Lavoro); break;
            case 2: count = (TextView) findViewById(R.id.Sport); break;
            case 3: count = (TextView) findViewById(R.id.Acqua); break;
        }

        if(count != null)
        {
            try
            {
                int i = Integer.parseInt(count.getText().toString());

                if(i > 0)
                {
                    StampaStorico(count.getTransitionName(), i , i-1);
                    i--;
                }

                count.setText(String.valueOf(i));
            } catch (NumberFormatException e) {
                System.out.println("Errore: la stringa non è un numero valido, sono in incrementa dal bottone " + view.getTransitionName());
            }
        }
    }

    public void Resetta(View view)
    {
        int b =  Character.getNumericValue(view.getTransitionName().charAt(3));
        TextView count = null;
        switch (b)
        {
            case 1: count = (TextView) findViewById(R.id.Lavoro); break;
            case 2: count = (TextView) findViewById(R.id.Sport); break;
            case 3: count = (TextView) findViewById(R.id.Acqua); break;
        }

        if(count != null)
        {
            try
            {
                int i = Integer.parseInt(count.getText().toString());

                StampaStorico(count.getTransitionName(), i , 0);
                i = 0;

                count.setText(String.valueOf(i));
            } catch (NumberFormatException e) {
                System.out.println("Errore: la stringa non è un numero valido, sono in incrementa dal bottone " + view.getTransitionName());
            }
        }
    }

    private void StampaStorico(String n, int v1, int v2)
    {
        // Ottieni la data e ora corrente
        Calendar calendar = Calendar.getInstance();
        // Definisci un formato
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        // Converte la data in stringa formattata
        String formattedDate = sdf.format(calendar.getTime());

        Storico.append("["+formattedDate+"]: contatore "+n+" da "+String.valueOf(v1)+" a "+String.valueOf(v2)+".");
    }
}