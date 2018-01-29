package smartcity.kni.wirtualnaapteczka.layout.content;

import java.util.HashMap;
import java.util.Map;

import smartcity.kni.wirtualnaapteczka.enums.ELayoutContentType;

/**
 * Created by Aleksander on 11.01.2018.
 */

public class LayoutContent {

    private Map<Integer, Object> content;
    private Integer idParentView;
    private LayoutContentConfig config;

    public LayoutContent(Integer idParentView, LayoutContentConfig config) {
        this.content = new HashMap<Integer, Object>();
        this.idParentView = new Integer(idParentView);
        this.config = new LayoutContentConfig();

        for(ELayoutContentType type: config.getLayoutContentConfig()) {
            this.config.addLayoutContentConfigParam(type);
        }
    }

    public Map<Integer, Object> getContentMap() {
        return content;
    }

    public Integer getIdParentView() {
        return this.idParentView;
    }

    public void setIdLayout(Integer idLayout) {
        this.idParentView = idLayout;
    }

    public LayoutContentConfig getConfig() {
        return this.config;
    }

    public void addContentParam(Integer idView, Object contentParam) {
        content.put(idView,contentParam);
    }
}
