package com.innter.msdesigncatalog.services;



import com.innter.msdesigncatalog.dtos.request.DesignColorRequest;
import com.innter.msdesigncatalog.dtos.request.DesignRequestStatus;
import com.innter.msdesigncatalog.dtos.response.DesignColorResponse;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IDesignColorService {

    DesignColorResponse saveDesignColor (DesignColorRequest newDesignColor);

    List<DesignColorResponse> getDesignColors (Pageable pageable);

    DesignColorResponse editedDesignColor (DesignColorRequest newDesignColor,Long id);

    DesignColorResponse editedDesignColorByStatus (DesignRequestStatus designRequestStatus, Long id);
}
