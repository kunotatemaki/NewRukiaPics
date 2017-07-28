package com.rukiasoft.newrukiapics.di.components

import com.rukiasoft.newrukiapics.di.modules.ListPicsModule
import com.rukiasoft.newrukiapics.di.scope.CustomScopes
import com.rukiasoft.newrukiapics.ui.activities.ListPicsActivity
import dagger.Subcomponent

/**
 * Created by Roll on 28/7/17.
 */
@CustomScopes.ListPicsScope
@Subcomponent(modules = arrayOf(ListPicsModule::class))
interface ListPicsComponent {
    fun inject(activity: ListPicsActivity) : ListPicsActivity
}