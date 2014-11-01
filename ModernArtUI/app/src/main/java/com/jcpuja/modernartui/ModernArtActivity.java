package com.jcpuja.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ModernArtActivity extends Activity {

    private static final int MAX_VERTICAL_SIZE = 6;
    private static final int MIN_VERTICAL_SIZE = 3;
    private static final int MAX_HORIZONTAL_SIZE = 7;
    private static final int MIN_HORIZONTAL_SIZE = 3;

    public static final String DIALOG_TAG = "MoreInfoDialog";
    public static final Uri MOMA_URL = Uri.parse("http://www.moma.org/collection/browse_results.php?criteria=O%3AAD%3AE%3A4057&page_number=9&template_id=1&sort_order=1");

    private Random random = new Random();

    private List<List<ColoredRectangle>> rectangles = new ArrayList<List<ColoredRectangle>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art);

        LinearLayout vertical = (LinearLayout) findViewById(R.id.verticalLayout);

        int verticalSize = getRandomSize(MIN_VERTICAL_SIZE, MAX_VERTICAL_SIZE);
        int whiteRectangleY = random.nextInt(verticalSize);

        for (int y = 0; y < verticalSize; y++) {
            List<ColoredRectangle> thisHorizontalLine = new ArrayList<ColoredRectangle>();

            LinearLayout horizontal = new LinearLayout(this);
            horizontal.setOrientation(LinearLayout.HORIZONTAL);
            horizontal.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (1f / verticalSize)));

            int horizontalSize = getRandomSize(MIN_HORIZONTAL_SIZE, MAX_HORIZONTAL_SIZE);
            int whiteRectangleX = random.nextInt(horizontalSize);

            for (int x = 0; x < horizontalSize; x++) {
                ColoredRectangle rectangle = (x == whiteRectangleX && y == whiteRectangleY) ?  //
                        new WhiteRectangle(this, horizontalSize) : //
                        new ColoredRectangle(this, horizontalSize); //

                horizontal.addView(rectangle);

                thisHorizontalLine.add(rectangle);
            }

            vertical.addView(horizontal);
            rectangles.add(thisHorizontalLine);
        }


        SeekBar colorModifier = (SeekBar) findViewById(R.id.colorModifier);
        colorModifier.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateRectanglesColor(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateRectanglesColor(int modifier) {
        for (List<ColoredRectangle> horizontalLine : rectangles) {
            for (ColoredRectangle rectangle : horizontalLine) {
                rectangle.adjustColor(modifier);
            }

        }

    }

    private int getRandomSize(int min, int max) {
        return random.nextInt(max - min) + min;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.modern_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_more_info) {
            MoreInfoDialog.newInstance().show(getFragmentManager(), DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MoreInfoDialog extends DialogFragment {

        public static MoreInfoDialog newInstance() {
            return new MoreInfoDialog();
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage(getResources().getString(R.string.more_info_message))

                    .setNegativeButton(getResources().getString(R.string.more_info_notnow),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    // do nothing
                                }
                            })

                    .setPositiveButton(getResources().getString(R.string.more_info_opensite),
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog, int id) {

                                    startActivity(new Intent(Intent.ACTION_VIEW, MOMA_URL));
                                }
                            }).create();
        }
    }

}
