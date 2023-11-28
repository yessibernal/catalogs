package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesingGarmentGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingGarmentGroupResponse;

import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IDesingGarmentGroupService {

    DesingGarmentGroupResponse saveDesingGarmentGroup (DesingGarmentGroupRequest desingGarmentGroupRequest);

    List<DesingGarmentGroupResponse> getDesingGarmentsGroup (Pageable pageable);

    DesingGarmentGroupResponse editedDesingGarmentGroup (DesingGarmentGroupRequest desingGarmentGroupRequest, Long id);

    DesingGarmentGroupResponse editedDesingGarmentGroupByStatus (DesingRequestStatus desingRequestStatus, Long id);
}
