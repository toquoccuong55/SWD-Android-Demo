package com.shoesshop.groupassignment.utils;

import com.shoesshop.groupassignment.ShoematicRepository.ShoematicService;

public class ClientApi extends BaseApi {
    public ShoematicService shoematicService() {
        return this.getService(ShoematicService.class, ConfigApi.BASE_URL);
    }
}
