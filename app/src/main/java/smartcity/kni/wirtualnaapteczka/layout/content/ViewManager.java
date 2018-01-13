package smartcity.kni.wirtualnaapteczka.layout.content;

import smartcity.kni.wirtualnaapteczka.enums.ELayoutContentType;
import smartcity.kni.wirtualnaapteczka.exceptions.MissingConverterException;
import smartcity.kni.wirtualnaapteczka.listeners.OnConvertListener;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Aleksander on 11.01.2018.
 */

public class ViewManager {
    private static final ViewManager instance = new ViewManager();

    public static ViewManager getInstance() {
        return instance;
    }

    private ViewManager() {
    }

    public LayoutContent getContent(View view) throws MissingConverterException {
        LayoutContentConfig config = this.createDefaultLayoutContentConfig();

        LayoutContent content = this.getContent(view, config);

        return content;
    }

    public LayoutContent getContent(View view, LayoutContentConfig config) throws MissingConverterException {
        LayoutContent content = new LayoutContent(view.getId(),config);

        if(isViewGroup(view)) {
            ViewGroup viewGroup = (ViewGroup)view;
            View childView = null;

            for(int i=0; i<viewGroup.getChildCount(); i++) {
                childView = viewGroup.getChildAt(i);

                if(isViewGroup(childView)) {
                    LayoutContent childContent = this.getContent(childView, config);

                    Map<Integer, Object> map = childContent.getContentMap();

                    Iterator<Integer> iterator = map.keySet().iterator();

                    Integer key;

                    while(iterator.hasNext()) {
                        key = iterator.next();
                        content.addContentParam(key,map.get(key));
                    }
                }
                else {
                    for(ELayoutContentType type: ELayoutContentType.values()) {
                        if(childView.getClass() == type.getContentClass())
                            content.addContentParam(childView.getId(), this.convertView(childView, type));
                    }
                }
            }
        }
        else {
            for(ELayoutContentType type: ELayoutContentType.values()) {
                if(view.getClass() == type.getContentClass()) {
                    content.addContentParam(view.getId(), this.convertView(view, type));
                }
            }
        }

        return content;
    }

    public void setContentTypeConverters() throws MissingConverterException {
        for(ELayoutContentType type: ELayoutContentType.values()) {
            switch(type) {

                //TODO: DEFINE WHOLE CONTENT TYPE CONVERTERS IN INDIVIDUAL CASES
                //EXAMPLE:
                case LAYOUT_CONTENT_TYPE_TEXTVIEW: {
                    type.setConverter(new OnConvertListener() {
                        @Override
                        public Object onConvert(View view) {
                            TextView obj = (TextView)view;
                            return obj.getText().toString();
                        }
                    });

                    break;
                }
                default: {
                    throw new MissingConverterException(type.name() + " has undefined converter."); //MESSAGE: LAYOUT_CONTENT_TYPE_[...] has undefined converter
                }
            }
        }
    }

    private LayoutContentConfig createDefaultLayoutContentConfig() {
        LayoutContentConfig config = new LayoutContentConfig();

        for(ELayoutContentType type: ELayoutContentType.values()) {
            config.addLayoutContentConfigParam(type);
        }

        return config;
    }

    private boolean isViewGroup(View view) {
        if(view instanceof ViewGroup)
            return true;
        else
            return false;
    }

    private Object convertView(View view, ELayoutContentType type) throws MissingConverterException {
        if(type.getConverter() == null)
            throw new NullPointerException(type.name() + " hasn't converter implementation");

        return type.getConverter().onConvert(view);
    }
}
