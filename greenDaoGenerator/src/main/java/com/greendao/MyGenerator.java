package com.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "smartcity.kni.wirtualnaapteczka"); //

        Entity dose = schema.addEntity("Dose");
        dose.addIdProperty().autoincrement();
        dose.addDateProperty("time");
        dose.addIntProperty("count");
        //FK
        Property medicineIdFkDose = dose.addLongProperty("idMedicine").getProperty();

        Entity alergen = schema.addEntity("Alergen");
        alergen.addIdProperty().autoincrement();
        alergen.addStringProperty("name");
        //FK


        Entity alergens_list = schema.addEntity("Alergens_List");
        alergens_list.addIdProperty().autoincrement();
        //FK
        Property medicineIdFkList = alergens_list.addLongProperty(
                "medicineId").getProperty();
        Property alergenIdFk = alergens_list.addLongProperty(
                "alergenId").getProperty();

        Entity information = schema.addEntity("Information");
        information.addIdProperty().autoincrement();
        information.addStringProperty("message");
        information.addDateProperty("date");
        information.addDateProperty("time");
        information.addBooleanProperty("alert");
        information.addBooleanProperty("regular");
        //FK
        Property medicineIdFkInfo = information.addLongProperty(
                "idMedicine").getProperty();

        Entity medicine = schema.addEntity("Medicine");
        medicine.addIdProperty().autoincrement();
        medicine.addStringProperty("name");
        medicine.addStringProperty("description");
        medicine.addDateProperty("expiryDate");
        medicine.addStringProperty("tag");
        medicine.addStringProperty("EAN");
        //FK
        Property informationIdFk = medicine.addLongProperty(
                "idInformation").getProperty();
        Property doseIdFk = medicine.addLongProperty(
                "idDose").getProperty();

        //Relations

        //Alergen - Alergens_list
        alergens_list.addToMany(alergen, alergenIdFk);
        alergen.addToOne(alergens_list, alergenIdFk);
        //Medicine - Alergens_list
        alergens_list.addToMany(medicine, medicineIdFkList);
        medicine.addToOne(alergens_list, medicineIdFkList);
        //Medicine - Dose
        medicine.addToOne(dose, medicineIdFkDose);
        dose.addToMany(medicine, medicineIdFkDose);
        //Medicine - Information
        medicine.addToOne(information, informationIdFk);
        information.addToMany(medicine, informationIdFk);


        new DaoGenerator().generateAll(schema,"..\\app\\src\\main\\java\\generatedByGreenDao");
    }
}


/*      //OLD RELATIONS - DON'T WORK
        //Relations ToOne
        alergen.addToOne(alergens_list, alergenIdFk);

        medicine.addToOne(alergens_list, medicineIdFkDose);
        medicine.addToOne(dose, doseIdFk);
        medicine.addToOne(information, informationIdFk);

        //Relations ToMany
        alergens_list.addToMany(alergen, alergen.getPkProperty()).setName("alergens");
        alergens_list.addToMany(medicine, medicine.getPkProperty()).setName("medykamenty");

        dose.addToMany(medicine, medicine.getPkProperty());

        information.addToMany(medicine, medicine.getPkProperty());
*/