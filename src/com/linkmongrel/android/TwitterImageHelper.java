package com.linkmongrel.android;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class TwitterImageHelper {
	
	private static final String debugTag = "ImageWorker";

    private HashMap<String, Bitmap> imageCache;
    private static Bitmap DEFAULT_ICON = null;
    private BaseAdapter adapt;
    
    
    public TwitterImageHelper (Context ctx)
    {
        imageCache = new HashMap<String, Bitmap>();
    }
    
    public Bitmap loadImage (BaseAdapter adapt, ImageView view)
    {
        this.adapt = adapt;
        String url = (String) view.getTag();
        if (imageCache.containsKey(url))
        {
            return imageCache.get(url);
        }
        else {
            new ImageTask().execute(url);
            return DEFAULT_ICON;
        }
    }
    
    private class ImageTask extends AsyncTask<String, Void, Bitmap>
    {
        private String s_url;

        @Override
        protected Bitmap doInBackground(String... params) {
            s_url = params[0];
//            InputStream istr;
//            try {
//                Log.d(debugTag, "Fetching: " + s_url);
//                URL url = new URL(s_url);
//                istr = url.openStream();
//            } catch (MalformedURLException e) {
//                Log.d(debugTag, "Malformed: " + e.getMessage());
//                throw new RuntimeException(e);
//            } catch (IOException e)
//            {
//                Log.d(debugTag, "I/O : " + e.getMessage());
//                throw new RuntimeException(e);
//                
//            }
//            return Drawable.createFromStream(istr, "src");
            
            Bitmap bitmap = null;
			try {
				bitmap = BitmapFactory.decodeStream((InputStream)new URL(s_url).getContent());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            synchronized (this) {
                imageCache.put(s_url, result);
            }
            adapt.notifyDataSetChanged();
        }
        
    }
}
