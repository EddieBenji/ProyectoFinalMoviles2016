package computomovil.fmat.lalo.integratingproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import computomovil.fmat.lalo.integratingproject.database.general.UserDataSource;
import computomovil.fmat.lalo.integratingproject.model.User;

public class FormUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);
    }

    public void saveUser(View view) {
        String username = ((TextView) findViewById(R.id.txtNewUsername)).getText().toString();
        String password = ((TextView) findViewById(R.id.txtNewPassword)).getText().toString();
        if (!username.isEmpty() && !password.isEmpty()) {
            User newUser = new User(username, password);

            UserDataSource userDataSource = new UserDataSource(this.getApplication().getBaseContext());
            User userRegistered = userDataSource.getUserByUsername(username);

            if (userRegistered.getUsername().equalsIgnoreCase("empty")) {
                userDataSource.insertUser(newUser);
                this.finish();
            } else {
                Toast.makeText(this, "No se puede registrar este usuario, porque ya existe",
                        Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "¡Llene los campos!", Toast.LENGTH_LONG).show();
        }
    }

}
