package kvr.bookit;

/**
 * Created by Kasi_Visu on 4/8/2018.
 */


import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.method.ArrowKeyMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AddDialog.AddDialogListener,
        UpdateInfo.UpdateDialogListener, DeleteDialog.DeleteDialogListener {

    Button btnAdd, btnUpdateInfo, btnShowDetails, btnDeleteInfo;
    TextView tvStdInfo;
    private String TAG = "Info";
    int btnBackPressCounter = 0;
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHandler(this);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        btnShowDetails = (Button)findViewById(R.id.btnShowDetails);
        btnUpdateInfo = (Button)findViewById(R.id.btnUpdateInfo);
        btnDeleteInfo = (Button)findViewById(R.id.btnDelete);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddDialog dialog = new AddDialog();
                dialog.show(getFragmentManager(), TAG);
            }
        });

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateInfo updateDialog = new UpdateInfo();
                updateDialog.show(getFragmentManager(),TAG);
            }
        });

        btnDeleteInfo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                DeleteDialog deleteDialog = new DeleteDialog();
                deleteDialog.show(getFragmentManager(),TAG);

            }
        });

        btnShowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //View Block Number List in the Text View Widget
                tvStdInfo = (TextView) findViewById(R.id.tvStdList);

                tvStdInfo.setMovementMethod(ArrowKeyMovementMethod.getInstance());

                tvStdInfo.setText("");	//	clear text area at each button press
                //tvStdInfo.setTextColor(Color.MAGENTA);
                tvStdInfo.setPadding(5, 2, 5, 2);

                List<Restaurant> rList = db.getAllList();

                for (Restaurant r : rList) {

                    String rDetail = "\n\nTABLE NO:" + r.get_id()+ "\n\tCUSTOMER COUNT:" + r.get_cus_no() +"\n\tNAME:" + r.get_name() + "\n\tPHONE NO:"+ r.get_phone_number();
                    tvStdInfo.append("\n"+ rDetail);

                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveButtonClick(DialogFragment dialog) {

        //		Get customer Number
        EditText encusno = (EditText) dialog.getDialog().findViewById(R.id.edtenno);
        String encusNo = encusno.getText().toString();
        int int_encusNo =Integer.parseInt(encusno.getText().toString());


//		Get Name
        EditText entName = (EditText) dialog.getDialog().findViewById(R.id.edtName);
        String name = entName.getText().toString();

        //		Get Phone Number
        EditText entPhnNo = (EditText) dialog.getDialog().findViewById(R.id.edtPhoneNo);
        String  phnNo = entPhnNo.getText().toString();



        boolean check_encusNo = checkencusNo(encusNo);

        boolean check_name = checkName(name);

        boolean check_phnNo = checkPhoneNo(phnNo);

        if(check_encusNo == false || check_name == false || check_phnNo == false){

            Toast.makeText(getApplicationContext(),"Enter Data Again",Toast.LENGTH_LONG).show();
        }
        else{
            String msg="Booking has been Confirmed for Customer "+name;
            db.addNew(new Restaurant (int_encusNo,name,phnNo));

            try
            {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phnNo, null, msg, null, null);
                Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "SMS failed, please try again later!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }



            Toast.makeText(getApplicationContext(),"Successfully Booked",Toast.LENGTH_LONG).show();

        }

        Toast.makeText(getApplicationContext(),"Customer Count:" + encusNo + "\nName: " + name + "\nPhone No:" + phnNo,Toast.LENGTH_LONG).show();

    }

    //Check Id Number
    public boolean checkIdno(String Id_No){

        if(Id_No == ""){
            return false;
        }else{
            return true;
        }

    }

    //Check Customer number
    public boolean checkencusNo(String enr_No){

        if(enr_No == ""){

            return false;
        }else{
            return true;
        }

    }

    //Check Name
    public boolean checkName(String stdName){

        if(stdName == ""){
            return false;
        }else{
            return true;
        }
    }

    //Check Phone Number
    public boolean checkPhoneNo(String phn_No){

        if(phn_No == "" || phn_No.length() != 10){

            return false;
        }else{
            return true;
        }

    }

    @Override
    public void onUpdateButtonClick(DialogFragment dialog) {


//		Get ID
        EditText entId = (EditText) dialog.getDialog().findViewById(R.id.edt_UpdateId);
        String idNo = entId.getText().toString();
        int int_idNo =Integer.parseInt(entId.getText().toString());

        //		Get customer no
        EditText entcusNo = (EditText) dialog.getDialog().findViewById(R.id.edt_UpdatecusNo);
        String encusno = entcusNo.getText().toString();
        int int_encusNo =Integer.parseInt(entcusNo.getText().toString());


//		Get Name
        EditText entName = (EditText) dialog.getDialog().findViewById(R.id.edt_UpdateName);
        String name = entName.getText().toString();

        //		Get Phone Number
        EditText entPhnNo = (EditText) dialog.getDialog().findViewById(R.id.edt_UpdatePhoneNo);
        String  phnNo = entPhnNo.getText().toString();

        boolean check_idNo = checkIdno(idNo);

        boolean check_enrollNo = checkencusNo(encusno);

        boolean check_name = checkName(name);

        boolean check_phnNo = checkPhoneNo(phnNo);

        if(check_idNo == false  || check_enrollNo == false || check_name == false || check_phnNo == false){

            Toast.makeText(getApplicationContext(),"Enter Data Again",Toast.LENGTH_LONG).show();
        }else{

            boolean updateCheck = db.updateInfo(int_idNo, int_encusNo, name, phnNo);

            if(updateCheck == true){

                Toast.makeText(getApplicationContext(),"Updation Successfully Done",Toast.LENGTH_LONG).show();}
            else{

                Toast.makeText(getApplicationContext(),"Details Updation Failed",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void callTemp(View v){

        startActivity(new Intent(MainActivity.this, SplashScreen.class));
    }

    @Override
    public void onDeleteButtonClick(DialogFragment dialog) {

        //		Get ID
        EditText entId = (EditText) dialog.getDialog().findViewById(R.id.edt_deleteID);
        String idNo = entId.getText().toString();
        int int_idNo =Integer.parseInt(entId.getText().toString());

        boolean check_idNo = checkIdno(idNo);

        if(check_idNo == false){

            Toast.makeText(getApplicationContext(),"Enter Proper Value Again",Toast.LENGTH_LONG).show();

        }else{

            boolean deleCheck = db.delete(int_idNo);

            if(deleCheck == true){

                Toast.makeText(getApplicationContext(),"Booking Deleted Successfully",Toast.LENGTH_LONG).show();}
            else{

                Toast.makeText(getApplicationContext(),"Deletion Failed",Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onBackPressed() {

        //CODE FOR EXIT ONLY IF DOUBLE BACK PRESSED
        ++btnBackPressCounter;
        if(btnBackPressCounter == 1){

            Toast.makeText(getBaseContext(), "Click Back once again to EXIT", Toast.LENGTH_SHORT).show();

        }
        else {
            finish();
        }

    }
}

