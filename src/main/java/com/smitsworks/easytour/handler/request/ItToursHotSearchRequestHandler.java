package com.smitsworks.easytour.handler.request;

import com.smitsworks.easytour.models.Request;
import com.smitsworks.easytour.command.request.HotSearchRequestCommand;
import com.smitsworks.easytour.command.request.ItToursSearchBaseRequestCommand;
import com.smitsworks.easytour.command.request.RequestCommand;
import com.smitsworks.easytour.command.response.ItToursHotSearchResponseCommand;
import com.smitsworks.easytour.command.response.ResponseCommand;
import com.smitsworks.easytour.service.RequestService;
import com.smitsworks.easytour.utils.ComeBackUtils;
import com.smitsworks.easytour.utils.RequestsPullUtils;
import com.smitsworks.easytour.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 13.09.2017 class for handle requests to ItToursSearch
 */
@Service
public class ItToursHotSearchRequestHandler implements RequestHandler {

    @Autowired
    TimeUtils timeUtils;

    @Autowired
    RequestService requestService;

    @Autowired
    RequestsPullUtils pullUtils;

    @Autowired
    ComeBackUtils backUtils;

    @Override
    public ResponseCommand handleRequest(Request request) {
        ResponseCommand responseCommand = null;
        Request entity = requestService.findByFields(request);
        if (entity == null) {//Request does not in request database
            requestService.saveRequest(request);
            Request entityRequest = requestService.findByFields(request);
            HotSearchRequestCommand command = new HotSearchRequestCommand(
                    entityRequest, 1, false, true);
            pullUtils.addRequestCommandToPull(command);
            responseCommand = new ItToursHotSearchResponseCommand(entityRequest,
                    backUtils.calculate(command));
            return responseCommand;
        }
        RequestCommand requestCommand = pullUtils.getCommandByRequest(entity);//return request if it in current's session pull
        if (requestCommand == null) {//request does not in curren session pull
                HotSearchRequestCommand command = new HotSearchRequestCommand(
                        entity, 1, false, true);
                pullUtils.addRequestCommandToPull(command);
                responseCommand = new ItToursHotSearchResponseCommand(entity,
                        backUtils.calculate(command));
                return responseCommand;
        }
        requestCommand.setByHuman(Boolean.TRUE);//request in current session pull
        if (!requestCommand.getDone()) {//request does not done
                responseCommand = new ItToursHotSearchResponseCommand(entity,
                        backUtils.calculate(requestCommand));
                return responseCommand;
        }
        responseCommand = new ItToursHotSearchResponseCommand(entity,//request already done
                backUtils.calculate(requestCommand));
        return responseCommand;
    }

    @Override
    public ItToursSearchBaseRequestCommand getBaseRequestCommand() {
        Request request = new Request();
        request.setHotel_Rating("3:78");
        request.setNight_From(2);
        request.setNight_Till(7);
        Request entity = requestService.findByFields(request);
        if (entity == null) {
            requestService.saveRequest(request);
            entity = requestService.findByFields(request);
        }
        ItToursSearchBaseRequestCommand command = new ItToursSearchBaseRequestCommand(
                entity, 1, false, timeUtils.getCurrentTime());
        return command;
    }

}
