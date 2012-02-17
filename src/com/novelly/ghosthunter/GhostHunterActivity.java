package com.novelly.ghosthunter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class GhostHunterActivity extends Activity {
	
    final int CAMERA_PIC_REQUEST = 200; // request code for taking a new picture
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main); 
    }
    	    
	public void getCamera(View view) { // onClick method for taking a new picture
		Intent getPicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(getPicture, CAMERA_PIC_REQUEST);
	}
	    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // handling the activity results
    	
    	Intent nextScreen 		= new Intent(GhostHunterActivity.this, ImageDisplay.class);
    	Bitmap selectedImage 	= null;
    	
    	if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
	    	selectedImage = (Bitmap) data.getExtras().get("data");
	        nextScreen.putExtra("the image", selectedImage);	
	        startActivity(nextScreen);
    	}
    }
}