package me.khol.intellij.plugin.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * Supports storing the application settings in a persistent way.
 * The State and Storage annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(
    name = "me.khol.intellij.plugin.settings.AppSettingsState",
    storages = [Storage("SdkSettingsPlugin.xml")]
)
class AppSettingsState : PersistentStateComponent<AppSettingsState?> {

    @JvmField
    var annotationsFqn = "com.sample.analytics.Lowlighting"

    override fun getState(): AppSettingsState? = this

    override fun loadState(state: AppSettingsState) = XmlSerializerUtil.copyBean(state, this)

    companion object {
        val instance: AppSettingsState
            get() = ServiceManager.getService(AppSettingsState::class.java)
    }
}