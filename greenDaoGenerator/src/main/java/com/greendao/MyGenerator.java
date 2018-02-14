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


        Entity alergens_list = schema.addEntity("Alergens_List");
        alergens_list.addIdProperty().autoincrement();
        //FK
        Property medicineIdFkAlergensList = alergens_list.addLongProperty(
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
        medicine.addStringProperty("tag");
        medicine.addStringProperty("EAN");
        //FK

        Entity tags_list = schema.addEntity("Tags_List");
        tags_list.addIdProperty().autoincrement();
        //FK
        Property medicineIdFKTagsList = tags_list.addLongProperty(
                "medicineId").getProperty();
        Property tagIdFKTagsList = tags_list.addLongProperty(
                "tagId").getProperty();

        Entity tag = schema.addEntity("Tag");
        tag.addIdProperty().autoincrement();
        tag.addStringProperty("name");


        //Relations

        alergen.addToMany(alergens_list, alergenIdFk).setName("alergensList");
        alergens_list.addToOne(alergen, alergenIdFk);
        //alergens_list.addToOne(alergen, alergen.getPkProperty());

        medicine.addToMany(alergens_list, medicineIdFkAlergensList).setName("alergensList");
        alergens_list.addToOne(medicine, medicineIdFkAlergensList);
        //alergens_list.addToOne(medicine, medicine.getPkProperty());

        medicine.addToMany(dose, medicineIdFkDose);
        dose.addToOne(medicine, medicineIdFkDose);
        //dose.addToOne(medicine, medicine.getPkProperty());

        medicine.addToMany(information, medicineIdFkInfo);
        information.addToOne(medicine, medicineIdFkInfo);
        //information.addToOne(medicine, medicine.getPkProperty());

        medicine.addToMany(tags_list,medicineIdFKTagsList).setName("tagsList");
        tags_list.addToOne(medicine, medicineIdFKTagsList);

        tag.addToMany(tags_list, tagIdFKTagsList).setName("tagsList");
        tags_list.addToOne(tag, tagIdFKTagsList);

        new DaoGenerator().generateAll(schema, "..\\app\\src\\main\\java\\generatedByGreenDao");
    }
}