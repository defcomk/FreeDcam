package com.troop.freedcam.camera.parameters.modes;

import android.hardware.Camera;
import com.troop.freedcam.camera.parameters.I_ParameterChanged;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by troop on 18.08.2014.
 */
public class PictureSizeParameter extends BaseModeParameter
{
    public PictureSizeParameter(HashMap<String, String> parameters,I_ParameterChanged parameterChanged, String value, String values) {
        super(parameters, parameterChanged, value, values);
    }

    @Override
    public void SetValue(String valueToSet, boolean setToCam)
    {
        parameters.put("picture-size" , valueToSet);
        if (throwParameterChanged != null && setToCam)
            throwParameterChanged.ParameterChanged();
    }
}
