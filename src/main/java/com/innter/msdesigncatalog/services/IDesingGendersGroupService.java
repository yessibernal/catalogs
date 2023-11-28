package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesingGenderGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingGenderGroupResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDesingGendersGroupService {

    DesingGenderGroupResponse saveDesingGenderGroup (DesingGenderGroupRequest desingGenderGroupRequest);

    List<DesingGenderGroupResponse> getDesingGenderGroup (Pageable pageable);

    DesingGenderGroupResponse editedDesingGenderGroup (DesingGenderGroupRequest desingGenderGroupRequest, Long id);

    DesingGenderGroupResponse editedDesingGenderGroupByStatus (DesingRequestStatus  desingRequestStatus, Long id);
}
