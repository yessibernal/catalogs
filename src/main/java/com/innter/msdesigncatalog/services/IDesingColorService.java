package com.innter.msdesigncatalog.services;

import com.innter.msdesigncatalog.dtos.request.DesingColorRequest;
import com.innter.msdesigncatalog.dtos.response.DesingColorResponse;
import com.innter.msdesigncatalog.dtos.request.DesingRequestStatus;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IDesingColorService {

    DesingColorResponse saveDesingColor (DesingColorRequest newDesingColor);

    List<DesingColorResponse> getDesingColors (Pageable pageable);

    DesingColorResponse editedDesingColor (DesingColorRequest newDesingColor,Long id);

    DesingColorResponse editedDesingColorByStatus (DesingRequestStatus desingRequestStatus, Long id);
}
