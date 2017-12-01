package com.spiritfitness.spiritfitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button addContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addContainer = (Button) this.findViewById(R.id.addContainer);
        addContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(MainActivity.this, ContainerActivity.class);
                Bundle flag = new Bundle();
                //flag.putInt(Constants.ARG_POSITION, Constants.DEPARTURE_QUERY_BOOKMARK);
                //page.putExtras(flag);
                //startActivityForResult(page,Constants.DEPARTURE_QUERY_BOOKMARK);
                page.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(page);
                finish();
            }
        });
    }
}
