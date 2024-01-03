package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesignCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignCompositionGroupResponse;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IDesignCompositionGroupService {

    DesignCompositionGroupResponse saveDesignCompositionGroup (DesignCompositionGroupRequest designCompositionGroupRequest);

    List<DesignCompositionGroupResponse> getDesignCompositionGroup(Pageable pageable);

    DesignCompositionGroupResponse editedDesignCompositionGroup (DesignCompositionGroupRequest designCompositionGroupRequest, Long id);

    DesignCompositionGroupResponse editedDesignCompositionGroupById (DesignRequestStatus designRequestStatus, Long id);
}
