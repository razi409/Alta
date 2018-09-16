package com.altashcools.altaschools;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raziehakbari on 03/01/2018.
 */

public class FragmentProfile extends Fragment {

    public int RC_SIGN_IN = 12;
    public CardView signInButton;
    public LinearLayout sign_in_layout;
    public View v;
    public static EditText user_birth_date;
    public Button birth;
    public DatabaseReference mFirebaseDatabaseReference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        /**
         * Handles google sign in
         */
        v = inflater.inflate(R.layout.register, container, false);
        signInButton = v.findViewById(R.id.sign_in_button);
        sign_in_layout = v.findViewById(R.id.sign_in_layout);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity().getApplicationContext());
       // if(account!=null){
        //    updateUI(account);

       // }else{

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getActivity().getApplicationContext(), gso);
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }
            });

      //  }


        birth = v.findViewById(R.id.birthBut);
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getChildFragmentManager(), "datePicker");
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();


        sign_in_layout.setVisibility(View.GONE);
        NestedScrollView nestedScrollView = v.findViewById(R.id.nested_scroll_view);
        nestedScrollView.setVisibility(View.VISIBLE);

        final EditText user_fname = v.findViewById(R.id.user_fname);
        user_fname.setTag(user_fname.getKeyListener());
        final EditText user_lname = v.findViewById(R.id.user_lname);
        user_lname.setTag(user_lname.getKeyListener());
        user_birth_date = v.findViewById(R.id.user_birth_date);
        user_birth_date.setTag(user_birth_date.getKeyListener());
        final EditText user_street = v.findViewById(R.id.user_street);
        user_street.setTag(user_street.getKeyListener());
        final EditText user_number = v.findViewById(R.id.user_number);
        user_number.setTag(user_number.getKeyListener());
        final EditText user_cap = v.findViewById(R.id.user_cap);
        user_cap.setTag(user_cap.getKeyListener());
        final EditText user_exp_subject = v.findViewById(R.id.user_exp_subject);
        user_exp_subject.setTag(user_exp_subject.getKeyListener());
        final EditText user_exp_level = v.findViewById(R.id.user_exp_level);
        user_exp_level.setTag(user_exp_level.getKeyListener());
        final EditText user_exp_where = v.findViewById(R.id.user_exp_where);
        user_exp_where.setTag(user_exp_where.getKeyListener());
        final EditText user_exp_duration = v.findViewById(R.id.user_exp_duration);
        user_exp_duration.setTag(user_exp_duration.getKeyListener());
        final EditText user_academic_subject = v.findViewById(R.id.user_academic_subject);
        user_academic_subject.setTag(user_academic_subject.getKeyListener());
        final EditText user_academic_level = v.findViewById(R.id.user_academic_level);
        user_academic_level.setTag(user_academic_level.getKeyListener());
        final EditText user_academic_where = v.findViewById(R.id.user_academic_where);
        user_academic_where.setTag(user_academic_where.getKeyListener());
        final EditText user_academic_duration = v.findViewById(R.id.user_academic_duration);
        user_academic_duration.setTag(user_academic_duration.getKeyListener());
        final EditText user_lang_one = v.findViewById(R.id.user_lang_one);
        user_lang_one.setTag(user_lang_one.getKeyListener());
        final EditText user_lang_two = v.findViewById(R.id.user_lang_two);
        user_lang_two.setTag(user_lang_two.getKeyListener());
        final EditText user_lang_three = v.findViewById(R.id.user_lang_three);
        user_lang_three.setTag(user_lang_three.getKeyListener());

        //final Spinner user_gender = v.findViewById(R.id.user_gender);

        Switch registr_switch = v.findViewById(R.id.register_switch);
        registr_switch.setChecked(false);

        registr_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==false){
                    user_fname.setKeyListener(null);
                    user_lname.setKeyListener(null);
                    user_birth_date.setKeyListener(null);
                    user_street.setKeyListener(null);
                    user_number.setKeyListener(null);
                    user_cap.setKeyListener(null);
                    user_exp_subject.setKeyListener(null);
                    user_exp_level.setKeyListener(null);
                    user_exp_where.setKeyListener(null);
                    user_exp_duration.setKeyListener(null);
                    user_academic_subject.setKeyListener(null);
                    user_academic_level.setKeyListener(null);
                    user_academic_where.setKeyListener(null);
                    user_academic_duration.setKeyListener(null);
                    user_lang_one.setKeyListener(null);
                    user_lang_two.setKeyListener(null);
                    user_lang_three.setKeyListener(null);
                    birth.setClickable(false);
                   // user_gender.setClickable(false);
                    //user_gender.setEnabled(false);

                    //gets and sends new data to server
                    Post post = new Post(
                            user_fname.getText().toString(),
                            user_lname.getText().toString(),
                            user_birth_date.getText().toString(),
                            user_street.getText().toString(),
                            user_number.getText().toString(),
                            user_cap.getText().toString(),
                            user_exp_subject.getText().toString(),
                            user_exp_level.getText().toString(),
                            user_exp_where.getText().toString(),
                            user_exp_duration.getText().toString(),
                            user_academic_subject.getText().toString(),
                            user_academic_level.getText().toString(),
                            user_academic_where.getText().toString(),
                            user_academic_duration.getText().toString(),
                            user_lang_one.getText().toString(),
                            user_lang_two.getText().toString(),
                            user_lang_three.getText().toString());

                    String key = mFirebaseDatabaseReference.child("posts").push().getKey();
                    Map<String, Object> postValues= post.toMap();
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/posts/" + key, postValues);
                    mFirebaseDatabaseReference.updateChildren(childUpdates);

                }else if(isChecked==true){
                    user_fname.setKeyListener((KeyListener) user_fname.getTag());
                    user_lname.setKeyListener((KeyListener) user_lname.getTag());
                    user_birth_date.setKeyListener((KeyListener) user_birth_date.getTag());
                    user_street.setKeyListener((KeyListener) user_street.getTag());
                    user_number.setKeyListener((KeyListener) user_number.getTag());
                    user_cap.setKeyListener((KeyListener) user_cap.getTag());
                    user_exp_subject.setKeyListener((KeyListener) user_exp_subject.getTag());
                    user_exp_level.setKeyListener((KeyListener) user_exp_level.getTag());
                    user_exp_where.setKeyListener((KeyListener) user_exp_where.getTag());
                    user_exp_duration.setKeyListener((KeyListener) user_exp_duration.getTag());
                    user_academic_subject.setKeyListener((KeyListener) user_academic_subject.getTag());
                    user_academic_level.setKeyListener((KeyListener) user_academic_level.getTag());
                    user_academic_where.setKeyListener((KeyListener) user_academic_where.getTag());
                    user_academic_duration.setKeyListener((KeyListener) user_academic_duration.getTag());
                    user_lang_one.setKeyListener((KeyListener) user_lang_one.getTag());
                    user_lang_two.setKeyListener((KeyListener) user_lang_two.getTag());
                    user_lang_three.setKeyListener((KeyListener) user_lang_three.getTag());

                    birth.setClickable(true);
                   // user_gender.setClickable(true);
                   // user_gender.setEnabled(true);
                }
            }
        });


    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            user_birth_date.setText(year+"/"+month+"/"+day);
        }
    }

}
