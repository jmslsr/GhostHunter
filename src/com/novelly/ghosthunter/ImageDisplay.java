package com.novelly.ghosthunter;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDisplay extends Activity {
	private ImageView theImageView;
	private ImageView theGhostView;
	private TextView noGhosts;
	private TextView yesGhosts;
	private Button startOver;
	private Button takeAnother;
	private final Paint pGhost = new Paint(Paint.ANTI_ALIAS_FLAG);
	public int randomCircles;
	public int isThereAGhost;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.next_screen);
		
		// take the image from the first activity
		Intent fromPrevious = getIntent();
		Bitmap previousImage = (Bitmap) fromPrevious.getExtras().get("the image");
				
		// our ghost decision text views
		yesGhosts = (TextView) findViewById(R.id.yesGhostsFound);
		noGhosts  = (TextView) findViewById(R.id.noGhostsFound);
		startOver = (Button)   findViewById(R.id.startOver);
		takeAnother = (Button) findViewById(R.id.takeAnother);
		
		// defining the ghost circle
		Bitmap aGhost = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
		Canvas cGhost = new Canvas(aGhost);
		pGhost.setColor(0xFFFFFFFF);
		
		// the ghost scanning technology
		isThereAGhost=100;
		Random decision = new Random();
		Random ghost    = new Random();
		
		// if we have an even number, we have found a ghost; odd = no ghost
		if ((decision.nextInt(isThereAGhost) % 2) == 0) {
			yesGhosts.setVisibility(View.VISIBLE);
			noGhosts.setVisibility(View.INVISIBLE);
			startOver.setVisibility(View.INVISIBLE);
			takeAnother.setVisibility(View.VISIBLE);
			randomCircles=1;
			for(int i = 0; i < randomCircles; i++) {
				int x = ghost.nextInt(10);
				int y = ghost.nextInt(10);
				int r = 1;
				cGhost.drawCircle(x, y, r, pGhost);
				
				theImageView = (ImageView) findViewById(R.id.thePicture);
				theImageView.setImageBitmap(previousImage);
				theImageView.setMinimumHeight(540);
				theImageView.setMinimumWidth(380);
				
				theGhostView = (ImageView) findViewById(R.id.theGhost);
				theGhostView.setBackgroundDrawable(new BitmapDrawable(aGhost));
			}
		} else {
			yesGhosts.setVisibility(View.INVISIBLE);
			takeAnother.setVisibility(View.INVISIBLE);
			noGhosts.setVisibility(View.VISIBLE);
			startOver.setVisibility(View.VISIBLE);
		}
		
	} // end onCreate
	
	// set up our button to start again
	public void goBack(View view) {
		Intent tryAgain = new Intent(this, GhostHunterActivity.class);
		this.startActivity(tryAgain);
	}
}
