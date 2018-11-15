package com.adel.currencyexchange2;

public class Presentation implements Contract.Presentation {

   Contract.View view;
   Contract.Model model = new Model();


    @Override
    public void AttachView(Contract.View view) {
       this.view = view;
       model.AttachPresentation(this);

    }

    @Override
    public void getResult() {

       model.getResult();

    }

    @Override
    public void onShowResult() {

        view.onShowResult();

    }

    @Override
    public void onFailureResult() {

        view.onFailureResult();

    }
}
