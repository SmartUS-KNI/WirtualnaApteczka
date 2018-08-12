package com.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

import java.io.File;

public class MyGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "smartcity.kni.wirtualnaapteczka"); //

        Entity dose = schema.addEntity("Dose");
        dose.addIdProperty().autoincrement();
        dose.addIntProperty("count");
        dose.addStringProperty("config");
        //FK
        Property periodIdFkDose = dose.addLongProperty("idPeriod").getProperty();
        Property medicineIdFkDose = dose.addLongProperty("idMedicine").getProperty();

        Entity period = schema.addEntity("Period");
        period.addIdProperty().autoincrement();
        period.addStringProperty("name");

        Entity medicine = schema.addEntity("Medicine");
        medicine.addIdProperty().autoincrement();
        medicine.addStringProperty("name");
        medicine.addStringProperty("description");
        medicine.addStringProperty("type");
        //FK
        Property responsibleSubjectIdFkMedicine = medicine.addLongProperty(
                "idResponsibleSubject").getProperty();
        Property formIdFkMedicine = medicine.addLongProperty(
                "idForm").getProperty();

        Entity pack = schema.addEntity("Pack");
        pack.addIdProperty().autoincrement();
        pack.addIntProperty("size");
        pack.addStringProperty("unit");
        pack.addStringProperty("eanCode");
        //FK
        Property medicineIdFKPack = pack.addLongProperty(
                "medicineId").getProperty();

        Entity form = schema.addEntity("Form");
        form.addIdProperty().autoincrement();
        form.addStringProperty("name");
        form.addBooleanProperty("countable");

        Entity activeSubstancePosition = schema.addEntity("ActiveSubstancePosition");
        activeSubstancePosition.addIdProperty().autoincrement();
        //FK
        Property activeSubstanceIdFkActiveSubstancePosition = activeSubstancePosition.addLongProperty(
                "activeSubstanceId").getProperty();
        Property medicineIdFkActiveSubstancePosition = activeSubstancePosition.addLongProperty(
                "medicineId").getProperty();

        Entity activeSubstance = schema.addEntity("ActiveSubstance");
        activeSubstance.addIdProperty().autoincrement();
        activeSubstance.addStringProperty("name");

        Entity informationType = schema.addEntity("InformationType");
        informationType.addIdProperty().autoincrement();
        informationType.addStringProperty("name");

        Entity information = schema.addEntity("Information");
        information.addIdProperty().autoincrement();
        information.addStringProperty("message");
        information.addStringProperty("config");
        //FK
        Property informationTypeIdFkInformation = information.addLongProperty(
                "informationTypeId").getProperty();

        //Relations
        medicine.addToMany(dose, medicineIdFkDose);
        dose.addToOne(medicine, medicineIdFkDose);

        medicine.addToOne(form, formIdFkMedicine);
        form.addToMany(medicine, formIdFkMedicine);

        pack.addToOne(medicine, medicineIdFKPack);
        medicine.addToMany(pack, medicineIdFKPack);

        period.addToMany(dose, periodIdFkDose);
        dose.addToOne(period, periodIdFkDose);

        activeSubstancePosition.addToOne(medicine, medicineIdFkActiveSubstancePosition);
        medicine.addToMany(activeSubstancePosition, medicineIdFkActiveSubstancePosition);

        activeSubstancePosition.addToOne(activeSubstance, activeSubstanceIdFkActiveSubstancePosition);
        activeSubstance.addToMany(activeSubstancePosition, activeSubstanceIdFkActiveSubstancePosition);

        information.addToOne(informationType, informationTypeIdFkInformation);
        informationType.addToMany(information, informationTypeIdFkInformation);

        new File("..\\app\\src\\main\\java\\generatedDatabaseTables").mkdir();
        new DaoGenerator().generateAll(schema, "..\\app\\src\\main\\java\\generatedDatabaseTables");
    }
}