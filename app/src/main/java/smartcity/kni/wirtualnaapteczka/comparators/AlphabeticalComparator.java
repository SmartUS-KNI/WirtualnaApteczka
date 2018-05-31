package smartcity.kni.wirtualnaapteczka.comparators;

import java.util.List;

import smartcity.kni.wirtualnaapteczka.Medicine;

/**
 * Created by KozMeeN on 31/05/2018.
 */

public class AlphabeticalComparator implements Comparator {
    @Override
    public List<Medicine> compare(List<Medicine> medicine) {

        for(int i=0;i<medicine.size()-1;i++){
            for(int j=0;j<medicine.size()-1;j++){
                if(medicine.get(j).getName().compareTo(medicine.get(j+1).getName())>0){
                   Medicine temporary = medicine.get(j);
                    medicine.set(j,medicine.get(j+1));
                    medicine.set(j+1,temporary);
                }
            }
        }
        return medicine;
    }
}
