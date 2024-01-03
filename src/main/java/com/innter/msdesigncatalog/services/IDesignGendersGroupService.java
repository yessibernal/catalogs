package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesignGenderGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignGenderGroupResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesignGendersGroupService {

    DesignGenderGroupResponse saveDesignGenderGroup (DesignGenderGroupRequest designGenderGroupRequest);

    List<DesignGenderGroupResponse> getDesignGenderGroup (Pageable pageable);

    DesignGenderGroupResponse editedDesignGenderGroup (DesignGenderGroupRequest designGenderGroupRequest, Long id);

    DesignGenderGroupResponse editedDesignGenderGroupByStatus (DesignRequestStatus designRequestStatus, Long id);
}
