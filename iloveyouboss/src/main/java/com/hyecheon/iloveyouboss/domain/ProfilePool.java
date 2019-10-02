package com.hyecheon.iloveyouboss.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProfilePool {
    private List<Profile> profiles = new ArrayList<>();

    public void add(Profile profile) {
        profiles.add(profile);
    }

    public void score(Criteria criteria) {
        for (Profile profile : profiles) {
            profile.matches(criteria);
        }
    }

    public List<Profile> ranked() {
        profiles.sort(Comparator.comparing(Profile::score).reversed());
        return profiles;
    }
}
