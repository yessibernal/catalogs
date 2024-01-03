package com.innter.msdesigncatalog.mappers;

import com.innter.msdesigncatalog.dtos.request.DesignPrintRequest;
import com.innter.msdesigncatalog.dtos.response.DesignPrintResponse;
import com.innter.msdesigncatalog.entities.DesignPrintEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignPrintMapper implements IDesignPrintMapper {
    @Override
    public DesignPrintResponse designPrintToDesignPrintResponse(DesignPrintEntity designPrint) {
        DesignPrintResponse designPrintResponse = new DesignPrintResponse();
        designPrintResponse.setId(designPrint.getId());
        designPrintResponse.setName(designPrint.getName());
        return designPrintResponse;
    }

    @Override
    public DesignPrintEntity designPrintRequestToDesingPrint(DesignPrintRequest designPrintRequest) {
        DesignPrintEntity designPrint = new DesignPrintEntity();
        designPrint.setName(designPrintRequest.getName());
        return designPrint;
    }
}
