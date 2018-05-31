package smartcity.kni.wirtualnaapteczka.comparators;

import java.util.List;

import smartcity.kni.wirtualnaapteczka.Medicine;

/**
 * Created by lebio on 31/05/2018.
 */

public interface Comparator {
     List<Medicine> compare(List<Medicine> medicine);
}
