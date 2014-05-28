package ipcm.calc.nprice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Info extends Activity{

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        
        // Gets the current width and height of the display to use for layout
        Display display = getWindowManager().getDefaultDisplay();
        final int width = display.getWidth();
        final int height = display.getHeight();
        
        // Get the layout objects
        ImageView npm_logo = (ImageView) findViewById(R.id.npm_logo);
        TextView infoText = (TextView) findViewById(R.id.info_text_view);
        TextView copyText = (TextView) findViewById(R.id.copyright_text_view);
        
        infoText.setPadding(width/24, width/24, width/24, width/24);
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        
        params.leftMargin = 0;
        params.rightMargin = 0;
        params.topMargin = 0;
        params.bottomMargin = 0;
        
        infoText.setLayoutParams(params);
        
        npm_logo.setPadding(0, 0, 0, 0);
        
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        
        params.leftMargin = 0;
        params.rightMargin = 0;
        params.topMargin = 0;
        params.bottomMargin = 0;
        
        npm_logo.setLayoutParams(params);
        
        copyText.setPadding(width/24, width/24, width/24, width/24);
        
    }
}
