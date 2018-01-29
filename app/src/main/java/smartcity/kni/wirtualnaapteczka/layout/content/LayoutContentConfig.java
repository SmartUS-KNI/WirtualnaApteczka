package smartcity.kni.wirtualnaapteczka.layout.content;

import java.util.ArrayList;
import java.util.List;

import smartcity.kni.wirtualnaapteczka.enums.ELayoutContentType;

/**
 * Created by Aleksander on 11.01.2018.
 */

public class LayoutContentConfig {

    private List<ELayoutContentType> params;

    public LayoutContentConfig() {
        params = new ArrayList<ELayoutContentType>();
    }

    public List<ELayoutContentType> getLayoutContentConfig() {
        return this.params;
    }

    public void addLayoutContentConfigParam(ELayoutContentType param) {
        params.add(param);
    }

    public void resetLayoutContentConfig() {
        params.clear();
    }
}
