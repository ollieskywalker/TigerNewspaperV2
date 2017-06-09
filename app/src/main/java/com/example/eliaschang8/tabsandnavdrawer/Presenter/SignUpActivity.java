package com.example.eliaschang8.tabsandnavdrawer.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.eliaschang8.tabsandnavdrawer.R;

/**
 * Created by yytjy on 5/30/2017.
 */

public class SignUpActivity extends AppCompatActivity {
    public static final String APPLICATION_ID = "02FC23DC-BA34-FE97-FF77-A54ED1203000";
    public static final String SECRET_KEY = "B9BF82A4-888A-1A5D-FFA0-C2A972584600";
    public static final String VERSION = "v1";
    private static final String TAG = SignUpActivity.class.getSimpleName();
    private EditText email;
    private EditText password;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Backendless.initApp(this, APPLICATION_ID, SECRET_KEY, VERSION);

        //Wire widgets
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signupButton = (Button) findViewById(R.id.button_signup);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                finishActivity(1);//doesn't work? TODO: find a way to link signUpActivity back to LoginActivity
                //TODO: Add intent to travel to [whatever] page
            }
        });
    }

    private void registerUser() {

        BackendlessUser user = new BackendlessUser();
        Log.d(TAG, "registerUser: User: " + (user == null));
        //Reporting false so user is created, not null
        user.setEmail(email.getText().toString());
        Log.d(TAG, "registerUser: Password: " + (password == null));
        user.setPassword(password.getText().toString());
        //Log.d(TAG, "registerUser: after " + password.getText());

        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>()
        {
            public void handleResponse( BackendlessUser registeredUser )
            {
                // user has been registered and now can login
                Log.d(TAG, "onCreate: User registration successful");
                Toast.makeText(SignUpActivity.this, "User registration successful!",
                        Toast.LENGTH_SHORT).show();
            }

            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.e(TAG, "onCreate: User registration FAILED: " + fault.getCode());
            }
        } );
    }
}



