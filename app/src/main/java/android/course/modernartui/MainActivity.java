package android.course.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private DialogFragment dialogMoreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up all our Textviews
        final TextView fibo0 = (TextView) findViewById(R.id.fibo0);
        final TextView fibo1 = (TextView) findViewById(R.id.fibo1);
        final TextView fibo2 = (TextView) findViewById(R.id.fibo2);
        final TextView fibo3 = (TextView) findViewById(R.id.fibo3); /* Grey, so we don't change it's colour */
        final TextView fibo5 = (TextView) findViewById(R.id.fibo5);

        final int baseColorFibo0 = ((ColorDrawable) fibo0.getBackground()).getColor();
        final int baseColorFibo1 = ((ColorDrawable) fibo1.getBackground()).getColor();
        final int baseColorFibo2 = ((ColorDrawable) fibo2.getBackground()).getColor();
        final int baseColorFibo5 = ((ColorDrawable) fibo5.getBackground()).getColor();

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // We set the TextViews to new colours, we use different multipliers for progress.
                fibo0.setBackgroundColor(baseColorFibo0 + (int)1.5*progress);
                fibo1.setBackgroundColor(baseColorFibo1 - 2*progress);
                fibo2.setBackgroundColor(baseColorFibo2 - (int)1.5*progress);
                fibo5.setBackgroundColor(baseColorFibo5 - progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showMoreInformationDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showMoreInformationDialog()
    {
        dialogMoreInfo = moreInfoDialogFragment.newInstance();
        dialogMoreInfo.show(getFragmentManager(), "Info");
        dialogMoreInfo.setStyle(DialogFragment.STYLE_NORMAL, R.style.modernart_theme);
    }

    // Class for creating our more information dialog
    public static class moreInfoDialogFragment extends DialogFragment {

        public static moreInfoDialogFragment newInstance() {
            return new moreInfoDialogFragment();
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            TextView view = new TextView(getActivity().getBaseContext());
            view.setGravity(Gravity.CENTER);
            view.setBackgroundColor(getResources().getColor(R.color.SteelBlue));
            view.setText(R.string.dialog_description);

            return new AlertDialog.Builder(getActivity()).setView(view)
                    //.setMessage(R.string.dialog_description)
                    .setCancelable(false)
                    .setPositiveButton(R.string.dialog_cancellation, // Note we want cancellation on the right, i.e. the yes option
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.dismiss();
                                }
                            })

                            // Set up Yes Button
                    .setNegativeButton(R.string.dialog_true_selection, // Note we want Visit MOMa on the left, i.e. the no option
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog, int id) {
                                    Intent implicitBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                                    startActivity(implicitBrowser);
                                }
                            }).create();
        }
    }


}
