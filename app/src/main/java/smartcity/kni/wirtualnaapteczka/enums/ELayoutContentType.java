package smartcity.kni.wirtualnaapteczka.enums;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import smartcity.kni.wirtualnaapteczka.listeners.OnConvertListener;

/**
 * Created by Aleksander on 11.01.2018.
 */

public enum ELayoutContentType {
    LAYOUT_CONTENT_TYPE_TEXTVIEW(android.support.v7.widget.AppCompatTextView.class),
    LAYOUT_CONTENT_TYPE_EDITTEXT(android.support.v7.widget.AppCompatEditText.class),
    LAYOUT_CONTENT_TYPE_BUTTON(android.support.v7.widget.AppCompatButton.class),
    LAYOUT_CONTENT_TYPE_CHECKBOX(android.support.v7.widget.AppCompatCheckBox.class),
    LAYOUT_CONTENT_TYPE_SPINNER(android.support.v7.widget.AppCompatSpinner.class);

    private Class<?> contentClass;
    private OnConvertListener listener;

    private ELayoutContentType(Class<?> contentClass) {
        this.contentClass = contentClass;
    }

    public Class<?> getContentClass() {
        return this.contentClass;
    }

    public void setConverter(OnConvertListener listener) {
        this.listener = listener;
    }

    public OnConvertListener getConverter() {
        return this.listener;
    }

    public static boolean hasContentImplementation(Class<?> contentClass) {
        for(ELayoutContentType i: values()) {
            if(i.getContentClass() == contentClass)
                return true;
        }

        return false;
    }
}
