package com.adel.currencyexchange2;



public interface Contract {

    interface  View {

       void onShowResult ();
       void onFailureResult();
    }


    interface  Presentation {

        void AttachView (View view);
        void getResult();
        void onShowResult();
        void onFailureResult();

    }


    interface Model {

        void AttachPresentation(Presentation presentation);
        void getResult();


    }

}
