package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesingPriceCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingPriceCompositionGroupResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesingPriceCompositionGroupService {

    DesingPriceCompositionGroupResponse saveDesingPriceCompositionGroup (DesingPriceCompositionGroupRequest desingPriceCompositionGroupRequest);

    List<DesingPriceCompositionGroupResponse> getDesingPriceCompositionGroup (Pageable pageable);

    DesingPriceCompositionGroupResponse editedDesingPriceCompositionGroup (DesingPriceCompositionGroupRequest desingPriceCompositionGroupRequest,Long id);

    DesingPriceCompositionGroupResponse editedDesingPriceCompositionGroupByStatus (DesingRequestStatus desingRequestStatus, Long id);
}
