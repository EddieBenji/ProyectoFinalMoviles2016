package computomovil.fmat.lalo.integratingproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import computomovil.fmat.lalo.integratingproject.database.general.PondDataSource;
import computomovil.fmat.lalo.integratingproject.model.Pond;

public class FormPond extends AppCompatActivity {

    private String name;
    private String description;
    private double latitud;
    private double longitud;
    private PondDataSource pondDS;
    private Pond pond;
    private boolean adding;

    private EditText et_name;
    private EditText et_des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pond);

        pondDS = new PondDataSource(getApplicationContext());
        pond = (Pond) getIntent().getSerializableExtra("pond");
        adding = (int) getIntent().getSerializableExtra("adding") == 1;

        this.setTexts();
        this.setButtons();

    }

    private void setButton(Button btn, boolean isAvailable) {
        btn.setClickable(isAvailable);
        btn.setEnabled(isAvailable);
    }

    private void setButtons(){
        setButton((Button) findViewById(R.id.btn_deletePond), !adding);
        setButton((Button) findViewById(R.id.btn_updatePond), !adding);
        setButton((Button) findViewById(R.id.btn_lookMap), !adding);
        setButton((Button) findViewById(R.id.btn_savePond), adding);
    }

    private void setTexts(){
        ((EditText) this.findViewById(R.id.text_namePond)).setText(pond.getName());
        ((EditText) this.findViewById(R.id.text_descriptionPond)).setText(pond.getDescription());
        ((EditText) this.findViewById(R.id.text_latPond)).setText(String.valueOf(pond.getLatitude()));
        ((EditText) this.findViewById(R.id.text_lngPond)).setText(String.valueOf(pond.getLongitude()));
    }

    private void getFields(){
        name = ((EditText) findViewById(R.id.text_namePond)).getText().toString();
        description = ((EditText) findViewById(R.id.text_descriptionPond)).getText().toString();
        String latitude = ((EditText) findViewById(R.id.text_latPond)).getText().toString();
        String longitud = ((EditText) findViewById(R.id.text_lngPond)).getText().toString();
        this.latitud = Double.valueOf(latitude);
        this.longitud = Double.valueOf(longitud);
    }

    private boolean emptyFields() {
        boolean band = true;
        if ( (((EditText) findViewById(R.id.text_namePond)).getText().toString().length()) *
                (((EditText) findViewById(R.id.text_descriptionPond)).getText().toString().length()) *
                (((EditText) findViewById(R.id.text_latPond)).getText().toString().length()) *
                (((EditText) findViewById(R.id.text_lngPond)).getText().toString().length()) > 0) {
            band = false;
        }
        return band;
    }

    private void showMsgForEmptyFields() {
        Toast.makeText(this, "Asegúrate de llenar todos los campos", Toast.LENGTH_LONG).show();
    }

    public void updatePond(View v){
        getFields();
        if ( !emptyFields() ){
            try {
                pondDS.updatePond(new Pond(name, description, latitud, longitud));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Poza actualizada", Toast.LENGTH_LONG).show();
            this.finish();
        }else{
            showMsgForEmptyFields();
        }
    }

    public void savePond(View v){
        getFields();
        if (!emptyFields()) {
            try {
                if (!pondDS.existsPond(name)) {
                    pondDS.insertPond(new Pond(name, description, latitud, longitud));
                    Log.i("DATA_POND", name + description + latitud + longitud);
                    Toast.makeText(this, "Poza guardado", Toast.LENGTH_LONG).show();
                    this.finish();
                } else {
                    Toast.makeText(this, "Ese nombre, ya está registrado. ¡Intenta otro!",
                            Toast.LENGTH_LONG).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            showMsgForEmptyFields();
        }
    }

    public void deletePond(View v){
        getFields();
        try {
            pondDS.deletePond(new Pond(name, description, latitud, longitud));
            Toast.makeText(this, "Poza eliminada", Toast.LENGTH_LONG).show();
            this.finish();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lookMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("pond", pond);
        startActivity(intent);
    }

}
