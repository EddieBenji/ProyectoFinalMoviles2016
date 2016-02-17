package computomovil.fmat.lalo.integratingproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import computomovil.fmat.lalo.integratingproject.database.user.UserDataSource;
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
        User newUser = new User(username, password);

        UserDataSource userDataSource = new UserDataSource(this.getApplication().getBaseContext());
        userDataSource.insertUser(newUser);
        this.finish();

    }

}
