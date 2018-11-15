package com.adel.currencyexchange2;

import java.util.ArrayList;

public class Model implements Contract.Model {

    Contract.Presentation presentation;

    @Override
    public void AttachPresentation(Contract.Presentation presentation) {
     this.presentation = presentation;

    }

    @Override
    public void getResult() {


        long millis = System.currentTimeMillis() % 1000;
        Gen.MoneydataModels.clear();
        Gen.MoneydataModels.add(new MoneyDataModel(millis+"", "دلار کانادا", "100 تومان افزایش قیمت",1));
        Gen.MoneydataModels.add(new MoneyDataModel("126500", "دلار امریکا", "بدون تغییر",0));
        Gen.MoneydataModels.add(new MoneyDataModel("143600", "یورو", "200 تومان افزایش قیمت",1));
        Gen.MoneydataModels.add(new MoneyDataModel("12600", "لیر ترکیه", "10 تومان کاهش قیمت",-1));
        Gen.MoneydataModels.add(new MoneyDataModel("15000", "درهم امارات", "بدون تغییر",0));


        Gen.GolddataModels.clear();
        Gen.GolddataModels.add(new MoneyDataModel("424100", "گرم طلای 18", "100 تومان افزایش قیمت",1));
        Gen.GolddataModels.add(new MoneyDataModel("565460", "گرم طلای 24", "بدون تغییر",0));
        Gen.GolddataModels.add(new MoneyDataModel("4400000", "سکه بهار آزادی", "200 تومان افزایش قیمت",1));
        Gen.GolddataModels.add(new MoneyDataModel("4610000", "سکه امامی", "10 تومان کاهش قیمت",-1));
        Gen.GolddataModels.add(new MoneyDataModel("2350000", "نیم سکه", "بدون تغییر",0));
        Gen.GolddataModels.add(new MoneyDataModel("1175000", "ربع سکه", "بدون تغییر",0));
        Gen.GolddataModels.add(new MoneyDataModel("680000", "سکه گرمی", "بدون تغییر",0));

        presentation.onShowResult();

    }





}
