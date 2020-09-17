package app.jugaadfunda.learningcompanion.utility;

import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.FileFilter;

public class ImageFileFilter implements FileFilter {
    File file;
    private final String[] okFileExtensions = new String[] {
            "jpg",
            "png",
            "gif",
            "jpeg"
    };

    public ImageFileFilter(File newfile) {
        this.file = newfile;
    }

    public boolean accept(File file) {
        for (String extension: okFileExtensions) {
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

}
