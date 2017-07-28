package com.rukiasoft.newrukiapics.di.modules

import com.rukiasoft.newrukiapics.di.scope.CustomScopes
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
import com.rukiasoft.newrukiapics.ui.observers.ListPicsObserver
import com.rukiasoft.newrukiapics.ui.presenters.ListPicsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Roll on 28/7/17.
 */

@Module
class ListPicsModule {

    @Provides
    @CustomScopes.ListPicsScope
    fun providesPresenter(presenter: ListPicsPresenter) : ListPicsContracts.PresenterContracts{
        return presenter
    }

    @Provides
    @CustomScopes.ListPicsScope
    fun providesObserver(observer: ListPicsObserver) : ListPicsContracts.ObserverContracts{
        return observer
    }
}