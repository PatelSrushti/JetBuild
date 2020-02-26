package com.meditab.jetbuild

sealed class UIState {

    object NoInternet : UIState()
    object Loading : UIState()
    object Error : UIState()
    object NoData : UIState()
    object HasData : UIState()

}