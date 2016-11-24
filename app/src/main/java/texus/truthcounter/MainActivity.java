package texus.truthcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import texus.truthcounter.components.ItemScoreBoard;
import texus.truthcounter.components.ItemYourStatus;
import texus.truthcounter.tasks.SetValuesTasks;

public class MainActivity extends BaseActivity {

    ItemScoreBoard itemScoreBoard;
    ItemYourStatus itemYourStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAppTitle();

//        setIconOnToolbar();


    }

    private void initViews() {
        itemScoreBoard = (ItemScoreBoard)
                this.findViewById(R.id.yourScoreBoard);
        itemYourStatus = (ItemYourStatus)
                this.findViewById(R.id.yourStatus);

        setValues();
    }

    private void setAppTitle() {
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(mActionBarToolbar == null) return;
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(
                getResources().getString(R.string.app_name));
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = new MenuInflater(this);
//        menuInflater.inflate(R.menu.menu_home, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    public void setValues() {
        SetValuesTasks tasks = new SetValuesTasks(this, itemScoreBoard, itemYourStatus);
        tasks.execute();
    }

    public void addBad( View view) {
        Intent i = new Intent(getApplicationContext(), AddGoodOrBadActivity.class);
        i.putExtra(PARAM_ADD_TYPE,BAD);
        startActivityForResult(i, REQUEST_CODE_FOR_ADD);
    }


    public void openMyBookActivity( View view) {
        startPage(MyBookActivity.class);
    }

    public void openDescriptionPage( View view) {
        startPage(DescriptionActivity.class);
    }

    public void openSettingsActivity( View view) {
        startPage(SettingsActivity.class);
    }

    public void addGood( View view) {
        Intent i = new Intent(getApplicationContext(), AddGoodOrBadActivity.class);
        i.putExtra(PARAM_ADD_TYPE,GOOD);
        startActivityForResult(i, REQUEST_CODE_FOR_ADD);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_FOR_ADD) {
            if (resultCode == RESULT_OK) {
                setValues();
            }
        }
    }



}

