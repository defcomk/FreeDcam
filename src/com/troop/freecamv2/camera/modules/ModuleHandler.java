package com.troop.freecamv2.camera.modules;

import android.util.Log;

import com.troop.freecamv2.camera.BaseCameraHolder;

import com.troop.freecam.manager.SoundPlayer;
import com.troop.freecamv2.ui.AppSettingsManager;

import java.util.HashMap;

/**
 * Created by troop on 16.08.2014.
 */
public class ModuleHandler
{
    HashMap<String, AbstractModule> moduleList;
    BaseCameraHolder cameraHolder;
    AppSettingsManager appSettingsManager;
    SoundPlayer soundPlayer;
    AbstractModule currentModule;
    final String TAG = "freecam.ModuleHandler";

    public static final String MODULE_VIDEO = "module_video";
    public static final String MODULE_PICTURE = "module_picture";
    public static final String MODULE_HDR = "module_hdr";

    public  ModuleHandler (BaseCameraHolder cameraHolder, AppSettingsManager appSettingsManager, SoundPlayer soundPlayer)
    {
        this.cameraHolder = cameraHolder;
        this.appSettingsManager = appSettingsManager;
        this.soundPlayer = soundPlayer;
        moduleList  = new HashMap<String, AbstractModule>();
        initModules();

    }

    public void SetModule(String name)
    {
        currentModule = moduleList.get(name);
        Log.d(TAG, "Set Module to " + name);
    }

    public String GetCurrentModuleName()
    {
        if (currentModule != null)
            return currentModule.name;
        else return "";
    }

    public boolean DoWork()
    {
        if (currentModule != null && !currentModule.IsWorking()) {
            currentModule.DoWork();
            return true;
        }
        else
            return false;
    }

    private void initModules()
    {
        PictureModule pictureModule = new PictureModule(cameraHolder, soundPlayer, appSettingsManager);
        moduleList.put(pictureModule.ModuleName(), pictureModule);

        VideoModule videoModule = new VideoModule(cameraHolder, soundPlayer, appSettingsManager);
        moduleList.put(videoModule.ModuleName(), videoModule);

        HdrModule hdrModule = new HdrModule(cameraHolder,soundPlayer,appSettingsManager);
        moduleList.put(hdrModule.ModuleName(), hdrModule);
    }

}
