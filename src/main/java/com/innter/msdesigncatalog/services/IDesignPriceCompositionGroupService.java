package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesignPriceCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignPriceCompositionGroupResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesignPriceCompositionGroupService {

    DesignPriceCompositionGroupResponse saveDesignPriceCompositionGroup (DesignPriceCompositionGroupRequest designPriceCompositionGroupRequest);

    List<DesignPriceCompositionGroupResponse> getDesignPriceCompositionGroup (Pageable pageable);

    DesignPriceCompositionGroupResponse editedDesignPriceCompositionGroup (DesignPriceCompositionGroupRequest designPriceCompositionGroupRequest,Long id);

    DesignPriceCompositionGroupResponse editedDesignPriceCompositionGroupByStatus (DesignRequestStatus desingRequestStatus, Long id);
}
