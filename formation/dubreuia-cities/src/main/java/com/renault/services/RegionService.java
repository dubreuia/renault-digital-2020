package com.renault.services;

import com.renault.entities.Country;
import com.renault.entities.Region;

public interface RegionService {

    void save(Region region);

    void save(Country country, Region region);

}
