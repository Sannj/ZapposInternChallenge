package impeccableshyte.sample.productdetail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class ProductDetailsActivity extends AppCompatActivity {

    private BottomSheetBehavior<View> behavior;
    private Bitmap imageBitmap;
    private ImageView imageView;

    int sLoc[] = new int[2];
    int dLoc[] = new int[2];
    float sX;
    float sY;
    float dX;
    float dY;
    TranslateAnimation moveImage;
    MenuItem mt;
    Bitmap scaled;
    ImageView scaledImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Log.d("XYZ",String.valueOf(dX));

        setSupportActionBar(toolbar);
        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.prod_image);


        /*moveImage = new TranslateAnimation(sX, dX, sY, dY);
        moveImage.setDuration(2000);
        moveImage.setFillAfter(true);
*/
        imageView = (ImageView) findViewById(R.id.product_image);
        imageView.setImageBitmap(imageBitmap);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int inWidth = imageBitmap.getWidth();
                int inHeight = imageBitmap.getHeight();
                scaled = Bitmap.createScaledBitmap(imageBitmap, inWidth, inHeight, true);
                scaledImage = (ImageView) findViewById(R.id.scaled_image);
                scaledImage.setImageBitmap(scaled);
                View ScaledImageView = findViewById(R.id.scaled_image);
                ScaledImageView.getLocationOnScreen(sLoc);
                sX = sLoc[0];
                sY = sLoc[1];
                moveImage = new TranslateAnimation(sX, dX, sY, -1);
                moveImage.setDuration(2000);
                moveImage.setFillAfter(true);
                //scaledImage.bringToFront();
              //  ScaledImageView.bringToFront();
             //   imageView.setVisibility(View.INVISIBLE);
                scaledImage.setImageBitmap(scaled);
                scaledImage.startAnimation(moveImage);
               // ScaledImageView.startAnimation(moveImage);

                /*Snackbar.make(view, "Do more with less", Snackbar.LENGTH_SHORT).setAction("YES!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getLocationOnScreen(sLoc);
                        sX = sLoc[0];
                        sY = sLoc[1];
                        Log.d("Check", String.valueOf(sY));
                    }
                }).show();*/
            }
        });

        View bottomSheet = findViewById(R.id.product_details_bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_details, menu);
       /* mt = (MenuItem) findViewById(R.id.cart);
        int id = mt.getItemId();
        Toast.makeText(this, String.valueOf(id),Toast.LENGTH_LONG).show();*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(item.getItemId() == R.id.cart) {
            View menuView = findViewById(R.id.cart);

            menuView.getLocationOnScreen(dLoc);
            dX = dLoc[0];
            dY = dLoc[1];
            Log.d("location",String.valueOf(dX));

        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

}
