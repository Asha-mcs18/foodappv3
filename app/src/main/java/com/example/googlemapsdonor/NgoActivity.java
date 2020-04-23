package com.example.googlemapsdonor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NgoActivity extends AppCompatActivity {
    String donations[] = new String [] {"jin","suga","jhope","rm","jimin","V","jk"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo);

        ListView donationList = (ListView) findViewById(R.id.donationsList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,donations);
        donationList.setAdapter(adapter);
        donationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),DonationDetails.class);
                intent.putExtra("memberName", donations[position]);
                startActivity(intent);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
