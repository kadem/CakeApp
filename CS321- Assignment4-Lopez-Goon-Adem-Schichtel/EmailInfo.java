//https://www.c-sharpcorner.com/blogs/send-to-email-app-in-android-application-using-android-studio
package bakery.cake.bakeryorder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmailInfo  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {//when class is started
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_info);//sets the email form
        Button startBtn = (Button) findViewById(R.id.sendbttn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();//if send button is clicked start send email method
            }
        });
    }

    protected void sendEmail() {
        EditText toText = (EditText) findViewById(R.id.editText);//reciprent
        EditText subjText = (EditText) findViewById(R.id.editText2);//subject
        EditText messageText = (EditText) findViewById(R.id.editText3);//message
        Log.i("Send email", "");

        String to = toText.getText().toString();//gets user's reciprient from To field
        String subj = subjText.getText().toString();//gets user's subject from subject field
        String message = messageText.getText().toString();//gets user's message from message field

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{to});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subj);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));//opens gmail and
            finish();                                        //adds fields into a new email message
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EmailInfo.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
