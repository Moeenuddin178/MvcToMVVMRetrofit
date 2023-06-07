# Convert Simple Retrofit to MVVM 
->Adding View Model <br>
-> Bussiness logic move from Mainactivity to View Model  <br>
->Configuration changes like screen rotate is maintained by live data viewmodel <br>
->viewModel only destroy if owner(Activity /fagment ) is completely destroy or finish <br>
->in mvc screen rotation persistance is achieved <br>
__  by using onSavedInstance() Method Saving bundle inside <br>
__  onsaved in bundle only small data not able to store large list thats y use viewModel


