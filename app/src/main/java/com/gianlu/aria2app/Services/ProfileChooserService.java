package com.gianlu.aria2app.Services;

import android.content.ComponentName;
import android.content.IntentFilter;
import android.os.Build;
import android.service.chooser.ChooserTarget;
import android.service.chooser.ChooserTargetService;
import android.support.annotation.RequiresApi;

import com.gianlu.aria2app.ProfilesManager.MultiProfile;
import com.gianlu.aria2app.ProfilesManager.ProfilesManager;
import com.gianlu.commonutils.LettersIcons.DrawingHelper;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)
public class ProfileChooserService extends ChooserTargetService {
    @Override
    public List<ChooserTarget> onGetChooserTargets(ComponentName targetActivityName, IntentFilter matchedFilter) {
        DrawingHelper helper = new DrawingHelper(this);
        List<MultiProfile> profiles = ProfilesManager.get(this).getProfiles();
        List<ChooserTarget> targets = new ArrayList<>();
        for (MultiProfile profile : profiles)
            targets.add(profile.getChooserTarget(helper, targetActivityName));
        return targets;
    }
}