package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesignGarmentGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignGarmentGroupResponse;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IDesignGarmentGroupService {

    DesignGarmentGroupResponse saveDesignGarmentGroup (DesignGarmentGroupRequest designGarmentGroupRequest);

    List<DesignGarmentGroupResponse> getDesignGarmentsGroup (Pageable pageable);

    DesignGarmentGroupResponse editedDesignGarmentGroup (DesignGarmentGroupRequest designGarmentGroupRequest, Long id);

    DesignGarmentGroupResponse editedDesignGarmentGroupByStatus (DesignRequestStatus designRequestStatus, Long id);
}
