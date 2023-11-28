package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesingCompositionGroupRequest;
import com.innter.msdesigncatalog.dtos.response.DesingCompositionGroupResponse;

import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IDesingCompositionGroupService {

    DesingCompositionGroupResponse saveDesingCompositionGroup (DesingCompositionGroupRequest desingCompositionGroupRequest);

    List<DesingCompositionGroupResponse> getDesingCompositionGroup(Pageable pageable);

    DesingCompositionGroupResponse editedDesingCompositionGroup (DesingCompositionGroupRequest desingCompositionGroupRequest, Long id);

    DesingCompositionGroupResponse editedDesingCompositionGroupById (DesingRequestStatus desingRequestStatus, Long id);
}
